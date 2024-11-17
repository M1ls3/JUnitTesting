package org.example;

import java.util.List;
import java.util.Map;

public interface Group {

    double calculateAverageMarks(Map<String, List<Integer>> marks, String studentLastName);
}
