package service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import vo.express;

public class PictureService {
	public static void DrawBackDropPicture() throws Exception// 画该运单图片的背景框架
	{
		// 设置背景图长宽
		int imageWidth = 1000;
		int imageHeight = 1200;

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();//获得图片画笔工具
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(new Color(220, 240, 240));

		graphics.setColor(Color.black);
		graphics.drawRect(0, 0, 900, 1200);//画整体矩形框架
		graphics2d.setColor(Color.black);
		BasicStroke dashed = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f,
				new float[] { 10, 5 }, 0f);
		graphics2d.setStroke(dashed);//获得虚线画笔工具
		graphics.drawLine(0, 1190, 900, 1190);
		int start = 0;
		for (int i = 0; i < 6; i++) {//动态画矩形中分界线
			start = start + 150;
			graphics2d.draw(new Line2D.Double(0, start, 900, start));// (x,y,z,w) x:开始时x坐标；y：开始时y坐标，z：线段长度，：结束时y坐标
		}
		graphics2d.draw(new Line2D.Double(450, 450, 450, 600));
		graphics2d.draw(new Line2D.Double(0, 1050, 600, 1050));
		graphics2d.draw(new Line2D.Double(600, 900, 600, 1200));
		ImageIO.write(image, "jpg", new File("src/BackdropPicture/back.jpg"));
	}

	public static void drawPlannarPicture() {//生产二维码图片，扫描结果是ems官网
		int width = 250;
		int height = 250;
		String contents = "www.ems.com";
		String imgPath = "src/PlannarPicture/plannar.jpg";//图片生成位置
		Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);

			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void drawbarCode(express ex1, String path) throws Exception {//画订单号条形码
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		int width = 100, height = 50;
		String contents = ex1.getOrder();
		codeWidth = Math.max(codeWidth, width);
		//条形码编码为CODE_128格式
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, height, null);

		MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new File(path));
	}

	public static void drawTransPicture(express ex1, String path) throws Exception {//画物流单号二维码
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		int width = 100, height = 50;
		String contents = ex1.getOrder2();
		codeWidth = Math.max(codeWidth, width);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, height, null);

		MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new File(path));
	}

	public static void drawEMSpicture(express ex1, String BackPath, String plannarPath, String BarPath,
			String TransPath, String EMSPath) throws Exception {//画最后生成的快递图片

		BufferedImage img = new BufferedImage(1000, 1200, BufferedImage.TYPE_INT_RGB);//生成一张新的图片
		BufferedImage BarcodePicture = ImageIO.read(new File(BarPath));//获得订单号条形码图
		BufferedImage BackPicture = ImageIO.read(new File(BackPath));//获得背景框架图
		BufferedImage PlannarPicture = ImageIO.read(new File(plannarPath));//获得二维码图片
		BufferedImage TransPicture = ImageIO.read(new File(TransPath));//获得物流单号图片
		Graphics g = img.getGraphics();
		g.drawImage(BackPicture.getScaledInstance(1000, 1200, Image.SCALE_DEFAULT), 0, 0, null);//将背景框架画进图片中
		g.drawImage(TransPicture.getScaledInstance(450, 90, Image.SCALE_DEFAULT), 250, 20, null);//将物流单号条形码画入图片
		g.drawImage(PlannarPicture.getScaledInstance(250, 250, Image.SCALE_DEFAULT), 630, 930, null);//将二维码画入图片
		g.drawImage(BarcodePicture.getScaledInstance(550, 85, Image.SCALE_DEFAULT), 180, 620, null);//将订单号划入图片
		g.drawImage(TransPicture.getScaledInstance(450, 90, Image.SCALE_DEFAULT), 200, 770, null);
		// 画ems
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.BOLD, 75));
		g.drawString("EMS", 70, 80);
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString("e标准", 90, 120);
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString(ex1.getOrder2(), 400, 140);// 画运单号
		g.drawString(ex1.getOrder2(), 350, 890);
		g.drawString(ex1.getOrder(), 300, 740);// 画订单号
		// 画收件人信息
		g.drawString("收件人:" + ex1.getReceName() + " 电话：" + ex1.getReceTele(), 20, 340);
		g.drawString("地址：" + ex1.getReceAdress(), 20, 390);
		g.drawString("邮编：" + ex1.getReceZipCode(), 20, 440);
		// 画内件详情
		g.drawString("内件详情：", 20, 190);
		String product[] = ex1.getProduct().split("克");//因可能产品有多个故动态画，将名称字符串划分，避免画出图片过界
		for (int i = 0; i < product.length; i++) {
			g.drawString(product[i], 160, 190 + i * 40);
		}
		// 画内件重量数量
		g.drawString("内件数量：" + ex1.getCount(), 20, 490);
		g.drawString("总重量：" + ex1.getWeight(), 20, 540);
		// 画签收界面
		g.setFont(new Font("宋体", Font.BOLD, 25));
		g.drawString("收件人/代收件人：", 470, 490);
		g.drawString("签收时间：   年    月     日", 470, 570);
		// 画发件人信息
		g.setFont(new Font("宋体", Font.BOLD, 28));
		g.drawString("发件人:" + ex1.getSenName() + " 电话：" + ex1.getSenTele(), 15, 940);
		g.drawString("地址：" + ex1.getSenAdress(), 15, 990);
		g.drawString("邮编：" + ex1.getSenZipCode(), 15, 1040);
		// 画地下栏收件人信息
		g.setFont(new Font("宋体", Font.BOLD, 22));
		g.drawString("收件人:" + ex1.getReceName() + " 电话：" + ex1.getReceTele(), 12, 1090);
		g.drawString("地址：" + ex1.getReceAdress(), 12, 1140);
		g.drawString("邮编：" + ex1.getReceZipCode(), 12, 1180);
		// 画二维码下方网址即联系电话：
		g.setFont(new Font("宋体", Font.BOLD, 26));
		g.drawString("www.ems.com.cn", 650, 1160);
		g.drawString("2017 电话：11183", 650, 1185);
		/// *******
		g.setFont(new Font("宋体", Font.BOLD, 26));
		g.drawString("订单号", 50, 635);
		// 画陆运信息
		g.setFont(new Font("宋体", Font.BOLD, 80));
		g.drawString("B-C", 700, 850);
		g.setFont(new Font("宋体", Font.BOLD, 50));
		g.drawString("全程", 300, 510);
		g.drawString("陆运", 300, 570);
		ImageIO.write(img, "jpg", new File(EMSPath));
		
	}
}
