package com.stude.spring_boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2019/4/16.
 *
 * @author Grak
 * @since 1.0
 */
@Controller
public class HelloCpntroller {

    @RequestMapping("/success")
    public String success(){
        //https://www.thymeleaf.org/documentation.html
        return "success";
    }
}
