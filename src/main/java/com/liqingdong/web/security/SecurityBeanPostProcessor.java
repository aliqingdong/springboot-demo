package com.liqingdong.web.security;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Component;

/**
 * @author liqingdong
 * @since 2019/01/31 16:03
 */
@Component
public class SecurityBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private CustomAjaxSessionStrategy ajaxSessionStrategy;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SessionManagementFilter)
            ((SessionManagementFilter) bean).setInvalidSessionStrategy(ajaxSessionStrategy);
        if (bean instanceof ConcurrentSessionFilter)
            ((ConcurrentSessionFilter) bean).setRedirectStrategy(ajaxSessionStrategy);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
