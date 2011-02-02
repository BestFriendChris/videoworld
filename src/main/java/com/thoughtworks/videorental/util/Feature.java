package com.thoughtworks.videorental.util;

public enum Feature {
    DetailedMovies(2);

    private final int minIteration;

    private final static int DEFAULT_ITERATION = 1;

    Feature(int minIteration) {
        this.minIteration = minIteration;
    }

    public boolean isEnabled() {
        return currentIteration() >= minIteration;
    }

    private static int currentIteration() {
        return Integer.getInteger("current.iteration", DEFAULT_ITERATION);
    }
}
