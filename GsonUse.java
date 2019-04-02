import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created on 2019/4/2.
 * Gson 的用法
 * @author hy
 * @since 1.0
 */
public class GsonUse {

    private Gson gson = new Gson();

    public static void main(String[] args) {
//        new GsonUse().gson01();
        //@SerializedName("email_address") 属性重命名，指定的制端解析成想要的名字
        //@SerializedName(value = "script_mess", alternate = {"script", "mess"})
        //会按照第一个解析，出现这几个名字都会解析到同一个对象中

        new GsonUse().gson02();

    }

    private void gson01(){
        //基本数据类型解析
        int intData = gson.fromJson("100",int.class);
        boolean booldata = gson.fromJson("false",boolean.class);
        System.out.println("基本数据解析：" + intData + "," + booldata);
        //基本数据生成
        String jsonStr = gson.toJson(intData);
        String jsonBool = gson.toJson(booldata);
        System.out.println("基本数据生成：" + jsonStr + jsonBool);

        User user = new User();
        //java对象转json
        String userJson = gson.toJson(user);
        System.out.println("java对象转json：" + userJson);
        //json转对象java
        user = gson.fromJson(userJson,User.class);
        System.out.println("");
    }

    private void gson02(){
        String[] names = {"黄","红","绿"};
        String gsonStr = gson.toJson(names);
        System.out.println(gsonStr);
        String[] gsonArr = gson.fromJson(gsonStr,String[].class);
        List<String> stringList = gson.fromJson(gsonStr,new TypeToken<List<String>>() {}.getType());

        //不主动导出null值
        String userStr = gson.toJson(new User());
        System.out.println(userStr);
        //显示null的序列化
        Gson gsonNull = new GsonBuilder().serializeNulls().create();
        System.out.println(gsonNull.toJson(new User()));
        //其他设置
        Gson gsonSet = new GsonBuilder()
                .serializeNulls()//设置显示null值
                .setDateFormat("yyyy-MM-dd")//日期格式化
//                .disableInnerClassSerialization()//禁止序列化内部类
//                .generateNonExecutableJson()//生成不可执行的json )]}'
//                .disableHtmlEscaping()//禁止转义html标签
                .setPrettyPrinting()//格式化输出
                .create();
        System.out.println(gsonSet.toJson(new User()));

    }



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
