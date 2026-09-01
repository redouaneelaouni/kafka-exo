package com.example.demo.kafkaexcercice.service;

import com.example.demo.kafkaexcercice.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

	private static final String TOPIC = "orders-topic";

	private final KafkaTemplate<String, Order> kafkaTemplate;

	public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendOrder(Order order) {

		kafkaTemplate.send(TOPIC, order.getCustomerName(), order);
	}
}