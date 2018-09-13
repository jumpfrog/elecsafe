package com.holley.elecsafe.dcserver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSupport {

    public static ApplicationContext springHandle = null;

    public static void initHandle() {
        if (springHandle == null) {

            // 初始化Spring
            String[] locations = { "classpath:spring/spring-*.xml" };
            springHandle = new ClassPathXmlApplicationContext(locations);
        }
    }
}
