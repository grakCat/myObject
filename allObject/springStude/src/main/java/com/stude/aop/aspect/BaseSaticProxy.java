package com.stude.aop.aspect;

/**
 * Created on 2019/4/5.
 * aspect
 * 优势: 分层架构
 * WEB层：SpringMVC
 * 业务层：Spring的IoC
 * 持久层：Spring的JDBCTemplate(Spring的JDBC模板，ORM模板用于整合其他的持久层框架)
 *
 * @author Grak
 * @since 1.0
 */
public class BaseSaticProxy {

    /**
     * IoC:Spring创建对象,通过配置实现的
     */


    /**
     * AOP：面向切面编程。
     * 1.对业务逻辑的各个部分进行隔离
     * 2.耦合度降低
     * 3.提高程序的可重用性，
     * 4.同时提高了开发的效率
     * 主要的功能: 日志记录，性能统计，安全控制，事务处理，异常处理
     * 功能： 让关注点代码与业务代码分离！(关注点,重复代码就叫做关注点)(关注点形成的类，就叫切面(类)！)
     * 体现：代理模式
     */

    /**
     * 实现方式
     * 1.静态代理
     * 2.jdk动态代理
     * 3.CGLIB动态代理
     * 4.aop注解
     * 5.xml实现aop
     */

    /**
     * Spring事务使用
     * 分类：编程式事务控制、声明式事务控制
     * 事务基本特性acid:原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）
     */

    /**
     * 静态代理
     * 程序运行前就已经存在代理类的字节码文件
     */
    public interface IUserDao {
        void save();
    }

    public static class UserDao implements IUserDao {
        @Override
        public void save() {
            System.out.println("存储数据");
        }
    }

    public static class UserDaoProxy implements IUserDao {
        private UserDao userDao;

        public UserDaoProxy(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        public void save() {
            System.out.println("检测数据类型是否符合标准");
            userDao.save();
            System.out.println("打印日志");
        }
    }


    public static void main(String[] args) {
        //静态代理
        new UserDaoProxy(new UserDao()).save();
    }
}
