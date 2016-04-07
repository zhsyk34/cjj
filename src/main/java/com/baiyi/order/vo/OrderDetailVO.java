package com.baiyi.order.vo;

import java.util.List;

public class OrderDetailVO {

	private Integer foodId;

	private String name;

	private List<String> tasteList;

	private double price;

	private int count;

	private double total;

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTasteList() {
		return tasteList;
	}

	public void setTasteList(List<String> tasteList) {
		this.tasteList = tasteList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
