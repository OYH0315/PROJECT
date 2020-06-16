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
	public static void DrawBackDropPicture() throws Exception// �����˵�ͼƬ�ı������
	{
		// ���ñ���ͼ����
		int imageWidth = 1000;
		int imageHeight = 1200;

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();//���ͼƬ���ʹ���
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(new Color(220, 240, 240));

		graphics.setColor(Color.black);
		graphics.drawRect(0, 0, 900, 1200);//��������ο��
		graphics2d.setColor(Color.black);
		BasicStroke dashed = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f,
				new float[] { 10, 5 }, 0f);
		graphics2d.setStroke(dashed);//������߻��ʹ���
		graphics.drawLine(0, 1190, 900, 1190);
		int start = 0;
		for (int i = 0; i < 6; i++) {//��̬�������зֽ���
			start = start + 150;
			graphics2d.draw(new Line2D.Double(0, start, 900, start));// (x,y,z,w) x:��ʼʱx���ꣻy����ʼʱy���꣬z���߶γ��ȣ�������ʱy����
		}
		graphics2d.draw(new Line2D.Double(450, 450, 450, 600));
		graphics2d.draw(new Line2D.Double(0, 1050, 600, 1050));
		graphics2d.draw(new Line2D.Double(600, 900, 600, 1200));
		ImageIO.write(image, "jpg", new File("src/BackdropPicture/back.jpg"));
	}

	public static void drawPlannarPicture() {//������ά��ͼƬ��ɨ������ems����
		int width = 250;
		int height = 250;
		String contents = "www.ems.com";
		String imgPath = "src/PlannarPicture/plannar.jpg";//ͼƬ����λ��
		Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
		// ָ������ȼ�
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// ָ�������ʽ
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);

			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void drawbarCode(express ex1, String path) throws Exception {//��������������
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		int width = 100, height = 50;
		String contents = ex1.getOrder();
		codeWidth = Math.max(codeWidth, width);
		//���������ΪCODE_128��ʽ
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, height, null);

		MatrixToImageWriter.writeToFile(bitMatrix, "jpg", new File(path));
	}

	public static void drawTransPicture(express ex1, String path) throws Exception {//���������Ŷ�ά��
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
			String TransPath, String EMSPath) throws Exception {//��������ɵĿ��ͼƬ

		BufferedImage img = new BufferedImage(1000, 1200, BufferedImage.TYPE_INT_RGB);//����һ���µ�ͼƬ
		BufferedImage BarcodePicture = ImageIO.read(new File(BarPath));//��ö�����������ͼ
		BufferedImage BackPicture = ImageIO.read(new File(BackPath));//��ñ������ͼ
		BufferedImage PlannarPicture = ImageIO.read(new File(plannarPath));//��ö�ά��ͼƬ
		BufferedImage TransPicture = ImageIO.read(new File(TransPath));//�����������ͼƬ
		Graphics g = img.getGraphics();
		g.drawImage(BackPicture.getScaledInstance(1000, 1200, Image.SCALE_DEFAULT), 0, 0, null);//��������ܻ���ͼƬ��
		g.drawImage(TransPicture.getScaledInstance(450, 90, Image.SCALE_DEFAULT), 250, 20, null);//���������������뻭��ͼƬ
		g.drawImage(PlannarPicture.getScaledInstance(250, 250, Image.SCALE_DEFAULT), 630, 930, null);//����ά�뻭��ͼƬ
		g.drawImage(BarcodePicture.getScaledInstance(550, 85, Image.SCALE_DEFAULT), 180, 620, null);//�������Ż���ͼƬ
		g.drawImage(TransPicture.getScaledInstance(450, 90, Image.SCALE_DEFAULT), 200, 770, null);
		// ��ems
		g.setColor(Color.black);
		g.setFont(new Font("����", Font.BOLD, 75));
		g.drawString("EMS", 70, 80);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("e��׼", 90, 120);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString(ex1.getOrder2(), 400, 140);// ���˵���
		g.drawString(ex1.getOrder2(), 350, 890);
		g.drawString(ex1.getOrder(), 300, 740);// ��������
		// ���ռ�����Ϣ
		g.drawString("�ռ���:" + ex1.getReceName() + " �绰��" + ex1.getReceTele(), 20, 340);
		g.drawString("��ַ��" + ex1.getReceAdress(), 20, 390);
		g.drawString("�ʱࣺ" + ex1.getReceZipCode(), 20, 440);
		// ���ڼ�����
		g.drawString("�ڼ����飺", 20, 190);
		String product[] = ex1.getProduct().split("��");//����ܲ�Ʒ�ж���ʶ�̬�����������ַ������֣����⻭��ͼƬ����
		for (int i = 0; i < product.length; i++) {
			g.drawString(product[i], 160, 190 + i * 40);
		}
		// ���ڼ���������
		g.drawString("�ڼ�������" + ex1.getCount(), 20, 490);
		g.drawString("��������" + ex1.getWeight(), 20, 540);
		// ��ǩ�ս���
		g.setFont(new Font("����", Font.BOLD, 25));
		g.drawString("�ռ���/���ռ��ˣ�", 470, 490);
		g.drawString("ǩ��ʱ�䣺   ��    ��     ��", 470, 570);
		// ����������Ϣ
		g.setFont(new Font("����", Font.BOLD, 28));
		g.drawString("������:" + ex1.getSenName() + " �绰��" + ex1.getSenTele(), 15, 940);
		g.drawString("��ַ��" + ex1.getSenAdress(), 15, 990);
		g.drawString("�ʱࣺ" + ex1.getSenZipCode(), 15, 1040);
		// ���������ռ�����Ϣ
		g.setFont(new Font("����", Font.BOLD, 22));
		g.drawString("�ռ���:" + ex1.getReceName() + " �绰��" + ex1.getReceTele(), 12, 1090);
		g.drawString("��ַ��" + ex1.getReceAdress(), 12, 1140);
		g.drawString("�ʱࣺ" + ex1.getReceZipCode(), 12, 1180);
		// ����ά���·���ַ����ϵ�绰��
		g.setFont(new Font("����", Font.BOLD, 26));
		g.drawString("www.ems.com.cn", 650, 1160);
		g.drawString("2017 �绰��11183", 650, 1185);
		/// *******
		g.setFont(new Font("����", Font.BOLD, 26));
		g.drawString("������", 50, 635);
		// ��½����Ϣ
		g.setFont(new Font("����", Font.BOLD, 80));
		g.drawString("B-C", 700, 850);
		g.setFont(new Font("����", Font.BOLD, 50));
		g.drawString("ȫ��", 300, 510);
		g.drawString("½��", 300, 570);
		ImageIO.write(img, "jpg", new File(EMSPath));
		
	}
}
