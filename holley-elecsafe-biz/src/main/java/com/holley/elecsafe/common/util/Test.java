package com.holley.elecsafe.common.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.holley.platform.common.util.DateUtil;

public class Test {

    private String str;

    @org.junit.Test
    public void tt() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(111);
                        // System.out.println(str.getBytes());
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        long s = 1504665909000l;
        Date d = new Date();
        d.setTime(s);
        System.out.println(DateUtil.DateToLongStr(d));
    }
}
