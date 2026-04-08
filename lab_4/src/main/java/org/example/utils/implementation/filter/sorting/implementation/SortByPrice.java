package org.example.utils.implementation.filter.sorting.implementation;

import org.example.dto.Result;
import org.example.utils.implementation.filter.sorting.SortStrategy;

import java.util.Comparator;
import java.util.Map;

public class SortByPrice implements SortStrategy {
    public Comparator<Map.Entry<String, Result>> getComparator() {
        return Comparator.comparingDouble(e -> e.getValue().price());
    }
}
