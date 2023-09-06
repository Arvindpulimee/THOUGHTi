/*
 * Creation : 4 Sep 2023
 */
package com.interview.spring.application.service;

import java.util.List;

import com.interview.spring.application.dto.OrderResponseDTO;
import com.interview.spring.application.entity.Orders;

public interface OrderService {
	
	public List<OrderResponseDTO> getOrders();
	
	public OrderResponseDTO getOrdersById(long orderId);
	
	public Orders createOrder(List<OrderResponseDTO> body);

}
