package cn.edu.zucc.Takeaway.model;

public class BeanStore {
	private int id;
	private String name;
	private int rating;
	private double PCC;
	private double total_sales;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getPCC() {
		return PCC;
	}
	public void setPCC(double pCC) {
		PCC = pCC;
	}
	public double getTotal_sales() { 
		return total_sales;
	}
	public void setTotal_sales(double total_sales) {
		this.total_sales = total_sales;
	}
}
