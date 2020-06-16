package Driver;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Sides;

import com.itextpdf.text.xml.simpleparser.IanaEncodings;

import jxl.read.biff.File;
import service.Excelservice;
import service.PDFservice;
import service.PictureService;
import vo.express;

public class driver {
	public static void main(String[] args) throws Exception {
		meanu();
		Scanner input = new Scanner(System.in);
		int choice;
		ArrayList<express> explist = new ArrayList<express>();
		while ((choice = input.nextInt()) != 0) {
			switch (choice) {
			case 1:
				explist = Excelservice.getinformatio();// ��������˵���Ϣ
				System.out.println("����ȡ��" + explist.size() + "����Ϣ");
				break;
			case 2:
				if (explist.size() == 0)// �ж��Ƿ��ȡ����Ϣ
				{
					System.out.println("����û��ȡ��Ϣ����������");
					break;
				} else {
					for (int i = 0; i < explist.size(); i++) {
						System.out.println(explist.get(i).toString());
					}
					break;
				}
			case 3:
				if (explist.size() == 0)// �ж��Ƿ��ȡ����Ϣ
				{
					System.out.println("����û��ȡ��Ϣ����������");
					break;
				} else {
					PictureService.drawPlannarPicture();// ����ά��
					PictureService.DrawBackDropPicture();// ������ͼ
					// ��̬���ɶ�����ͼƬ
					String barPath = "src/BarcodePicture/";
					int flag = 1;// ����ļ�λ�ã���̬���ɲ�����
					for (int i = 0; i < explist.size(); i++) {
						express ex1 = explist.get(i);
						String path = barPath + String.valueOf(flag) + ".jpg";
						PictureService.drawbarCode(ex1, path);
						flag++;
					}
					// ��̬������������������ͼƬ
					int flag2 = 1;// ����ļ�λ�ã���̬���ɲ�����
					String transPath = "src/TransPicture/";
					for (int i = 0; i < explist.size(); i++) {
						express ex1 = explist.get(i);
						String path = transPath + String.valueOf(flag2) + ".jpg";
						PictureService.drawTransPicture(ex1, path);
						flag2++;
					}
					// �����п��ͼƬ�ϵ�Ҫ�ػ���ȥ�����ɿ��ͼƬ
					int flag3 = 1;// ����ļ�λ�ã���̬���ɲ�����
					String emspath = "src/EMSpicture/";
					for (int i = 0; i < explist.size(); i++) {
						String BackPath = "src/BackdropPicture/back.jpg";
						String plannarPath = "src/PlannarPicture/plannar.jpg";
						String BarPath = barPath + String.valueOf(flag3) + ".jpg";
						String TransPath = transPath + String.valueOf(flag3) + ".jpg";
						String EMSPath = emspath + String.valueOf(flag3) + ".jpg";
						express ex1 = explist.get(i);
						PictureService.drawEMSpicture(ex1, BackPath, plannarPath, BarPath, TransPath, EMSPath);
						flag3++;
					}
					// ��̬�����ͼƬת����pdf
					String pdfpath = "src/PDF/";
					int flag4 = 1;// ����ļ�λ�ã���̬���ɲ�����
					for (int i = 0; i < explist.size(); i++) {
						String pdfpath2 = pdfpath + String.valueOf(flag4) + ".pdf";
						String picturepath = emspath + String.valueOf(flag4) + ".jpg";
						PDFservice.PicturetoPDF(picturepath, pdfpath2);
						flag4++;
					}
					System.out.println("һ������" + explist.size() + "��ͼƬ����ǰ��SRCĿ¼��EMSpictureĿ¼�鿴");
					System.out.println("һ������" + explist.size() + "��PDF����ǰ��SRCĿ¼��PDFĿ¼�鿴");
					break;
				}

			default:
				System.out.println("����������������������");
				break;

			}
			meanu();
		}

	}

	public static void meanu()// �˵�
	{
		System.out.println("��ӭʹ�ÿ��ͼƬ����ϵͳ");
		System.out.println("����������ѡ��");
		System.out.println("1.��excel�ļ���ȡ�����Ϣ");
		System.out.println("2.��ʾ���п����Ϣ");
		System.out.println("3.���ɿ��ͼƬ����ת����PDF");
		System.out.println("4.�˳�");

	}
}
