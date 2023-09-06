/*
 * Creation : 4 Sep 2023
 */
package com.interview.spring.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.spring.application.dto.OrderResponseDTO;
import com.interview.spring.application.entity.Orders;
import com.interview.spring.application.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	private static final String SUCCESS_MESSSAGE = "Orders created successfully.";
	private static final String FAILURE_MESSAGE = "Failed to create orders.";

	@Autowired
	OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<List<OrderResponseDTO>> getOrders() {

		List<OrderResponseDTO> result = orderService.getOrders();

		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("orderId") long orderId) {

		OrderResponseDTO result = orderService.getOrdersById(orderId);

		return new ResponseEntity<OrderResponseDTO>(result, HttpStatus.ACCEPTED);

	}

	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@RequestBody List<OrderResponseDTO> body) {

		Orders result = orderService.createOrder(body);

		return result != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(SUCCESS_MESSSAGE)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(FAILURE_MESSAGE);

	}
}
