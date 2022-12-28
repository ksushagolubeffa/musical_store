package utils;

import models.Order;
import services.OrderService;
import services.ProductService;

import java.util.List;

public class OrderUtils {
    private static final OrderService orderService = new OrderService();
    private static final ProductService productService = new ProductService();

    public static Integer getTotalSum(Long userID) {
        List<Order> orders = orderService.getAllOrders();

        return orders.stream()
                .filter(order -> order.getUserID().equals(userID) && !order.isExecuted())
                .mapToInt(order -> productService.getProductById(order.getProductID()).getPrice())
                .sum();
    }
}
