package vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class express {
private String num;//��ݱ��
private String order;//������
private String order2;//��������
private String weight;//����
private String count;//����
private String product;//������Ʒ
private String ReceName;//�ռ�������
private String ReceZipCode;//�ռ����ʱ�
private String ReceAdress;//�ռ��˵�ַ
private String ReceTele;//�ռ��˵绰
private String SenName;//����������
private String SenZipCode;//�������ʱ�
private String SenAdress;//�����˵�ַ
private String SenTele;//�����˵绰
public express() {
	super();
	// TODO Auto-generated constructor stub
}
public express(String num,String order2,String weight, String count, String product, String receName,
		String receZipCode, String receAdress, String receTele, String senName, String senZipCode, String senAdress,
		String senTele) {
	super();
	//������Ϊʱ��������������
	SimpleDateFormat dF2 = new SimpleDateFormat("yyyyMMddhhmmss");//��õ�ǰϵͳʱ�䰴�չ涨��ʽ
	Date today = new Date();
	String date = dF2.format(today);
	int i= new java.util.Random().nextInt(900)+100;
	String s=String.valueOf(i);//�����ţ�ʱ�����λ�����
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
