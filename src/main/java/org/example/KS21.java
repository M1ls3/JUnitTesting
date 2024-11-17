package org.example;

import java.util.List;
import java.util.Map;

public class KS21 implements Group {
    @Override
    public double calculateAverageMarks(Map<String, List<Integer>> marks, String studentLastName) {
        List<Integer> studentMarks = marks.get(studentLastName);

        boolean allWithinBound = studentMarks.stream()
                .allMatch(v -> v >= 0 && v <= 100);
        if (!allWithinBound) {
            return -1;
        }
        if (studentMarks.isEmpty()) {
            return -2;
        }

        return studentMarks.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }
}
