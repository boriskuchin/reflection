package ru.bvkuchin.testingclass;

import ru.bvkuchin.testedclass.Calculator;
import ru.bvkuchin.tester.annotations.AfterSuite;
import ru.bvkuchin.tester.annotations.BeforeSuite;
import ru.bvkuchin.tester.annotations.Test;

public class testCalculator {

    public testCalculator(int i) {

    }

    public testCalculator() {

    }

    Calculator calculator = new Calculator();

    @Test(priority = 4)
    public void testSum4() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест4 пройден");
        } else {
            System.out.println("тест4 не пройден");
        }
    }

    @Test(priority = 2)
    public void testSum2() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест2 пройден");
        } else {
            System.out.println("тест2 не пройден");
        }
    }

    @Test(priority = 3)
    public void testSum3() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест3 пройден");
        } else {
            System.out.println("тест3 не пройден");
        }
    }

    @Test(priority = 3)
    public void testSum3_1() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест3_1 пройден");
        } else {
            System.out.println("тест3_1 не пройден");
        }
    }

    @Test(priority = 1)
    public void testDiv1() {
        int a = 1;
        int b = 3;

        if (calculator.div(a, b) == a + b) {
            System.out.println("Тест1 пройден");
        } else {
            System.out.println("Тест1 не пройден");
        }
    }

    public void testSumWithNoTest() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест без аннотации пройден");
        } else {
            System.out.println("тест без аннотации не пройден");
        }
    }

    @BeforeSuite
    public void initialScript() {
        System.out.println("Код перед тестами запущен");
    }

    @AfterSuite
    public void finalScript() {
        System.out.println("Код после тестов запущен");
    }

    @Test(priority = 999)
    public void test999() {

        System.out.println("тест с приоритетом 999 пройден");
    }


    @Test
    public void testSum4Default() {
        int a = 1;
        int b = 3;

        if (calculator.sum(a, b) == a + b) {
            System.out.println("Тест 4 (дефолтный притритет) пройден");
        } else {
            System.out.println("тест 4 (дефолтный притритет) не пройден");
        }
    }

}
