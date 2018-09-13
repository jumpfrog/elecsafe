package com.holley.elecsafe.bqdevice;

public class BQFactory {

    public static BQData createBMW(int type, byte[] buf, int from) {
        switch (type) {

            case 2:
                return new BQ100(buf, from);
            case 1:
                return new BQ200(buf, from);
            case 4:
                return new BQ300(buf, from);
        }
        return null;
    }
}
