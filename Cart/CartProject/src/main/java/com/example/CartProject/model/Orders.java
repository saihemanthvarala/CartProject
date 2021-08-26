package com.example.CartProject.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {

	@Id
	@Column
	private int order_id;
	@Column
	private int customer_id;
	@Column
	private String customer_name;
	@Column
	private String product_name;
	@Column
	private String product_type;
	@Column
	private Date date;
	@Column
	private Time time;
	@Column
	private double price;
	@Column
	private String address;
	@Column
	private String status;
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getOrder_id() {
		return order_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_type() {
		return product_type;
	}
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public double getPrice() {
		return price;
	}
	public String getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	Orders(){
		
	}
	public Orders(int order_id, int customer_id, String customer_name, String product_name, String product_type,
			Date date, Time time, double price, String address, String status) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.product_name = product_name;
		this.product_type = product_type;
		this.date = date;
		this.time = time;
		this.price = price;
		this.address = address;
		this.status = status;
	}
	
	
	
}
