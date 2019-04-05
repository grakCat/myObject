package com.stude.qiao.lamada;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2019/4/2.
 *
 * @author Grak
 * @since 1.0
 */
public class Lamada01 {

    public void lamada001(){
        Function<String,String> nf = String::toUpperCase;
        Supplier<String> get = () -> 55 + "";
        Consumer<Integer> set = System.out::println;

        int[] arr = {};
        List<String> list = new LinkedList<>();
        Stream.of(arr).skip(5).limit(10).forEach(System.out::println);
        Stream.iterate(5 , x -> x + 1).limit(10).collect(Collectors.toList());
        Stream.generate(() -> 1).limit(11).sorted().filter(x -> x > 10).distinct().max(Comparator.comparing(x -> x)).get();
        Stream.iterate(5 , x -> x + 1).limit(10).findFirst().get();
        Stream.generate(() -> 1).limit(11).peek(x->System.out.println()).findFirst().get();
        Stream.generate(() -> 1).limit(11).collect(Collectors.groupingBy(x -> x > 2));
//        Stream.generate(() -> 1).limit(11).collect(Collectors.toMap(,));
    }

    public static void main(String[] args) {
        Map mm = Stream.iterate(1, x -> x+1).limit(50).collect(Collectors.toMap(x -> x, y -> y %2 == 0));
        System.out.println(new Gson().toJson(mm));

    }
}
