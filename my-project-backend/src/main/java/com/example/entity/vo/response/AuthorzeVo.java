package com.example.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author 林圣涛
 */
@Data
public class AuthorzeVo {
    String username;
    String role;
    String token;
    Date expire;
}
