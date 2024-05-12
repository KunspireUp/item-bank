package com.itembank.entity.dto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.awt.*;

/**
 * @Description: 创建图片验证码
 * @Author: KunSpireUp
 * @Date: 11/5/2024 下午1:02
 */
public class CreateImageCode {

    // 图片的宽度
    private int width = 160;
    // 图片的高度
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 5;
    // 验证码干扰线条数
    private int lineCount = 20;
    // 验证码
    private String code = null;
    // 验证码图片 Buffer
    private BufferedImage buffImg = null;
    // 生成随机数
    Random random = new Random();

    public CreateImageCode() {
        createImage();
    }

    public CreateImageCode(int width, int height){
        this.width = width;
        this.height = height;
        createImage();

    }

    public CreateImageCode(int width, int height, int codeCount){
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        createImage();

    }

    public CreateImageCode(int width, int height, int codeCount, int lineCount){
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        createImage();
    }

    // 生成图片
    private void createImage(){
        int fontWidth = width / codeCount; // 字体的宽度
        int fontHeight = height - 5;// 字体的高度
        int codeY = height - 8;

        // 图像 buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();

        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设置字体
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f;
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            buffImg.setRGB(x, y, random.nextInt(255));
        }

        String strl = randomStr(codeCount); // 得到随机字符
        this.code = strl;
        for (int i = 0; i < codeCount; i++) {
            String strRand = strl.substring(i, i + 1);
            g.setColor(getRandColor(1,255));

            g.drawString(strRand, i * fontHeight + 3, codeY);
        }
    }

    /**
     * 得到随机字符
     * @param n
     * @return
     */
    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTOPQRSTUVWXYZabcdefghijklmnoprstuvwxyz0123456789";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    /**
     * 得到随机颜色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return  new Color(r, g, b);
    }

    /**
     * 产生随机字体
     * @param size
     * @return
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    /**
     * 扭曲方法
     * @param g
     * @param wl
     * @param hl
     * @param color
     */
    private void shear(Graphics g, int wl, int hl, Color color) {
        shearX(g, wl, hl, color);
        shearY(g, wl, hl, color);
    }

    private void shearX(Graphics g, int wl, int hl, Color color) {
        int period = random.nextInt(2);
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);
        for (int i = 0; i < hl; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, wl, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + wl, i, wl, i);
            }
        }
    }

    private void shearY(Graphics g, int wl, int hl, Color color) {
        int period = random.nextInt(40) + 10; // 50;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < wl; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, hl, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + hl, i, hl);
            }
        }
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }
    public String getCode() {
        return code.toUpperCase();
    }
}
