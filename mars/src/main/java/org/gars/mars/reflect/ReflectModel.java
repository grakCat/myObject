package org.gars.mars.reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/10/30.
 *
 * @author grayCat
 * @since 1.0
 */
public class ReflectModel {

    public int value;

    public String color;

    public List<String> name = new ArrayList<>();

    public Map<Integer, String> city = new HashMap<>();

    @Override
    public String toString() {
        return "ReflectModel{" +
                "value=" + value +
                ", color='" + color + '\'' +
                ", name=" + name +
                ", city=" + city +
                '}';
    }
}
