package com.putianhouduan.PhotovoltaicSmartSupplyChain.controller;

import com.alibaba.fastjson.JSONObject;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.Alipay.AlipayFaceToFace;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.Alipay.util.CommonUntil;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.model.ZFBFaceToFaceModel;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林圣涛
 */

@Controller
@RequestMapping("/api/auth/AlipayFaceToFaceController")
public class AlipayFaceToFaceController {

    /*
      支付宝预约下单
      用于接受前端请求 返回给前端二维码地址和商户唯一订单编号
     */
    @RequestMapping(value = "/ZFBPreorderAction" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> ZFBPreorderAction(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try{
            CommonUntil commonUntil = new CommonUntil();
            //（必填） 商户唯一订单编号
            String outTradeNo = CommonUntil.getUuid();
            // (必填) 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
            String subject ="毛毛消费（中国）";
            // (必填) 订单总金额，单位为元，不能超过1亿元
            String totalAmount = "0.01";
            //（必填）支付成功支付支付宝异步通知的接口地址
            String NotifyUrl=commonUntil.getZFBinfoValue("NotifyUrl");
            //将参数放入实体对象中
            ZFBFaceToFaceModel zfbFaceToFaceModel=new ZFBFaceToFaceModel();
            zfbFaceToFaceModel.setOutTradeNo(outTradeNo);
            zfbFaceToFaceModel.setSubject(subject);
            zfbFaceToFaceModel.setTotalAmount(totalAmount);
            zfbFaceToFaceModel.setNotifyUrl(NotifyUrl);
            //支付宝预下单
            String json= AlipayFaceToFace.ZFBPreorder(zfbFaceToFaceModel);
            //解析json数据
            JSONObject jsonObject=JSONObject.parseObject(json);
            //得到alipay_trade_precreate_response数据后再强转JSONObject
            JSONObject jsonobj_two=(JSONObject)jsonObject.get("alipay_trade_precreate_response");
            //再通过jsonobj_two获取到二维码地址
            String qrcode=jsonobj_two.get("qr_code").toString();

            resultMap.put("qrcode",qrcode);
            resultMap.put("outTradeNo",outTradeNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    @RequestMapping("/findZFB_tradeAction")
    @ResponseBody
    public Map<String,Object> findZFB_tradeAction(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        try {
            //(必填)商户唯一订单编号
            String outTradeNo=request.getParameter("outTradeNo");
            ZFBFaceToFaceModel zfbFaceToFaceModel=new ZFBFaceToFaceModel();
            zfbFaceToFaceModel.setOutTradeNo(outTradeNo);
            //查询交易状态
            String json=AlipayFaceToFace.findZFB_trade(zfbFaceToFaceModel);
            System.out.println(json);
            JSONObject jsonObject=JSONObject.parseObject(json);
            JSONObject jsonobj_two=(JSONObject)jsonObject.get("alipay_trade_query_response");
            //网关返回码,详见文档 https://opendocs.alipay.com/open/common/105806
            String ZFBCode=(String)jsonobj_two.get("code");
            //业务返回码
            String ZFBSubCode=(String)jsonobj_two.get("sub_code");
            //业务返回码描述
            String sub_msg=(String)jsonobj_two.get("sub_msg");
            //交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
            String trade_status=(String)jsonobj_two.get("trade_status");
            if (ZFBCode.equals("40004") && ZFBSubCode.equals("ACQ.TRADE_NOT_EXIST")) {
                //订单未创建（用户未扫码）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "用户未扫码");
            } else if (ZFBCode.equals("10000") && trade_status.equals("WAIT_BUYER_PAY")) {
                //订单已经创建但未支付（用户扫码后但是未支付）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "non-payment");
            } else if (ZFBCode.equals("10000") && (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED"))) {
                //判断ZFBCode是否等于”10000“ 并且 trade_status等于TRADE_SUCCESS（交易支付成功）或者 trade_status等于TRADE_FINISHED（交易结束，不可退款）
                //订单已支付（用户扫码完成并且支付成功之后）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "yes-payment");
            } else {
                resultMap.put("code", ZFBCode);
                resultMap.put("data", sub_msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}
