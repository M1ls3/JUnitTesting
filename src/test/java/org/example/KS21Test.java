package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KS21Test {

    private static Map<String, List<Integer>> marks;
    private KS21 ks21 = new KS21();

    @BeforeAll
    static void initData() {
        marks = new HashMap<>();

        marks.put("Yehor", new ArrayList<Integer>() {
            {
                this.add(50);
                this.add(60);
                this.add(100);
                this.add(70);
            }
        });

        marks.put("Master", new ArrayList<Integer>() {
            {
                this.add(40);
                this.add(30);
                this.add(120); // Оцінка виходить за межі
                this.add(10);
            }
        });

        marks.put("IShowSpeed", new ArrayList<Integer>() {
            {
                this.add(-5); // Оцінка виходить за межі
                this.add(20);
                this.add(35);
            }
        });

        marks.put("Cucuber", new ArrayList<Integer>() {
            {
                this.add(85); // Тільки одна оцінка
            }
        });

        marks.put("Max", new ArrayList<>()); // Порожній список оцінок

        marks.put("Unknown", null); // Студент відсутній у списку

        marks.put("ipgeriogvjerf", new ArrayList<Integer>() {
            {
                this.add(20);
                this.add(25);
                this.add(50);
                this.add(75);
                this.add(85);
                this.add(90);
                this.add(45);
                this.add(60);
                this.add(70);
                this.add(80);
                this.add(95);
                this.add(30);
            }
        });
    }

    @Test
    void avgYehor() {
        double avgMark = this.ks21.calculateAverageMarks(marks, "Yehor");
        Assertions.assertEquals(70.0, avgMark);
    }

    @Test
    void nullMarks() {
        NullPointerException unknownStudent = (NullPointerException)Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            this.ks21.calculateAverageMarks(marks, "Unknown");
        });
        Assertions.assertEquals("Cannot invoke \"java.util.List.stream()\" because \"studentMarks\" is null", unknownStudent.getMessage());
    }

    @Test
    void singleMark() {
        double oneMark = this.ks21.calculateAverageMarks(marks, "Cucuber");
        Assertions.assertEquals(85.0, oneMark);
    }

    @Test
    void outOfBoundMarks() {
        double iShowSpeed = this.ks21.calculateAverageMarks(marks, "IShowSpeed");
        double master = this.ks21.calculateAverageMarks(marks, "Master");
        Assertions.assertEquals(-1.0, iShowSpeed);
        Assertions.assertEquals(-1.0, master);
    }

    @Test
    void emptyMarks() {
        double max = this.ks21.calculateAverageMarks(marks, "Max");
        Assertions.assertEquals(-2.0, max);
    }

    @Test
    void avgIpgeriogvjerf() {
        double ipgeriogvjerf = this.ks21.calculateAverageMarks(marks, "ipgeriogvjerf");
        Assertions.assertEquals(60.41, ipgeriogvjerf, 0.01);
    }

}