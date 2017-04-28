package com.comp.web.controller.kaptcha;

import java.awt.image.BufferedImage;  
import javax.imageio.ImageIO;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;   
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
import com.google.code.kaptcha.Constants;  
import com.google.code.kaptcha.Producer;  
 
/**
 *项目名：
 *创建时间：2017-3-19
 *创建人：Aobo
 *类名：CaptchaController
 *所属包名：com.comp.web.controller.kaptcha
 *项目名称：comp
 *类功能描述：
 */
@Controller  
@RequestMapping("/code")  
public class CaptchaController {  
      
    @Autowired  
    private Producer captchaProducer;  
    @RequestMapping(value = "captcha-image")  
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        HttpSession session = request.getSession();  
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
        System.out.println("验证码: " + code );
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText = captchaProducer.createText();  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
}
