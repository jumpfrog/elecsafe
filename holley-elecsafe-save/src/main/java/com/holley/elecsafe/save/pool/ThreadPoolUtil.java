package com.holley.elecsafe.save.pool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.holley.elecsafe.save.App;

public class ThreadPoolUtil {

    private static ThreadPoolExecutor pool = null;

    public static ThreadPoolExecutor getPool() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        if (pool == null) {
            int corePoolSize = Integer.parseInt(App.properties.getProperty("corePoolSize"));
            int maximumPoolSize = Integer.parseInt(App.properties.getProperty("maximumPoolSize"));
            int keepAliveTime = Integer.parseInt(App.properties.getProperty("keepAliveTime"));
            String queueType = App.properties.getProperty("queueType");
            if ("SynchronousQueue".equals(queueType)) {
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
            } else if ("LinkedBlockingQueue".equals(queueType)) {
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
            } else if ("ArrayBlockingQueue".equals(queueType)) {
                int queueSize = Integer.parseInt(App.properties.getProperty("queueSize"));
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));

            }
        }
        return pool;
    }

    public static void execute(Runnable command, String jobName) {

        ThreadPoolExecutor threadPool;
        try {
            threadPool = getPool();
            threadPool.execute(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
