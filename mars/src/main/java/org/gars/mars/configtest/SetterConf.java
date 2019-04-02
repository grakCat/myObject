package org.gars.mars.configtest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/12/6.
 * <p>
 * 这种方法要添加processor包
 *
 * @author grayCat
 * @since 1.0
 */
@Configuration
@PropertySource("file:${user.dir}/config/my.properties")
@ConfigurationProperties(prefix = "aaa")
public class SetterConf {

    private String a;
    private String b;
    private String c;
    private List<String> host = new ArrayList<>();

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }


    public void show() {
        System.out.println("a --- > " + a);
        System.out.println("b --- > " + b);
        System.out.println("c --- > " + c);
        System.out.println(host.toString());
    }
}
