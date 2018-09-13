package com.holley.elecsafe.save.util;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.platform.common.cache.RedisUtil;

public class ClearRedis {

    public static void main(String[] args) {
        RedisUtil.delKey(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L1_REAL.getBytes());
        RedisUtil.delKey(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L2_REAL.getBytes());
        RedisUtil.delKey(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_EVENT.getBytes());
        RedisUtil.delKey(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS.getBytes());
    }

}
