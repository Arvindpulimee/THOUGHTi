package com.interview.spring.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.spring.application.dto.ItemResponseDTO;
import com.interview.spring.application.dto.OrderResponseDTO;
import com.interview.spring.application.entity.Items;
import com.interview.spring.application.entity.Orders;
import com.interview.spring.application.repo.ItemRepository;
import com.interview.spring.application.repo.OrderRepository;

@SpringBootTest
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrders() {
        // Create mock data
        Orders order1 = new Orders();
        order1.setOrderDate(new Date());
        order1.setOrderId(1);
        order1.setOrderStatus("new");
        List<Items> itemResponses = new ArrayList<>();
        Items item = new Items();
        item.setItemId(1);
        item.setItemName("Hp Mouse");
        item.setItemQuantity(2);
        item.setItemUnitPrice(new BigDecimal("500.77"));
        itemResponses.add(item);
        order1.setItems(itemResponses);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order1);
        
        when(orderRepository.findAll()).thenReturn(ordersList);

        // Call the method to test
        List<OrderResponseDTO> result = orderService.getOrders();

        // Assertions and verification
        assertEquals(1, result.size());
        assertNotNull(result);
    }
    
    @Test
    public void testGetOrdersById() {
        // Create mock data
        long orderId = 1;
        
        // Mock an Orders object
        Orders mockOrder = new Orders();
        mockOrder.setOrderId(orderId);
        mockOrder.setOrderDate(new Date());
        mockOrder.setOrderStatus("new");

        List<Items> itemResponses = new ArrayList<>();
        Items item = new Items();
        item.setItemId(1);
        item.setItemName("Hp Mouse");
        item.setItemQuantity(2);
        item.setItemUnitPrice(new BigDecimal("500.77"));
        item.setOrder(mockOrder);
        itemResponses.add(item);
        mockOrder.setItems(itemResponses);

        // Mock the repository method to return the mockOrder when getById is called
        when(orderRepository.getById(orderId)).thenReturn(mockOrder);

        // Call the method to test
        OrderResponseDTO result = orderService.getOrdersById(orderId);

        // Assertions and verification
        assertEquals(orderId, result.getOrderId());
        assertNotNull(result);
    }

    @Test
    public void testCreateOrder() {
        // Create mock data
        long orderId = 1;

        // Mock an Orders object
        Orders mockOrder = new Orders();
        mockOrder.setOrderId(orderId);
        mockOrder.setOrderDate(new Date());
        mockOrder.setOrderStatus("new");

        List<Items> itemResponses = new ArrayList<>();
        Items item = new Items();
        item.setItemId(1);
        item.setItemName("Hp Mouse");
        item.setItemQuantity(2);
        item.setItemUnitPrice(new BigDecimal("500.77"));
        item.setOrder(mockOrder);
        itemResponses.add(item);
        mockOrder.setItems(itemResponses);

        List<OrderResponseDTO> body = new ArrayList<>();
        OrderResponseDTO mockOrderResponseDTO = new OrderResponseDTO();
        mockOrderResponseDTO.setOrderId(orderId);
        mockOrderResponseDTO.setOrderDate(new Date());
        mockOrderResponseDTO.setOrderStatus("new");
        List<ItemResponseDTO> itemResponseDTOs = new ArrayList<>();
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.setItemId(1234L);
        itemResponseDTO.setItemName("Hp Mouse");
        itemResponseDTO.setItemQuantity(2);
        itemResponseDTO.setItemUnitPrice(new BigDecimal("500.77"));
        itemResponseDTOs.add(itemResponseDTO);
        mockOrderResponseDTO.setItems(itemResponseDTOs);
        body.add(mockOrderResponseDTO);

        // Call the method to test
        when(orderRepository.save(any(Orders.class))).thenReturn(mockOrder);
        when(itemRepository.saveAll(anyList())).thenReturn(itemResponses);
        
        Orders result = orderService.createOrder(body);

        // Assertions and verification
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals("new", result.getOrderStatus());
        // Add more assertions as needed
    }


}