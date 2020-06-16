package service;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFservice {
	public static void PicturetoPDF(String picturepath, String pdfpath) throws Exception, DocumentException {
		String tAGString = "PdfManager";
		Document doc = new Document(PageSize.A4, 20, 20, 20, 20);// 得到pdf绘制工具
		PdfWriter.getInstance(doc, new FileOutputStream(pdfpath));
		doc.open();
		doc.newPage();
		// doc.add(new Paragraph("简单使用iText"));
		com.itextpdf.text.Image png1 = com.itextpdf.text.Image.getInstance(picturepath);// 得到快递运单图片
		float heigth = png1.getHeight();// 获得图片高度
		float width = png1.getWidth();// 获得图片宽度
		png1.setAlignment(com.itextpdf.text.Image.MIDDLE);
		int percent = getPercent2(heigth, width);
		png1.scalePercent(percent + 3);// 表示是原来图像的比例;
		doc.add(png1); // 将图片写入pdf中
		doc.close();
		File f = new File(pdfpath);

		f.createNewFile();
	}

	public static int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	public static int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}
}
