package com.devlauten.webanalyzer.domain.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CounterTest {

    @Test
    public void incrementAndGet_increasesCount() {
        Counter counter = new Counter();

        assertEquals(1, counter.incrementAndGet());
        assertEquals(2, counter.incrementAndGet());
        assertEquals(2, counter.getCount());
    }

    @Test
    public void reset_restoresInitialValue() {
        Counter counter = new Counter();
        counter.incrementBy(5);

        counter.reset();

        assertEquals(Counter.INITIAL_COUNT, counter.getCount());
    }
}
