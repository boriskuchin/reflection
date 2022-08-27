package ru.bvkuchin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.bvkuchin.tester.annotations.Test;
import ru.bvkuchin.tester.testerclass.Tester;
import ru.bvkuchin.testingclass.testCalculator;

import java.lang.reflect.InvocationTargetException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        logger.info("**********************************************************************8");
        logger.info("Запуск тестов по экземпляру тестового класса");

        Tester.start(testCalculator.class);

        logger.info("**********************************************************************8");
        logger.info("Запуск тестов по имени тестового класса");
        Tester.start("ru.bvkuchin.testingclass.testCalculator");


    }
}
