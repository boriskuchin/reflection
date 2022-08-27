package ru.bvkuchin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.bvkuchin.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Tester {
    private static final Logger logger = LogManager.getLogger(Tester.class);

    public static void start(Class<?> testClazz) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Method[] methods = testClazz.getDeclaredMethods();

        var o = testClazz.getDeclaredConstructor().newInstance();

        for (Method method : methods) {
            logger.debug(method.getName());
            logger.debug(Arrays.toString( method.getAnnotations()));
            method.invoke(o);
            method.getAnnotation(Test.class).priority();
        }

    }



    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        start(testCalculator.class);


    }
}