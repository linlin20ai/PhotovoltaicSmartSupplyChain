package com.example.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.utils.Jwtutil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author 林圣涛
 */
@Component
public class JwtAuthorizeFilter extends OncePerRequestFilter {

    @Resource
    Jwtutil jwtutil;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization=request.getHeader("Authorization");
        DecodedJWT jwt = jwtutil.resolveJwt(authorization);
        if(jwt!=null){
            UserDetails user=jwtutil.toUser(jwt);
            UsernamePasswordAuthenticationToken authentication=
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.setAttribute("id",jwtutil.toId(jwt));
        }
        filterChain.doFilter(request,response);
    }
}
