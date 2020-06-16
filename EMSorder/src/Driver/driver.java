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
				explist = Excelservice.getinformatio();// 获得所有运单信息
				System.out.println("共获取到" + explist.size() + "条信息");
				break;
			case 2:
				if (explist.size() == 0)// 判断是否读取了信息
				{
					System.out.println("您还没读取信息请重新输入");
					break;
				} else {
					for (int i = 0; i < explist.size(); i++) {
						System.out.println(explist.get(i).toString());
					}
					break;
				}
			case 3:
				if (explist.size() == 0)// 判断是否读取了信息
				{
					System.out.println("您还没读取信息请重新输入");
					break;
				} else {
					PictureService.drawPlannarPicture();// 画二维码
					PictureService.DrawBackDropPicture();// 画背景图
					// 动态生成订单号图片
					String barPath = "src/BarcodePicture/";
					int flag = 1;// 标记文件位置，动态生成不覆盖
					for (int i = 0; i < explist.size(); i++) {
						express ex1 = explist.get(i);
						String path = barPath + String.valueOf(flag) + ".jpg";
						PictureService.drawbarCode(ex1, path);
						flag++;
					}
					// 动态生成物流单号条形码图片
					int flag2 = 1;// 标记文件位置，动态生成不覆盖
					String transPath = "src/TransPicture/";
					for (int i = 0; i < explist.size(); i++) {
						express ex1 = explist.get(i);
						String path = transPath + String.valueOf(flag2) + ".jpg";
						PictureService.drawTransPicture(ex1, path);
						flag2++;
					}
					// 将所有快递图片上的要素画进去，生成快递图片
					int flag3 = 1;// 标记文件位置，动态生成不覆盖
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
					// 动态将快递图片转换成pdf
					String pdfpath = "src/PDF/";
					int flag4 = 1;// 标记文件位置，动态生成不覆盖
					for (int i = 0; i < explist.size(); i++) {
						String pdfpath2 = pdfpath + String.valueOf(flag4) + ".pdf";
						String picturepath = emspath + String.valueOf(flag4) + ".jpg";
						PDFservice.PicturetoPDF(picturepath, pdfpath2);
						flag4++;
					}
					System.out.println("一共生成" + explist.size() + "张图片，请前往SRC目录下EMSpicture目录查看");
					System.out.println("一共生成" + explist.size() + "张PDF，请前往SRC目录下PDF目录查看");
					break;
				}

			default:
				System.out.println("您的输入有误请重新输入");
				break;

			}
			meanu();
		}

	}

	public static void meanu()// 菜单
	{
		System.out.println("欢迎使用快递图片生成系统");
		System.out.println("请输入您的选择");
		System.out.println("1.从excel文件读取快递信息");
		System.out.println("2.显示所有快递信息");
		System.out.println("3.生成快递图片并且转换成PDF");
		System.out.println("4.退出");

	}
}
