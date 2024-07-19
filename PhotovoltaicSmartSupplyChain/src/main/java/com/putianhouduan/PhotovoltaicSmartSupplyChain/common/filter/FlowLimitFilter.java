package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.filter;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util.Const;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 林圣涛
 */
@Component
@Order(Const.ORDER_LIMIT)
public class FlowLimitFilter extends HttpFilter {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String address=request.getRemoteAddr();
        if(this.tryCount(address)){
            chain.doFilter(request,response);
        }else {
            this.writeBlockMessage(response);
        }

    }

    private void writeBlockMessage(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(CommonResult.forbidden("操作频繁，请稍后再试").asJsonString());
    }

    private boolean tryCount(String ip){
        synchronized (ip.intern()){
            if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(Const.FLOW_LIMIT_BLOCK + ip))){
                return false;
            }
            return this.limiterPeriodCheck(ip);
        }
    }

    private boolean limiterPeriodCheck(String ip){
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(Const.FLOW_LIMIT_COUNTER + ip))) {
            long increment = Optional.ofNullable(stringRedisTemplate.opsForValue().increment(Const.FLOW_LIMIT_COUNTER + ip)).orElse(0L) ;
            if(increment>10){
                stringRedisTemplate.opsForValue().set(Const.FLOW_LIMIT_BLOCK+ip,"", 30, TimeUnit.SECONDS);
                return false;
            }
        }else {
            stringRedisTemplate.opsForValue().set(Const.FLOW_LIMIT_COUNTER+ip,"1",5, TimeUnit.SECONDS);
        }
        return true;
    }
}

