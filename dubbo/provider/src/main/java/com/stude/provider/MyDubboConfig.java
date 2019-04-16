package com.stude.provider;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2019/4/15.
 *
 * @author Grak
 * @since 1.0
 */
@Configuration
public class MyDubboConfig {

    @Bean
    public ProtocolConfig protocol(){
        ProtocolConfig config = new ProtocolConfig();
        config.setPort(28007);
        return config;
    }
}
