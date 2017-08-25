# Fibonacci series using Vertx

[Vert.X](http://vertx.io/) is a toolkit for developing reactive applications on JVM

This is an test project to learn on how to expose a JSON endpoint that generates fibonacci series.

## Usage

- Clone the project using `git clone https://github.com/aswinkarthik93/vertx-fibonacci.git`
- `./gradlew shadowJar run`
- `curl http://localhost:9090/fibs?limit=6`