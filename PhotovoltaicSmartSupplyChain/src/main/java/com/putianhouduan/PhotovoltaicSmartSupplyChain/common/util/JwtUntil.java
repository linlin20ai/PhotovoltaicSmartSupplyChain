package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
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

    public Date expireTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,expire*24);
        return calendar.getTime();
    }

    public String creatJwt(UserDetails details,int id, String username){
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date expire = this.expireTime();
        return JWT.create()
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
}
