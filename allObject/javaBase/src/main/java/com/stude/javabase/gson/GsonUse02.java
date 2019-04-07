package com.stude.javabase.gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2019/4/7.
 *
 * @author Grak
 * @since 1.0
 */
public class GsonUse02 {

    private String name = "玉帝";

    /**
     * 创建Gson
     */
    public void creat(){
        //普通创建
        Gson gson = new Gson();
        //自定义创建（设置控制参数）
        Gson gsonBuilder = new Gson().newBuilder()
                .disableHtmlEscaping()//过滤HTML标签
                .disableInnerClassSerialization()//不解析内部类
                .serializeNulls()//显示null的参数
                .setDateFormat("yyyy-MM-dd")//日期格式化
                .setPrettyPrinting()//格式化输出
                .create();

    }

    public void use(){
        Gson gson = new Gson();
        //把java对象序列化成json
        String json = gson.toJson(new LinkedList<>());

        //json转换成对象或变量
        //json转变量
        String jsonInt = "5";
        System.out.println(gson.fromJson(jsonInt,int.class));
        //json转对象
        System.out.println(gson.fromJson("{\"name\":\"玉帝\"}",GsonUse02.class));
        //json转复杂对象
        List<Map<String, GsonUse02>> list = gson.fromJson("对应json数据",new TypeToken<List<Map<String, GsonUse02>>>(){}.getType());
    }


    /**
     * 注解说明
     * @SerializedName("email_address") 属性重命名，指定的制端解析成想要的名字
     *
     * @SerializedName(value = "script_mess", alternate = {"script", "mess"})
     * 会按照第一个解析，出现这几个名字都会解析到同一个对象中
     */
    private class User {
        //省略其它
        public String name = "用户";

        public int age = 100;

        @SerializedName("email_address")
        public String emailAddress = "澜山";
        @SerializedName(value = "script_mess", alternate = {"script", "mess"})
        public String scriptMess = "黄昏";

        public String type;
    }
}
