import generator.FibonacciGenerator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import model.FibonacciNumber;

import java.util.ArrayList;
import java.util.List;

public class ApplicationVerticle extends AbstractVerticle {

    public static final int LIMIT = 4;

    @Override
    public void start(Future future) throws Exception {
        Router router = Router.router(vertx);

        router.get().handler(event -> {
            HttpServerResponse res = event.response();
            res.putHeader("content-type", "application/json; charset=utf-8");
            event.next();
        });

        router.get("/ping").handler(this::ping);
        router.get("/fibs").handler(this::fibs);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 9090),
                        event -> {
                            if (event.succeeded()) {
                                future.complete();
                            } else {
                                future.fail(event.cause());
                            }
                        });
    }

    private void ping(RoutingContext event) {
        event.response().end("pong");
    }

    private void fibs(RoutingContext context) {
        int limit = LIMIT;
        try {
            limit = Integer.parseInt(context.request().getParam("limit"));
        } catch (NumberFormatException e) {
            System.out.println("Using default value");
        }

        FibonacciGenerator generator = new FibonacciGenerator(limit);
        List<FibonacciNumber> fibs = new ArrayList<>();

        generator.forEachRemaining(fibs::add);

        context
                .response()
                .end(Json.encodePrettily(fibs));
    }
}
