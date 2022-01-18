package com.example.demo.common.controller;

import com.example.demo.common.support.RedisKit;
import com.example.demo.common.support.StrKit;
import com.example.demo.common.vo.CaptchaVO;
import com.example.demo.common.vo.ResultVo;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author banyue
 * date 2020-06-07
 */
@RestController
@Slf4j
public class CommonController extends BaseController {

    @Autowired
    public Producer producer = null;

    @Autowired
    private RedisKit redisKit;


    @GetMapping("/captcha")
    @ResponseBody
    public ResultVo captcha(HttpServletRequest request, HttpServletResponse response){
        CaptchaVO vo = new CaptchaVO();
        try{
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            HttpSession session = request.getSession();
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            String capText = producer.createText();
            if(!StrKit.isBlank(capText)){
                session.setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
                String key = StrKit.getRandomUUID();
                //验证码一分钟有效
                redisKit.set(key,capText,1);
                BufferedImage bi = producer.createImage(capText);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bi, "jpg", baos);
                byte[] bytes = baos.toByteArray();
                BASE64Encoder encoder = new BASE64Encoder();
                String pic = encoder.encodeBuffer(bytes).trim();
                vo.setCaptchaPic(pic);
                vo.setCodeKey(key);
                vo.setValidity(true);
                return ok("获取动态验证码成功",vo);
            }
        }catch(Exception e){
            log.error("获取动态验证码图片异常",e);
            return error("服务异常，请稍后再试");
        }
        return error("获取动态验证码失败，请稍后再试");
    }

}
