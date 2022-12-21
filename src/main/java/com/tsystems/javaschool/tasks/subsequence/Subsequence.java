package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {

        if (x == null && y.isEmpty())
            throw new IllegalArgumentException();
        if (y == null && x.isEmpty())
            throw new IllegalArgumentException();

        if (x.equals(y))
            return true;

        if (x == null || y == null)
            return false;

        if (x.size() < y.size()) {
            int index = 0;
            for (int i = 0; i < x.size(); i++) {

                if (y.subList(index, y.size() - 1).indexOf(x.get(i)) != -1) {

                    index = y.subList(index, y.size() - 1).indexOf(x.get(i)) + 1;

                } else
                    return false;

            }
            return true;
        } else
            return false;

    }
}
