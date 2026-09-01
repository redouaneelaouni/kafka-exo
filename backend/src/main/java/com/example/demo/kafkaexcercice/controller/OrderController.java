package com.example.demo.kafkaexcercice.controller;

import com.example.demo.kafkaexcercice.model.Order;
import com.example.demo.kafkaexcercice.service.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

	private final OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}

	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody Order order) {

		orderProducer.sendOrder(order);

		return ResponseEntity.ok("Commande envoyée avec succès");
	}
}