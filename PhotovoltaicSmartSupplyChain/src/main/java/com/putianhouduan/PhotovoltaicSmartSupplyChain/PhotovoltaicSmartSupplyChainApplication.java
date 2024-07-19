package com.putianhouduan.PhotovoltaicSmartSupplyChain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 林圣涛
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.putianhouduan.PhotovoltaicSmartSupplyChain.mapper")
public class PhotovoltaicSmartSupplyChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotovoltaicSmartSupplyChainApplication.class, args);
    }

}
