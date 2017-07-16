package com.comp.web.controller.kaptcha;

import java.awt.image.BufferedImage;  
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;   

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;  

import com.base.entity.Entity;
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
@RequestMapping("/captcha")  
public class CaptchaController {  
    @Autowired  
    private Producer captchaProducer;
    @RequestMapping(value = "captchaimage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	response.setDateHeader("Expires", 0); 
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header. 
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText = captchaProducer.createText();  
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
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
    
    /**
     *             
     *                前端输入的验证码与生成的对比
     * @author         ccg
     * @param         request
     * @param         response
     * Created        2017年1月17日 下午5:34:23
     */
    
    @RequestMapping("checkCaptchaCode")
    @ResponseBody
    public Entity checkCaptchaCode(HttpServletRequest request, HttpServletResponse response){
    	String captchaCode = request.getParameter("captchaCode");
    	System.out.println("页面输入验证码===="+captchaCode);
        String generateCode =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Entity entity = new Entity();
        if(generateCode.equals(captchaCode)){
        	entity.setMessage("验证码正确");
        	entity.setStatus("0");
        }else{
        	entity.setMessage("验证码错误");
        	entity.setStatus("1");
        }
        return entity;
    }
}