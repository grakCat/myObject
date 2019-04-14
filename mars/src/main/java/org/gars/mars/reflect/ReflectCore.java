package org.gars.mars.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/10/30.
 * 通过反射生成对象，并已知对象变量名和类型赋值
 *
 * @author grayCat
 * @since 1.0
 */
public class ReflectCore {

    Logger log = LoggerFactory.getLogger(getClass());
    /*路径是java目录开始的*/
    private String path = "com.gray.game.grayreflect.ReflectModel";

    private ReflectModel model;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ReflectCore core = new ReflectCore();
//        core.run();
        core.getObject();
    }


    private void run() {
        String path = "com.gray.game.grayreflect.ReflectModel";
        try {
            //获取对象实例
            ReflectModel model = (ReflectModel) Class.forName(path).newInstance();
            if (model != null) {
                System.out.println("实例化ReflectModel成功！");
                //开始设置值
                Field field = model.getClass().getField("color");
                String vobj = "大豆";
                Object object = parseValue(field.getType(), vobj);
                field.set(model, object);
            }
        } catch (Exception e) {
            log.debug("实例化失败", path);
        }
    }

    private void setValue() {
        try {
            Field field = model.getClass().getField("color");
            String vobj = "大豆";
            Object object = parseValue(field.getType(), vobj);
            field.set(model, object);

            field = model.getClass().getField("value");
            String value = "55";
            object = parseValue(field.getType(), value);
            field.set(model, object);

            field = model.getClass().getField("city");
            Map<Integer, String> city = new HashMap<>();
            city.put(5, "成都");
            field.set(model, city);

            log.debug(model.toString());
        } catch (Exception e) {
            log.debug("赋值失败", path);
        }
    }

    public void getObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //获取类信息
        Class<?> c1 = Class.forName("ReflectModel");
        Class<?> c2 = ReflectModel.class;
        //实例化
        Object newInstance = c2.newInstance();
    }

    /**
     * 找到对应的类型
     *
     * @param fieldClazz
     * @param value
     * @return
     */
    public static Object parseValue(Type fieldClazz, String value) {
        if (fieldClazz == String.class) {
            return value;
        } else if (fieldClazz == int.class || fieldClazz == Integer.class) {
            return Double.valueOf(value).intValue();
        } else if (fieldClazz == byte.class || fieldClazz == Byte.class) {
            return Double.valueOf(value).byteValue();
        } else if (fieldClazz == boolean.class || fieldClazz == Boolean.class) {
            if (value == null || value.isEmpty() || "false".equalsIgnoreCase(value) || "0.0".equals(value) || "0".equals(value)) {
                return false;
            } else {
                return true;
            }
        } else if (fieldClazz == short.class || fieldClazz == Short.class) {
            return Double.valueOf(value).shortValue();
        } else if (fieldClazz == long.class || fieldClazz == Long.class) {
            return Double.valueOf(value).longValue();
        } else if (fieldClazz == float.class || fieldClazz == Float.class) {
            return Float.valueOf(value);
        } else if (fieldClazz == double.class || fieldClazz == Double.class) {
            return Double.valueOf(value);
        }
        return value;
    }
}
