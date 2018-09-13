package com.holley.elecsafe.save;


import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppContext {

    public static String              DB_SOURCES = "sqlSessionFactory";

    private static ApplicationContext context    = null;
    

    public static void init() {
        String[] locations = { "classpath:spring/spring-*.xml" };
        context = new FileSystemXmlApplicationContext(locations);
    }

    // public static void initTest() {
    // String[] locations = { "conf/spring/applicationContext.xml", "conf/spring/spring-util.xml",
    // "conf/spring/dao/spring-*.xml", "conf/spring/service/spring-*.xml",
    // "conf/spring/job/spring-job.xml" };
    // context = new FileSystemXmlApplicationContext(locations);
    // }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

}
