/*
 * Creation : 4 Sep 2023
 */
package com.interview.spring.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.spring.application.dto.ItemResponseDTO;
import com.interview.spring.application.dto.OrderResponseDTO;
import com.interview.spring.application.entity.Items;
import com.interview.spring.application.entity.Orders;
import com.interview.spring.application.repo.ItemRepository;
import com.interview.spring.application.repo.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<OrderResponseDTO> getOrders() {

		List<Orders> orders = orderRepository.findAll();
		List<OrderResponseDTO> orderResponses = new ArrayList<>();

		for (Orders order : orders) {
			OrderResponseDTO orderResponse = new OrderResponseDTO();
			orderResponse.setOrderId(order.getOrderId());
			orderResponse.setOrderDate(order.getOrderDate());
			orderResponse.setOrderStatus(order.getOrderStatus());

			List<ItemResponseDTO> itemResponses = new ArrayList<>();
			for (Items item : order.getItems()) {
				ItemResponseDTO itemResponse = new ItemResponseDTO();
				itemResponse.setItemId(item.getItemId());
				itemResponse.setItemName(item.getItemName());
				itemResponse.setItemQuantity(item.getItemQuantity());
				itemResponse.setItemUnitPrice(item.getItemUnitPrice());
				itemResponses.add(itemResponse);
			}
			orderResponse.setItems(itemResponses);

			orderResponses.add(orderResponse);

		}

		return orderResponses;
	}

	@Override
	public OrderResponseDTO getOrdersById(long orderId) {

		Orders od = orderRepository.getById(orderId);
		OrderResponseDTO orderResponse = new OrderResponseDTO();

		orderResponse.setOrderId(od.getOrderId());
		orderResponse.setOrderDate(od.getOrderDate());
		orderResponse.setOrderStatus(od.getOrderStatus());

		List<ItemResponseDTO> itemResponses = new ArrayList<>();
		for (Items item : od.getItems()) {
			ItemResponseDTO itemResponse = new ItemResponseDTO();
			itemResponse.setItemId(item.getItemId());
			itemResponse.setItemName(item.getItemName());
			itemResponse.setItemUnitPrice(item.getItemUnitPrice());
			itemResponse.setItemQuantity(item.getItemQuantity());
			itemResponses.add(itemResponse);
		}
		orderResponse.setItems(itemResponses);

		return orderResponse;
	}

	@Override
	public Orders createOrder(List<OrderResponseDTO> body) {
		List<Orders> ordersList = new ArrayList<>();

		List<Items> items = new ArrayList<>();

		for (OrderResponseDTO orderRequest : body) {
			Orders order = new Orders();
			order.setOrderDate(orderRequest.getOrderDate());
			order.setOrderStatus("New");

			order = orderRepository.save(order);

			for (ItemResponseDTO itemResponse : orderRequest.getItems()) {
				Items item = new Items();
				item.setItemName(itemResponse.getItemName());
				item.setItemQuantity(itemResponse.getItemQuantity());
				item.setItemUnitPrice(itemResponse.getItemUnitPrice());
				item.setOrder(order);
				items.add(item);
			}
			itemRepository.saveAll(items);

			ordersList.add(order);
		}
		return ordersList.get(0);
	}

}
