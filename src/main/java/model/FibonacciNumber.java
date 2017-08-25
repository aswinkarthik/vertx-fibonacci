package model;

import java.math.BigInteger;

public final class FibonacciNumber {
    private final int seqId;
    private final BigInteger value;

    public FibonacciNumber(int seqId, BigInteger value) {
        this.seqId = seqId;
        this.value = value;
    }

    public int getSeqId() {
        return seqId;
    }

    public BigInteger getValue() {
        return value;
    }
}
