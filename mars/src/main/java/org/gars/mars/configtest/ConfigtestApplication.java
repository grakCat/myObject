package org.gars.mars.configtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.gars.mars.configtest"})
public class ConfigtestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConfigtestApplication.class, args);

        //1.
//		 ctx.getEnvironment(); // 获取 边境变量
//		System.out.println("===========================================");
//		//获取字符串
//		System.out.println("String: " + (ctx.getEnvironment().getProperty("string.port")) );
//
//		//获取整数
//		System.out.println("Interger: " + (ctx.getEnvironment().getProperty("integer.port",Integer.class)));
//		System.out.println(ctx.getEnvironment().getProperty("db.link.url"));
//		System.out.println(ctx.getEnvironment().getProperty("db.link.driver"));
//		System.out.println(ctx.getEnvironment().getProperty("db.link.username"));
//		System.out.println(ctx.getEnvironment().getProperty("db.link.password"));
//		System.out.println("===========================================");

//		MyConf myconf = (MyConf) ctx.getBean("myConf");
//		myconf.show2();
//
//		PropConf propConf = (PropConf) ctx.getBean("propConf");
//		propConf.show();

        SetterConf propConf = (SetterConf) ctx.getBean("setterConf");
        propConf.show();


//		for(int i = 0 ;i< 99;i++){
//			SampleFactory<LGMJConfiginfo> factory  = LGMJConfiginfo.factory;
//			System.out.println("kk");
//			try {
//				Thread.sleep(6000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		ctx.close();
        //可以通过修改Program arguments
        //指定配置文件spring.config.name=xxx.properties
        //修改默认配置文件读取地址spring.config.location=xxx.properties
    }


}
