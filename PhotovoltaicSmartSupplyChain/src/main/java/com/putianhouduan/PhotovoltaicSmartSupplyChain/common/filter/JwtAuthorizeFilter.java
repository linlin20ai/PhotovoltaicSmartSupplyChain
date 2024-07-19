package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util.JwtUntil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 林圣涛
 */
@Component
public class JwtAuthorizeFilter extends OncePerRequestFilter {
    @Resource
    JwtUntil jwtUntil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        DecodedJWT jwt = jwtUntil.resolveJwt(authorization);
        if(jwt != null) {
            UserDetails user = jwtUntil.toUser(jwt);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("id",jwtUntil.toId(jwt));
        }
        filterChain.doFilter(request,response);
    }
}
