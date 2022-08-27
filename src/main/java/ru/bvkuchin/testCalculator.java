package ru.bvkuchin;

import ru.bvkuchin.annotations.AfterSuite;
import ru.bvkuchin.annotations.BeforeSuite;
import ru.bvkuchin.annotations.Test;

public class testCalculator {

    Calculator calculator = new Calculator();


    @Test
    public void testSum() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест пройден");

        } else {
            System.out.println("тест не пройден");

        }

    }

}
