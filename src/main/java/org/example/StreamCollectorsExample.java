package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, Double> productTotalCost = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

        List<Map.Entry<String, Double>> sortedProducts = productTotalCost.entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                .toList();

        List<Map.Entry<String, Double>> topThreeProducts = sortedProducts.stream()
                .limit(3)
                .toList();

        topThreeProducts.forEach(entry ->
                System.out.println("Продукт: " + entry.getKey() + ", Общая стоимость: " + entry.getValue()));
    }
}
