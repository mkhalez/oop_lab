package org.example.utils.implementation.filter.sorting;

import org.example.dto.Result;

import java.util.Comparator;
import java.util.Map;

public interface SortStrategy {
    Comparator<Map.Entry<String, Result>> getComparator();
}
