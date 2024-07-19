package com.putianhouduan.PhotovoltaicSmartSupplyChain.common.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 林圣涛
 */
@Component
@RabbitListener(queues = "mail")
public class MailQueueListener {

    @Resource
    JavaMailSender sender;

    @Value("${spring.mail.username}")
    String username;

    @RabbitHandler
    public void sendMailMessage(Map<String, Object> data){
        String email=(String) data.get("email");
        Integer code=(Integer) data.get("code");
        String type = (String) data.get("type");
        SimpleMailMessage message;
        switch (type) {
            case "register":
                message = createMessage(
                        "欢迎注册光伏智慧管理系统",
                        "您的邮件注册验证码为" + code + "，有效时间为3分钟，为了保障您的安全，请勿向他人透露验证码信息",
                        email
                );
                break;
            case "reset":
                message = createMessage(
                        "您好！这是光伏智慧管理系统账户的密码重置验证邮件",
                        "您的账号重置密码验证码为" + code + "，有效时间为3分钟，为了保障您的安全，请勿向他人透露验证码信息",
                        email
                );
                break;
            default:
                message = createMessage(
                        "您好这是光伏智慧系统的邮箱",
                        code + " hello world",
                        email
                );
                break;
        }
        
        sender.send(message);
    }

    private SimpleMailMessage createMessage(String title ,String content , String  email){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(email);
        message.setFrom(username);
        return message;
    }

}
