package com.stude.aop.note;

import com.stude.aop.note.definedAspect.ExtTransaction;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Component
public class NoteRun {

    @ExtTransaction
    public void add(){
        System.out.println("执行方法");
        int a = 3/0;
    }

    public void reduce(){
        System.out.println("执行减法");
        int a = 3/0;
    }
}
