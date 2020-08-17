package com.xander.mdblog.util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 验证码工具类
 *
 * @author 叶振东
 * @date 2018-11-20
 */
public class VerifyCodeUtils {

    public static String generateVerifyCode(int codeCount) {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder builderCode = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < codeCount; i++) {
            char c = codeSequence[r.nextInt(codeSequence.length)];
            builderCode.append(c);
        }
        return builderCode.toString();
    }

    /**
     * 生成验证码BufferedImage
     *
     * @param width
     * @param height
     * @param code
     * @return java.awt.image.BufferedImage
     * @author yezhendong
     * @date 2019/9/11
     */
    public static BufferedImage generateImage(int width, int height, String code) {
        int lineCount = 19;//混淆线个数  默认值：19
        int fontSize = 20;//字体大小像素
        //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        char[] c = code.toCharArray();
        int codeCount = c.length;
        //定义画布
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = buffImg.getGraphics();
        //1.设置颜色,画边框
        g.setColor(Color.gray);
        g.drawRect(0, 0, width, height);
        //2.设置颜色,填充内部
        g.setColor(Color.white);
        g.fillRect(1, 1, width - 2, height - 2);
        //3.设置干扰线
        // g.setColor(Color.gray);
        for (int i = 0; i < lineCount; i++) {
            int _R = (int) Math.floor(Math.random() * 256);
            int _G = (int) Math.floor(Math.random() * 256);
            int _B = (int) Math.floor(Math.random() * 256);
            g.setColor(new Color(_R, _G, _B, 255));
            g.drawLine(r.nextInt(width), r.nextInt(width), r.nextInt(width), r.nextInt(width));
        }
        //4.设置验证码
        g.setColor(Color.blue);
        //4.1设置验证码字体
        g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, fontSize));
        for (int i = 0; i < codeCount; i++) {
            g.drawString(c[i] + "", ((width / codeCount) * i + 2), height * 4 / 5);
        }
        return buffImg;
    }

    /**
     * 具体获取验证码的方法
     *
     * @param width
     * @param height
     * @param sos
     * @param code
     */
    public static void outputImage(int width, int height, ServletOutputStream sos, String code) {
        BufferedImage buffImg = generateImage(width, height, code);
        try {
            //5.输出到屏幕
            ImageIO.write(buffImg, "png", sos);
            //8.关闭sos
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成base64验证码
     *
     * @param width
     * @param height
     * @param code
     */
    public static String imageBase64(int width, int height, String code) {
        BufferedImage buffImg = generateImage(width, height, code);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(buffImg, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encodeBuffer(bytes).trim();
        base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
        return base64;

    }
}
