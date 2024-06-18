package com.hl.javaee.config;

import com.hl.javaee.mybatis.MyInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huanglin
 * @date 2024/05/26 15:04
 */
@Configuration
public class MybatisConfig {

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true); // 设置驼峰命名
                configuration.addInterceptor(myInterceptor());
            }
        };
    }
}
