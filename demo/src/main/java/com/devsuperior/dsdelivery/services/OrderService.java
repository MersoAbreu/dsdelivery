package com.devsuperior.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.entities.Order;
import com.devsuperior.dsdelivery.entities.OrderStatus;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.OrderRepository;
import com.devsuperior.dsdelivery.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	public OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = orderRepository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	@Transactional
	public OrderDTO insert(OrderDTO orderDTO){
		Order order = new Order(null, orderDTO.getAddress() , orderDTO.getLatitude(), orderDTO.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p: orderDTO.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = orderRepository.save(order);
		return new OrderDTO(order);
		
	}
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = orderRepository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}
}
