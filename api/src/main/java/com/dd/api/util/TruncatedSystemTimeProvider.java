package com.dd.api.util;

/**
 * Simple time provider that takes the current system time and divides it by 1000 to conform to
 * the integer standard. This provides a smaller storage medium with minimal loss of relevant info.
 *
 * @author noahsternbrg
 */
public class TruncatedSystemTimeProvider implements TimeProvider {

    @Override
    public long provideTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
