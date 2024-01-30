package com.dd.api.util;

/**
 * Simple interface to provide a timestamp. Useful in unit testing contexts.
 * @author noahsternberg
 */
public interface TimeProvider {

    long provideTime();
}
