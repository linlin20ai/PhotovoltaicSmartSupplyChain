package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 林圣涛
 */
@Component
public class JwtUntil {

    @Value("${spring.security.jwt.key}")
    String key;
    @Value("${spring.security.jwt.expire}")
    int expire;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public Date expireTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,expire*24);
        return calendar.getTime();
    }

    public String creatJwt(UserDetails details,int id, String username){
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date expire = this.expireTime();
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("id",id)
                .withClaim("username",username)
                .withClaim("authorities",details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(expire)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }


    public DecodedJWT resolveJwt(String headerToken){
        String token = this.convertToken(headerToken);
        if(token==null) {
            return null;
        }
        Algorithm algorithm=Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        try{
            DecodedJWT verify=jwtVerifier.verify(token);
            if(this.isInvalidToken(verify.getId())){
                return null;
            }
            Date expiresAt = verify.getExpiresAt();
            return new Date().after(expiresAt)?null:verify;
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private String convertToken(String headerToken){
        if(headerToken== null || !headerToken.startsWith("Bearer ")) {
            return null;
        }
        return headerToken.substring(7);
    }

    public UserDetails toUser(DecodedJWT jwt){
        Map<String, Claim> claims=jwt.getClaims();
        return  User
                .withUsername(claims.get("username").asString())
                .password("1234")
                .authorities(claims.get("authorities").asArray(String.class))
                .build();
    }

    public Integer toId(DecodedJWT jwt){
        Map<String, Claim> claims=jwt.getClaims();
        return claims.get("id").asInt();
    }

    public boolean invalidateJwt(String headerToken){
        String token=this.convertToken(headerToken);
        if(token == null) {
            return false;
        }
        Algorithm algorithm=Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        try{
            DecodedJWT jwt=jwtVerifier.verify(token);
            String id = jwt.getId();
            return deleteToken(id,jwt.getExpiresAt());
        }catch (JWTVerificationException exception){
            return false;
        }
    }

    public boolean deleteToken(String uuid,Date time){
        if(this.isInvalidToken(uuid)){
            return false;
        }
        Date now=new Date();
        long expire=Math.max(time.getTime()-now.getTime(),0);
        stringRedisTemplate.opsForValue().set(Const.JWT_BLACK_LIST+uuid,"",expire, TimeUnit.MILLISECONDS);
        return true;
    }

    public boolean isInvalidToken(String uuid){
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(Const.JWT_BLACK_LIST + uuid));
    }
}
