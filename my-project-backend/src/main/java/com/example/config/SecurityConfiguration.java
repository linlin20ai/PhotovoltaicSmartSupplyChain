package com.example.config;

import com.example.entity.RestBean;
import com.example.entity.vo.response.AuthorzeVo;
import com.example.filter.JwtAuthorizeFilter;
import com.example.utils.Jwtutil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author 林圣涛
 */
@Configuration
public class SecurityConfiguration {

        @Resource
        Jwtutil jwtutil;

        @Resource
        JwtAuthorizeFilter jwtAuthorizeFilter;


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                        .authorizeHttpRequests(conf -> conf
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
                        .formLogin(conf -> conf
                                .loginProcessingUrl("/api/auth/login")
                                .failureHandler(this::onAuthenticationFailure)
                                .successHandler(this::onAuthenticationSuccess)
                        )
                        .logout(conf->conf
                                .logoutUrl("/api/auth/logout")
                                .logoutSuccessHandler(this::onLogoutSuccess)
                        )
                        .exceptionHandling(conf->conf
                                .authenticationEntryPoint(this::onUnauthorized)
                                .accessDeniedHandler(this::onAccessDeny)
                        )
                        .csrf(AbstractHttpConfigurer::disable)
                        .sessionManagement(conf->conf
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

        private void onAccessDeny(HttpServletRequest request,
                                  HttpServletResponse response,
                                  AccessDeniedException accessDeniedException) throws IOException {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(RestBean.forbidden(accessDeniedException.getMessage()).asJsonString());
        }

        public void onUnauthorized(HttpServletRequest request,
                                   HttpServletResponse response,
                                   AuthenticationException exception) throws IOException{
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
        }
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException{
                response.setContentType("application/json;charset=utf-8");
                User user = (User) authentication.getPrincipal();
                String token=jwtutil.creatJwt(user,1,"小林");
                AuthorzeVo authorzeVo=new AuthorzeVo();
                authorzeVo.setExpire(jwtutil.expireTime());
                authorzeVo.setToken(token);
                authorzeVo.setUsername("小林");
                authorzeVo.setRole("");
                response.getWriter().write(RestBean.success(authorzeVo).asJsonString());
        }
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException exception) throws IOException {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
        }
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication authentication) throws IOException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer=response.getWriter();
                String authorization=request.getHeader("Authorization");
                if(jwtutil.invalidateJwt(authorization)){
                        writer.write(RestBean.success().asJsonString());
                }else{
                        writer.write(RestBean.failure(400,"退出登录失败").asJsonString());
                }

        }
}
