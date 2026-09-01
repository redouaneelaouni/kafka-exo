package com.example.demo.kafkaexcercice.service;

import com.example.demo.kafkaexcercice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "orders-topic",
            groupId = "order-group"
    )
    public void consumeOrder(Order order) {

        System.out.println("=================================");
        System.out.println("Commande reçue depuis Kafka");
        System.out.println("Client : " + order.getCustomerName());
        System.out.println("Produit : " + order.getProduct());
        System.out.println("Quantité : " + order.getQuantity());
        System.out.println("=================================");
    }
}