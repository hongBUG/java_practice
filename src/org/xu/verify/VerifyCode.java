package org.xu.verify;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertPathValidatorException.Reason;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {

	// 设置图片缓冲区的宽高
	private int width = 70;
	private int height = 35; 
	private Random r = new Random(); // 生成随机数字
	private String[] fontNames = {"宋体","华文楷体","黑体","微软雅黑"}; // 创建一个字体数组
	private Color bgColor = new Color(255, 255, 255); // 白色的背景色
	private String codes = "1234567890qwertyuiopasdfghjklzxcvbnm"; // 可提供选择的随机文字
	private String text; // 要在图片上生成的文本
	
	/**
	 * 创建图片缓冲区
	 */
	public BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics g = image.getGraphics();
		StringBuilder sb = new StringBuilder();
		// 循环四次 每次添加一个字符
		for (int i = 0; i < 4; i++) {
			sb.append(randomChar()); // 调用产生随机字符的方法， 随机生成一个字符  并添加到sb后面
			g.setFont(randomFont()); // 调用产生随机字体
			g.setColor(randomColor()); // 调用产生随机颜色方法 生成随机颜色
			g.drawString(sb.toString(), i*width/4, height - 5); // 在图片中绘制文本
		}
		this.text=sb.toString();// 把生成的字符串赋值给文本
		drawLine(image); // 调用添加干扰线的方法对图片中的文本进行干扰
		return image;
	}
	
	/**
	 * 创建图片缓冲区(背景图)
	 */
	public BufferedImage createImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height); // 设置整个图片背景为白色
		return image;
	}
	
	/**
	 * 生成随机字符的方法
	 */
	public char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}
	/**
	 * 生成随机字体
	 */
	public Font randomFont() {
		int index = r.nextInt(fontNames.length);
		int style = r.nextInt(4); // 设置字体样式，0表示无样式，1表示粗体，2表示斜体 3表示粗体加斜体 
		int size = r.nextInt(4) + 24;
		return new Font(fontNames[index], style, size);
	}
	/**
	 * 生成随机颜色
	 */
	public Color randomColor() {
		int red = r.nextInt(255);
		int green = r.nextInt(255);
		int blue = r.nextInt(255);
		return new Color(red, green, blue);
	}
	/**
	 * 生成干扰线
	 */
	public void drawLine(BufferedImage image) {
		// 取画笔 画线  画三条
		Graphics g = image.getGraphics();
		for(int i = 0; i < 3; i++) {
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(width);
			int y2 = r.nextInt(height); // 起点和终点坐标
			g.setColor(Color.yellow); // 
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	/**
	 * 返回验证码内容 用于验证
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * 保存图片到指定的输出流
	 */
	public static void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
}
