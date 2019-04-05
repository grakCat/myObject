package com.stude.qiao.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created on 2019/4/4.
 *
 * @author hy
 * @since 1.0
 */
public class Gson01 {

    @SerializedName("Name")
    public String name;

    @SerializedName(value = "Address",alternate = {"email","add_redss"})
    public String address;

    public void gson01(){
        Gson gson = new Gson();
        Gson gson1 = new GsonBuilder()
                .serializeNulls()
                .disableInnerClassSerialization()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .setDateFormat("yyyy=MM-dd")
                .create();

        gson.toJson("sss");
        gson.fromJson("",Gson01.class);
        gson.fromJson("",boolean.class);
        gson.fromJson("",new TypeToken<List<Integer>>(){}.getType());
        gson.fromJson("",Gson01.class);


    }

    @SerializedName("")
    public String aaa;
    @SerializedName(value = "ll",alternate = {"ss","ssw"})
    public String ooo;

}
