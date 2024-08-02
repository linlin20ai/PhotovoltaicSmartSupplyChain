package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.Alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.Alipay.util.CommonUntil;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.model.ZFBFaceToFaceModel;

import java.io.IOException;

/**
 * 支付宝面对面支付
 * @author 林圣涛
 * 下方代码为预下单，main函数调用一下这个方法就可以运行了，调用此方法后支付宝会返回一个json格式的字符串里面包含二维码地址，然后可以利用二维码生成器 生成二维码然后就可以使用支付宝付款了
 */
public class AlipayFaceToFace {
    /*public static void main(String[] args) throws IOException {
        CommonUntil commonUntil = new CommonUntil();
        *//** 支付宝网关**//*
        String URL = commonUntil.getZFBinfoValue("open_api_domain");

        *//** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **//*
        String APP_ID = commonUntil.getZFBinfoValue("appid");

        *//** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **//*
        String APP_PRIVATE_KEY = commonUntil.getZFBinfoValue("private_key");

        *//** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **//*
        String ALIPAY_PUBLIC_KEY =commonUntil.getZFBinfoValue("alipay_public_key");

        *//** 初始化 **//*
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY,"RSA2");

        *//** 实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.precreate（统一收单线下交易预创建（扫码支付）） **//*
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();

        *//** 设置业务参数  **//*
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();

        *//** 商户订单号，商户自定义，需保证在商户端不重复，如：20200612000001 **//*
        model.setOutTradeNo("20200612000005");

        *//**订单标题 **//*
        model.setSubject("毛毛测试");

        *//** 订单金额，精确到小数点后两位 **//*
        model.setTotalAmount("0.01");

        *//** 订单描述 **//*
        model.setBody("");

        *//** 业务扩展参数 **//*
        //ExtendParams extendParams = new ExtendParams();

        *//** 系统商编号，填写服务商的PID用于获取返佣，返佣参数传值前提：传值账号需要签约返佣协议，用于isv商户。 **//*
        //extendParams.setSysServiceProviderId("2088511****07846");

        *//** 花呗分期参数传值前提：必须有该接口花呗收款准入条件，且需签约花呗分期 **//*
        *//** 指定可选期数，只支持3/6/12期，还款期数越长手续费越高 **//*
        // extendParams.setHbFqNum("3");

        *//** 指定花呗分期手续费承担方式，手续费可以由用户全承担（该值为0），也可以商户全承担（该值为100），但不可以共同承担，即不可取0和100外的其他值。 			**//*
        //extendParams.setHbFqSellerPercent("0");

        //model.setExtendParams(extendParams);

        *//** 将业务参数传至request中 **//*
        request.setBizModel(model);

        *//** 异步通知地址，以http或者https开头的，商户外网可以post访问的异步地址，用于接收支付宝返回的支付结果，如果未收到该通知可参考该文档进行确认：	https://opensupport.alipay.com/support/helpcenter/193/201602475759 **//*
        request.setNotifyUrl("");

        *//**第三方调用（服务商模式），传值app_auth_token后，会收款至授权app_auth_token对应商家账号，如何获传值app_auth_token请参考文档：	https://opensupport.alipay.com/support/helpcenter/79/201602494631 **//*
        //request.putOtherTextParam("app_auth_token", "传入获取到的app_auth_token值");

        AlipayTradePrecreateResponse response = null;
        try {

            *//** 通过alipayClient调用API，获得对应的response类  **//*
            response = alipayClient.execute(request);

        } catch (AlipayApiException e) {

            e.printStackTrace();
        }

        *//** 获取接口调用结果，如果调用失败，可根据返回错误信息到该文档寻找排查方案：https://opensupport.alipay.com/support/helpcenter/101 **//*
        System.out.println(response.getBody());

    }*/

    /*
    * 支付宝预下单
    * */
    public static String ZFBPreorder(ZFBFaceToFaceModel zfbFaceToFaceModel) {
        try {
            CommonUntil commonUtils = new CommonUntil();
            /** 支付宝网关 **/
            String URL = commonUtils.getZFBinfoValue("open_api_domain");

            /** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **/
            String APP_ID = commonUtils.getZFBinfoValue("appid");

            /** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **/
            String APP_PRIVATE_KEY = commonUtils.getZFBinfoValue("private_key");

            /** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **/
            String ALIPAY_PUBLIC_KEY = commonUtils.getZFBinfoValue("alipay_public_key");

            /** 初始化 **/
            AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");

            /** 实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.precreate（统一收单线下交易预创建（扫码支付）） **/
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();

            /** 设置业务参数  **/
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();

            /** 商户订单号，商户自定义，需保证在商户端不重复，如：20200612000001 **/
            model.setOutTradeNo(zfbFaceToFaceModel.getOutTradeNo());

            /**订单标题 **/
            model.setSubject(zfbFaceToFaceModel.getSubject());

            /** 订单金额，精确到小数点后两位 **/
            model.setTotalAmount(zfbFaceToFaceModel.getTotalAmount());

            /** 订单描述 **/
            model.setBody(zfbFaceToFaceModel.getBody());

            /** 业务扩展参数 **/
            //ExtendParams extendParams = new ExtendParams();

            /** 系统商编号，填写服务商的PID用于获取返佣，返佣参数传值前提：传值账号需要签约返佣协议，用于isv商户。 **/
            //extendParams.setSysServiceProviderId("2088511****07846");

            /** 花呗分期参数传值前提：必须有该接口花呗收款准入条件，且需签约花呗分期 **/
            /** 指定可选期数，只支持3/6/12期，还款期数越长手续费越高 **/
            // extendParams.setHbFqNum("3");

            /** 指定花呗分期手续费承担方式，手续费可以由用户全承担（该值为0），也可以商户全承担（该值为100），但不可以共同承担，即不可取0和100外的其他值。 **/
            //extendParams.setHbFqSellerPercent("0");

            //model.setExtendParams(extendParams);

            /** 将业务参数传至request中 **/
            request.setBizModel(model);

            /** 异步通知地址，以http或者https开头的，商户外网可以post访问的异步地址，用于接收支付宝返回的支付结果，如果未收到该通知可参考该文档进行确认：https://opensupport.alipay.com/support/helpcenter/193/201602475759 **/
            request.setNotifyUrl(zfbFaceToFaceModel.getNotifyUrl());

            /**第三方调用（服务商模式），传值app_auth_token后，会收款至授权app_auth_token对应商家账号，如何获传值app_auth_token请参考文档：https://opensupport.alipay.com/support/helpcenter/79/201602494631 **/
            //request.putOtherTextParam("app_auth_token", "传入获取到的app_auth_token值");

            /** 通过alipayClient调用API，获得对应的response类  **/
            AlipayTradePrecreateResponse response = alipayClient.execute(request);

            /** 获取接口调用结果，如果调用失败，可根据返回错误信息到该文档寻找排查方案：https://opensupport.alipay.com/support/helpcenter/101 **/
            System.out.println(response.getBody());
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String findZFB_trade(ZFBFaceToFaceModel zfbFaceToFaceModel) throws Exception{
        CommonUntil commonUtils = new CommonUntil();
        /** 支付宝网关 **/
        String URL = commonUtils.getZFBinfoValue("open_api_domain");

        /** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **/
        String APP_ID = commonUtils.getZFBinfoValue("appid");

        /** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **/
        String APP_PRIVATE_KEY = commonUtils.getZFBinfoValue("private_key");

        /** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **/
        String ALIPAY_PUBLIC_KEY = commonUtils.getZFBinfoValue("alipay_public_key");

        /** 初始化 **/
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY,"RSA2");

        /** 实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.query（统一收单线下交易查询） **/
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        /** 设置业务参数 **/
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();

        /** 注：交易号（TradeNo）与订单号（OutTradeNo）二选一传入即可，如果2个同时传入，则以交易号为准 **/
        /** 支付接口传入的商户订单号。如：2020061601290011200000140004 **/
        model.setOutTradeNo(zfbFaceToFaceModel.getOutTradeNo());

        /** 异步通知/查询接口返回的支付宝交易号，如：2020061622001473951448314322 **/
        //model.setTradeNo("2020061622001473951448314322");

        /** 将业务参数传至request中 **/
        request.setBizModel(model);

        /** 第三方调用（服务商模式），必须传值与支付接口相同的app_auth_token **/
        //request.putOtherTextParam("app_auth_token", "传入获取到的app_auth_token值");

        /** 通过alipayClient调用API，获得对应的response类  **/
        AlipayTradeQueryResponse response = alipayClient.execute(request);

        /** 获取接口调用结果，如果调用失败，可根据返回错误信息到该文档寻找排查方案：https://opensupport.alipay.com/support/helpcenter/101 **/
        return response.getBody();
    }
}
