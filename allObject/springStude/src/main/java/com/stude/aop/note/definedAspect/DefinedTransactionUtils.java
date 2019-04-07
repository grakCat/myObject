package com.stude.qiao.note.definedAspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * Created on 2019/4/6.
 *
 * @author Grak
 * @since 1.0
 */
@Component
@Scope("prototype")//设置成原型模式，每次都会new一个新的
public class DefinedTransactionUtils {

    private TransactionStatus transactionStatus;
    // 获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    // 开启事务
    public TransactionStatus begin() {
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    // 提交事务
    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    // 回滚事务
    public void rollback() {
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
