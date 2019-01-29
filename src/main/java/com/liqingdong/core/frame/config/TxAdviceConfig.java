package com.liqingdong.core.frame.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * 全局声明式事务配置
 *
 * @author liqingdong
 * @since 2019/01/28 12:37
 */
@Aspect
@Configuration // 或使用@Component注解
public class TxAdviceConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * {@link TransactionInterceptor} 的创建方式还可通过
     * {@link TransactionInterceptor#TransactionInterceptor(PlatformTransactionManager, TransactionAttributeSource)}
     *
     * @return txAdvice
     */
    @Bean(name = "txAdvice")
    public TransactionInterceptor txAdvice() {
        Properties properties = new Properties();
        properties.setProperty("add**", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("create*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("modify*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("clear*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("remove*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("change*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("submit*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("complete*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("tx*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("syn*", "PROPAGATION_REQUIRED,-Exception,timeout_5");
        properties.setProperty("*", "PROPAGATION_SUPPORTS,-Exception,timeout_5");
        properties.setProperty("get*", "PROPAGATION_SUPPORTS,-Exception,timeout_5");
        properties.setProperty("query*", "PROPAGATION_SUPPORTS,-Exception,timeout_5");
        properties.setProperty("select*", "PROPAGATION_SUPPORTS,-Exception,timeout_5");

        return new TransactionInterceptor(transactionManager, properties);
    }

    /**
     * 方式一
     * 使用BeanName创建tx切点
     *
     * @return creator
     */
    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }

//    /**
//     * 方式二
//     * 使用Advisor创建tx切点
//     * @return advisor
//     */
//    @Bean
//    public Advisor txAdviceAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution (* com.liqingdong.core..*Service*.*(..))");
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//    }
}
