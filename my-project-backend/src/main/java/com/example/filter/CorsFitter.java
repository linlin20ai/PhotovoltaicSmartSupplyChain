package com.example.filter;

import com.example.utils.Const;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author 林圣涛
 */
@Component
@Order(Const.ORDER_CORS)
public class CorsFitter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        this.addCorsHeader(request,response);
        chain.doFilter(request,response);
    }


    public void addCorsHeader(HttpServletRequest request, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers","Authorization,Content-Type");
    }

}
