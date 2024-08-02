package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.Alipay.util;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.service.PreOrderService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * @author 林圣涛
 */
public class CommonUntil {


    public static String getUuid() {
        String uuid = "text";
        return uuid;
    }


    /*
    * 获取zfbinfo文件里的值
    * */
    public String getZFBinfoValue(String name) throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/zfbinfo.properties"));
        String filepath = props.getProperty(name);
        return filepath;
    }
}
