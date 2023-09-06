package com.interview.spring.application.dto;

import java.util.Date;
import java.util.List;

public class OrderResponseDTO {
	private Long orderId;
	private Date orderDate;
	private String orderStatus;
	private List<ItemResponseDTO> items;

	// Constructors, getters, and setters


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<ItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemResponseDTO> items) {
		this.items = items;
	}

}
