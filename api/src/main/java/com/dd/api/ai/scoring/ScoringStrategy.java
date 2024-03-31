package com.dd.api.ai.scoring;

/**
 * Simple scoring strategy to allow customisation
 * of the scoring process
 * @param <T> The type of object you are scoring.
 * @author Noah Sternberg
 */
public interface ScoringStrategy<T> {
    public double score(T toScore);
}