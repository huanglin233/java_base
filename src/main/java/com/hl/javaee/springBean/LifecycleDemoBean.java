package com.hl.javaee.springBean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author huanglin
 * @date 2024/05/19 23:24
 */
public class LifecycleDemoBean implements InitializingBean, DisposableBean {


    /**
     * 使用@Value注解注入属性值
     */
    @Value("${lifecycle.demo.bean.name: default name}")
    private String name;

    // 构造方法,再bean实例化时调用
    public LifecycleDemoBean() {
        System.out.println("LifecycleDemoBean构造方法: 实例化");
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
