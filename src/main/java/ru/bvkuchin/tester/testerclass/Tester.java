package ru.bvkuchin.tester.testerclass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.bvkuchin.tester.annotations.AfterSuite;
import ru.bvkuchin.tester.annotations.BeforeSuite;
import ru.bvkuchin.tester.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Tester {
    private static final Logger logger = LogManager.getLogger(Tester.class);

    public static void start(Class<?> testClazz) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        startTestProcess(testClazz);
    }


    public static void start(String testClassName) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> testClazz = Class.forName(testClassName);
        startTestProcess(testClazz);
    }



    private static void startTestProcess(Class<?> testClazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//      Вытаскиваем методы из класса тестирования
        Method[] methods = testClazz.getDeclaredMethods();

//      Проверяем есть ли пустой конструктор
        Constructor<?> constructor = getDefaultConstructor(testClazz);
        if (constructor == null) {
            throw new RuntimeException("Отсутсвует пустой конструктор");
        }

//      Создаем обхект клааса тестивраония
        var o = constructor.newInstance();

        Map<Integer, ArrayList<Method>> testMethodsMap = new HashMap<>();
        List<Method> beforeSuiteMethods = new ArrayList<>();
        List<Method> afterSuiteMethods = new ArrayList<>();

        for (Method method : methods) {
            logger.debug("Анализируемый метод: " + method.getName() + " -> " + Arrays.toString(method.getAnnotations()));
//      Составляем массив методов с аннотацией Test
            if (method.isAnnotationPresent(Test.class)) {
                if (method.isAnnotationPresent(BeforeSuite.class) || method.isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("Аннотация Test не может быть совмещена с аннорациями BeforeSuit или AfterSuit");
                }
                Integer key = method.getAnnotation(Test.class).priority();
                if (!testMethodsMap.containsKey(key)) {
                    ArrayList<Method> newList = new ArrayList<>();
                    newList.add(method);
                    testMethodsMap.put(key, newList);
                    logger.debug("Метод " + method.getName() + " с новым приоритетом " + key + " добавлен в Map");
                } else {
                    ArrayList<Method> currentList = testMethodsMap.get(key);
                    currentList.add(method);
                    logger.debug("Метод " + method.getName() + " с существующим приоритетом " + key + " добавлен в Map");
                }
//      Составляем массив методов с аннотацией BeforeSuits
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethods.add(method);
//      Составляем массив методов с аннотацией AfterSuits
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
            afterSuiteMethods.add(method);
            } else {
                logger.debug("у метода " + method.getName() + " нет нужных аннотаций аннотаций");
            }
        }

        if (afterSuiteMethods.size() > 1 || beforeSuiteMethods.size() > 1) {
            throw new RuntimeException("Начальный и завершающий методы тестирования должны быть в эдинственном экземпляре или отсутсвовать");
        }

//        Запускаем начальный скрипт
        if (beforeSuiteMethods.size() == 1) {
            beforeSuiteMethods.get(0).invoke(o);
        }

//        Запускаем тесты согласно приоритетам
        for (Map.Entry entry : testMethodsMap.entrySet()) {
            logger.debug(entry.getKey() + " -> " + entry.getValue());
            for (Method m : (ArrayList<Method>) entry.getValue()) {
                m.invoke(o);
            }
        }

//        Запускаем конечный скрипт
        if (afterSuiteMethods.size() == 1) {
            afterSuiteMethods.get(0).invoke(o);
        }
    }

    private static Constructor<?> getDefaultConstructor(Class<?> testClazz) {
        for(Constructor<?> constructor : testClazz.getDeclaredConstructors()){
            if(constructor.getParameterCount()==0 ){
                return constructor;
            }
        }
        return null ;
    }
}