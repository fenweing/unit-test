package com.tuanbaol.unittest;
import com.tuanbaol.unittest.Pack;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.stream.IntStream;
@Service
public class ObjenesisTest {
    public ObjenesisTest() {
        System.out.println("in it.");
    }

    public void print() {
        System.out.println(1);
    }

    public static void main(String[] args) {
//        ObjenesisTest objenesisTest = new SunReflectionFactoryInstantiator<>(ObjenesisTest.class).newInstance();
//        objenesisTest.print();
//        Annotation[] annotations = ObjenesisTest.class.getPackage().getAnnotations();
//        IntStream.range(0, annotations.length).forEach(i -> System.out.println(annotations[i].getClass()));
        Map<String, Integer> map = new ConcurrentHashMap<>(16);
        map.computeIfAbsent("AaAa", key -> {
            return map.computeIfAbsent("BBBB", key2 -> 42);
        });

        System.out.println(map.size());
    }
}
