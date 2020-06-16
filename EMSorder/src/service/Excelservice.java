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
	public static ArrayList<express> getinformatio() throws Exception//从excel中读取文件得到订单数据
{
	File f = new File("src/IOfile/订单表.xls");
	Workbook wok = Workbook.getWorkbook(f);
	Sheet sh = wok.getSheet(0);
	ArrayList<express> expList=new ArrayList<express>();
	int flag=-1;//标记此时操作的是运单数据集合中的第几个
	for(int i=1;i<sh.getRows();i++)
	{
		express ex1=null;
		ArrayList<String> list=new ArrayList<String>();//获得数据集合
	    Cell c1=sh.getCell(0,i);
	    if(c1.getContents().length()!=0)//判断第一列是否位空，不为空的话则生产新的运单数据
	    {
	    	for(int j=0;j<sh.getColumns();j++)
	    	{
	    		Cell c2=sh.getCell(j,i);
	    		list.add(c2.getContents());//将该行所有数据添加进数据集合中
	    	}
	    	 ex1=new express(list.get(0), list.get(1), list.get(3),list.get(4), list.get(5), list.get(6),list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12), list.get(13));
	    	 expList.add(ex1);
	    	 flag++;
	    }
	    else {//第一行为空的话将数据中商品名称添加进属于他的数据集合中
			Cell c3=sh.getCell(5,i);
			express ex2=expList.get(flag);//得到这一行应该添加进的运单对象
			String product=ex2.getProduct();
			product=product+c3.getContents();//将新的商品名称加入进去
			ex2.setProduct(product);//将添加新的数据后的运单对象替换原来的对象
			expList.set(flag, ex2);
		}
	}
	return expList;
}
}
