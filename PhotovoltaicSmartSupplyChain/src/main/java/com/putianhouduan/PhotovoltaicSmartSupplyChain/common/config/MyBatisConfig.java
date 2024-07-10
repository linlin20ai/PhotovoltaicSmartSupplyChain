package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: adlx
 * @Description: MyBatis相关配置
 * @DateTime: 2024/7/10 20:25
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.putianhouduan.PhotovoltaicSmartSupplyChain.dao")
public class MyBatisConfig {
}
