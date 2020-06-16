package vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class express {
private String num;//快递编号
private String order;//订单号
private String order2;//物流单号
private String weight;//重量
private String count;//数量
private String product;//包含产品
private String ReceName;//收件人姓名
private String ReceZipCode;//收件人邮编
private String ReceAdress;//收件人地址
private String ReceTele;//收件人电话
private String SenName;//发件人姓名
private String SenZipCode;//发件人邮编
private String SenAdress;//发件人地址
private String SenTele;//发件人电话
public express() {
	super();
	// TODO Auto-generated constructor stub
}
public express(String num,String order2,String weight, String count, String product, String receName,
		String receZipCode, String receAdress, String receTele, String senName, String senZipCode, String senAdress,
		String senTele) {
	super();
	//订单号为时间加上随机数生成
	SimpleDateFormat dF2 = new SimpleDateFormat("yyyyMMddhhmmss");//获得当前系统时间按照规定格式
	Date today = new Date();
	String date = dF2.format(today);
	int i= new java.util.Random().nextInt(900)+100;
	String s=String.valueOf(i);//订单号：时间加三位随机数
	this.order=date+s;
	this.order2=order2;
	this.num = num;
	this.weight = weight;
	this.count = count;
	this.product=product;
	ReceName = receName;
	ReceZipCode = receZipCode;
	ReceAdress = receAdress;
	ReceTele = receTele;
	SenName = senName;
	SenZipCode = senZipCode;
	SenAdress = senAdress;
	SenTele = senTele;
}

public String getOrder2() {
	return order2;
}
public void setOrder2(String order2) {
	this.order2 = order2;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public String getOrder() {
	return order;
}
public void setOrder(String order) {
	this.order = order;
}
public String getWeight() {
	return weight;
}
public void setWeight(String weight) {
	this.weight = weight;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}

public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}
public String getReceName() {
	return ReceName;
}
public void setReceName(String receName) {
	ReceName = receName;
}
public String getReceZipCode() {
	return ReceZipCode;
}
public void setReceZipCode(String receZipCode) {
	ReceZipCode = receZipCode;
}
public String getReceAdress() {
	return ReceAdress;
}
public void setReceAdress(String receAdress) {
	ReceAdress = receAdress;
}
public String getReceTele() {
	return ReceTele;
}
public void setReceTele(String receTele) {
	ReceTele = receTele;
}
public String getSenName() {
	return SenName;
}
public void setSenName(String senName) {
	SenName = senName;
}
public String getSenZipCode() {
	return SenZipCode;
}
public void setSenZipCode(String senZipCode) {
	SenZipCode = senZipCode;
}
public String getSenAdress() {
	return SenAdress;
}
public void setSenAdress(String senAdress) {
	SenAdress = senAdress;
}
public String getSenTele() {
	return SenTele;
}
public void setSenTele(String senTele) {
	SenTele = senTele;
}
@Override
public String toString() {
	return "express [num=" + num + ", order=" + order + ", order2=" + order2 + ", weight=" + weight + ", count=" + count
			+ ", product=" + product + ", ReceName=" + ReceName + ", ReceZipCode=" + ReceZipCode + ", ReceAdress="
			+ ReceAdress + ", ReceTele=" + ReceTele + ", SenName=" + SenName + ", SenZipCode=" + SenZipCode
			+ ", SenAdress=" + SenAdress + ", SenTele=" + SenTele + "]";
}



}
