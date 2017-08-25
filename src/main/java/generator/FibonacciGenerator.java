package generator;

import model.FibonacciNumber;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class FibonacciGenerator implements Iterator<FibonacciNumber> {

    private final int limit;
    private FibonacciNumber current;
    private FibonacciNumber next;
    private final AtomicInteger counter = new AtomicInteger();

    public FibonacciGenerator() {
        this(Integer.MAX_VALUE);
    }

    public FibonacciGenerator(final int limit) {
        this.limit = limit;
        this.current = new FibonacciNumber(counter.getAndIncrement(), BigInteger.ONE);
        this.next = new FibonacciNumber(counter.getAndIncrement(), BigInteger.ONE);
    }

    @Override
    public boolean hasNext() {
        return current.getSeqId() < limit;
    }

    @Override
    public FibonacciNumber next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        FibonacciNumber toBeReturned = this.current;
        FibonacciNumber nextToBeReturned = this.next;

        this.next = new FibonacciNumber(counter.getAndIncrement(),
                current.getValue().add(this.next.getValue())
        );
        this.current = nextToBeReturned;
        return toBeReturned;
    }
}
