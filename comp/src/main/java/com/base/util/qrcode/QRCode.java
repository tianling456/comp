package com.base.util.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 *项目名：
 *创建时间：2017-7-9
 *创建人：Aobo
 *类名：TwoDimensionCodeImage
 *所属包名：com.base.util.qrcode
 *项目名称：comp
 *类功能描述：
 */
public class QRCode implements QRCodeImage{
	BufferedImage bufImg;  
    
    public QRCode(BufferedImage bufImg) {  
        this.bufImg = bufImg;  
    }  
      
    @Override  
    public int getHeight() {  
        return bufImg.getHeight();  
    }  
  
    @Override  
    public int getPixel(int x, int y) {  
        return bufImg.getRGB(x, y);  
    }  
  
    @Override  
    public int getWidth() {  
        return bufImg.getWidth();  
    } 
}

