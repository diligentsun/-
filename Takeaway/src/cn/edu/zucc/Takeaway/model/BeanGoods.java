package cn.edu.zucc.Takeaway.model;

public class BeanGoods {
	private int goods_id;
	private int store_id;
	private int gategory_id;
	private String goods_name;
	private String store_name;
	private String gategory_name;
	private double price;
	private double sale_price;
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGategory_id() {
		return gategory_id;
	}
	public void setGategory_id(int gategory_id) {
		this.gategory_id = gategory_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getGategory_name() {
		return gategory_name;
	}
	public void setGategory_name(String gategory_name) {
		this.gategory_name = gategory_name;
	}	
	
	
}
