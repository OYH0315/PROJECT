package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import vo.express;

public class Excelservice {
	public static ArrayList<express> getinformatio() throws Exception//��excel�ж�ȡ�ļ��õ���������
{
	File f = new File("src/IOfile/������.xls");
	Workbook wok = Workbook.getWorkbook(f);
	Sheet sh = wok.getSheet(0);
	ArrayList<express> expList=new ArrayList<express>();
	int flag=-1;//��Ǵ�ʱ���������˵����ݼ����еĵڼ���
	for(int i=1;i<sh.getRows();i++)
	{
		express ex1=null;
		ArrayList<String> list=new ArrayList<String>();//������ݼ���
	    Cell c1=sh.getCell(0,i);
	    if(c1.getContents().length()!=0)//�жϵ�һ���Ƿ�λ�գ���Ϊ�յĻ��������µ��˵�����
	    {
	    	for(int j=0;j<sh.getColumns();j++)
	    	{
	    		Cell c2=sh.getCell(j,i);
	    		list.add(c2.getContents());//����������������ӽ����ݼ�����
	    	}
	    	 ex1=new express(list.get(0), list.get(1), list.get(3),list.get(4), list.get(5), list.get(6),list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12), list.get(13));
	    	 expList.add(ex1);
	    	 flag++;
	    }
	    else {//��һ��Ϊ�յĻ�����������Ʒ������ӽ������������ݼ�����
			Cell c3=sh.getCell(5,i);
			express ex2=expList.get(flag);//�õ���һ��Ӧ����ӽ����˵�����
			String product=ex2.getProduct();
			product=product+c3.getContents();//���µ���Ʒ���Ƽ����ȥ
			ex2.setProduct(product);//������µ����ݺ���˵������滻ԭ���Ķ���
			expList.set(flag, ex2);
		}
	}
	return expList;
}
}
