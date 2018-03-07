package de.scout24.webanalyzerrest.util;

/**
 * Utility class to count thing;
 * Attention! Works with int. Don't overlap!
 */
public class Counter {

    public static final int INITIAL_COUNT = 0;
    private int count;

    public Counter() {
        count = INITIAL_COUNT;
    }

    public void increment() {
        count++;
    }

    public void incrementBy(int amount) {
        count = count + amount;
    }

    public int incrementAndGet() {
        return ++count;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = INITIAL_COUNT;
    }
}
