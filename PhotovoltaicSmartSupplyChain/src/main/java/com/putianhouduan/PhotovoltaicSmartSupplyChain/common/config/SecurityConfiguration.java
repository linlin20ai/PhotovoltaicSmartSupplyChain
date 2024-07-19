package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.config;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.ResultCode;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.filter.JwtAuthorizeFilter;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util.JwtUntil;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.entity.vo.AuthorzeVo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 林圣涛
 */
@Component
public class SecurityConfiguration {

    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;

    @Resource
    JwtUntil jwtUntil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(conf ->conf
                        .loginProcessingUrl("/api/auth/login")
                        .failureHandler(this::onAuthenticationFailure)
                        .successHandler(this::onAuthenticationSuccess)
                )
                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf->conf
                        .authenticationEntryPoint(this::onUnauthorized)
                        .accessDeniedHandler(this::onAccessDeny)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void onAccessDeny(HttpServletRequest request,
                              HttpServletResponse response,
                              AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(CommonResult.forbidden(accessDeniedException.getMessage()).asJsonString());
    }

    public void onUnauthorized(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException exception) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(CommonResult.unauthorized(exception.getMessage()).asJsonString());
    }


    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        User user= (User) authentication.getPrincipal();
        String token =jwtUntil.creatJwt(user,1,"xiaolin");
        AuthorzeVo authorzeVo = new AuthorzeVo();
        authorzeVo.setExpire(jwtUntil.expireTime());
        authorzeVo.setToken(token);
        authorzeVo.setName("xiaolin");
        authorzeVo.setRole("");
        response.getWriter().write(CommonResult.success(authorzeVo).asJsonString());
    }
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(CommonResult.failed(exception.getMessage()).asJsonString());
    }

    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String authorization=request.getHeader("Authorization");
        if(jwtUntil.invalidateJwt(authorization)){
            writer.write(CommonResult.success(ResultCode.SUCCESS).asJsonString());
        }else{
            writer.write(CommonResult.failed(ResultCode.FAILED,"退出登录失败").asJsonString());
        }
    }

}
