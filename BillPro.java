package front;

public class BillPro {
	int sn;
	String proname;
	int qty;
	String category;
	float gst;
	float gstam;
	float price;
	BillPro()
	{
		sn =11;
		proname = new String();
		qty = 0;
		category = new String();
		gst = 0;
		gstam = 0;
		price = 0;
	}
	
	void setData(int sn,String proname,String qty,String category,float gst,String price)
	{
		this.sn=sn;
		this.proname=proname;
		this.qty = Integer.parseInt(qty);
		this.category = category;
		this.gst = gst;
		this.price = Float.parseFloat(price) * this.qty;
		this.gstam = this.price - (this.price/(1 + this.gst/100));
	}
	void updateSn() {sn = sn- 1;}
	void  updateqty(int x) { qty = qty - 1;}
	
	int getSn() {System.out.println("sn returned");return sn;}
	String getProname() {System.out.println(proname); return proname;}
	int getQty() {return qty;}
	float getGstam() {return gstam;}
	float getPrice() {return price;}
	
}
