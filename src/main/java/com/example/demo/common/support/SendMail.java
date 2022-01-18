package com.example.demo.common.support;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件重新开一个线程,提高业务效率.
 * @author banyue
 * date 2020-04-09
 */
public class SendMail {

    public static boolean sendMailForText(String content,String subject,final String sendMailer, final String password,String receiveMailer,String mailHost){
        // 第一步： 创建Session
        Properties props = new Properties();
        // smtp: Smiple Mail Transport Protocol(简单的邮件传输协议)
        props.put("mail.transport.protocol", "smtp");
        // mail.host: 指定邮件服务器
        props.put("mail.host", mailHost);
        // mail.from: 指定邮件的发送人
        props.put("mail.from", sendMailer);
        Session session = Session.getDefaultInstance(props);
        // 调试模式
        session.setDebug(true);
        try {
            // 第二步：创建Transport运输员
            Transport transport = session.getTransport();
            // 用 用户名与密码登录邮件服务器
            transport.connect(sendMailer, password);
            // 第三步：创建Message(信封) 邮件的消息体
            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject); // 邮件的主题
            message.setContent(content, "text/html;charset=utf-8");

            // 第四步：送信
            transport.sendMessage(message, InternetAddress.parse(receiveMailer));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
