package com.example.demo.lamada;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2019/4/2.
 *
 * @author hy
 * @since 1.0
 */
public class Lamada01 {

    private void lamadaUse(){
        Function<String,String> fn = String::toUpperCase;
        Consumer<String> run = System.out::println;
        Supplier<String> get = () -> 5*5 + "";

        int[] name ={1,2,3,4};
        Stream.of(name).forEach(System.out::println);
        List<Integer> list = new LinkedList();
        list.stream().skip(5).limit(10).forEach(System.out::println);

        list.stream().filter(x -> x %2== 0).findFirst().get();
        list.stream().min(Comparator.comparing(x -> x)).get();
        list.stream().max(Comparator.comparing(x -> x)).get();
        list.stream().collect(Collectors.toList());
        Stream.generate(() -> 1).limit(20);
        Stream.iterate(5 , x-> x+ 2).limit(10);

    }
}
