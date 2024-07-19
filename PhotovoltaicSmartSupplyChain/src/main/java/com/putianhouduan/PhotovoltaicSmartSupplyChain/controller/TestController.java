package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林圣涛
 */
@RestController
@RequestMapping("/api/text")
public class TestController {
    @GetMapping("/hello")
    public String test(){
        return "Hello World";
    }
}
