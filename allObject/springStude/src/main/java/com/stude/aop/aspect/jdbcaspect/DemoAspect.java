package com.stude.qiao.aspect.jdbcaspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Component
public class DemoAspect {

    /**
     * @Transactional使用
     * 1.只能使用在public方法或者类上，不建议使用在接口上
     * 2.不要在内部使用try catch.使用了内部自己写回滚逻辑
     * value 和 transactionManager
     *  当配置了多个事务管理器时，可以使用该属性指定选择哪个事务管理器
     * propagation:
     *      在默认的代理模式下，只有目标方法由外部调用，才能被 Spring 的事务拦截器拦截（就是说在同一个类，不同方法就算是加上REQUIRES_NEW也不会新创建一个事务，要用两个类）
     *
     *      Propagation.REQUIRED 如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。(默认值)
     *      Propagation.SUPPORTS 如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
     *      Propagation.MANDATORY 如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常。
     *      Propagation.REQUIRES_NEW 重新创建一个新的事务，如果当前存在事务，暂停当前的事务。
     *      Propagation.NOT_SUPPORTED 以非事务的方式运行，如果当前存在事务，暂停当前的事务。
     *      Propagation.NEVER 以非事务的方式运行，如果当前存在事务，则抛出异常。
     *      Propagation.NESTED 和 Propagation.REQUIRED 效果一样。
     * isolation ：
     *      Isolation.DEFAULT 使用底层数据库默认的隔离级别。（默认）
     *      Isolation.READ_UNCOMMITTED
     *      Isolation.READ_COMMITTED
     *      Isolation.REPEATABLE_READ
     *      Isolation.SERIALIZABLE
     * timeout ：事务的超时时间，默认值为-1。如果超过该时间限制但事务还没有完成，则自动回滚事务。
     * readOnly ：指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。
     * rollbackFor ：用于指定能够触发事务回滚的异常类型，可以指定多个异常类型。
     * noRollbackFor ：抛出指定的异常类型，不回滚事务，也可以指定多个异常类型。
     */


    @Autowired
    private UserService user;


//    @Transactional()//可以使用这个mybatis注解  或者自定义AopSpringAspect方法
    public void runAspect(){
        //事务里面不要用try catch 如果使用了一定要手动回滚
        user.create("刘良","mm","江西");
        List<Userinfo> userinfos =  user.findAll();
        int uid = 0;
        for(Userinfo info : userinfos){
            if(info.getUsername().equals("刘良")){
                uid = info.getUid();
            }
        }
        System.out.println("用户信息：" + user.findById(uid));
        int m = 1/0;//模拟异常，回滚提交
        user.update("李白","ww","麻花",uid);
        System.out.println("用户修改：" + user.findById(uid));
//        user.delete(uid);
    }
}
