package com.holley.elecsafe.save;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {

    public static Properties properties = new Properties();

    public static void main(String[] args) {

        // 加载配置文件
        loadProperties("threadPoolConfig.properties");
        AppContext.init();
        System.out.println("spring bean loaded.");
    }

    static void loadProperties(String file) {
        InputStream is = null;
        is = App.class.getClassLoader().getResourceAsStream(file);
        try {
            properties.load(is);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
