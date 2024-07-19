package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 林圣涛
 */
@Component
public class FlowUtils {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public boolean limitOnceCheck(String key, int blockTime){
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))){
            return false;
        }else {
            stringRedisTemplate.opsForValue().set(key,"",blockTime, TimeUnit.SECONDS);
            return true;
        }
    }
}
