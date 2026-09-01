package com.example.demo.kafkaexcercice.service;

import com.example.demo.kafkaexcercice.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderProducer {

	private static final String TOPIC = "orders-topic";

	private static final AtomicInteger counter = new AtomicInteger(0);

	private final KafkaTemplate<String, Order> kafkaTemplate;

	public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendOrder(Order order) {
		String key = generateOrderKey();
		kafkaTemplate.send(TOPIC, key, order);
	}

	private String generateOrderKey() {
		int id = counter.incrementAndGet();
		return String.format("ORD-%03d", id);
	}
}