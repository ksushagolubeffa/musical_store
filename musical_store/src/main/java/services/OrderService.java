package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import models.User;
import repositories.OrderRepository;

import java.io.IOException;
import java.util.List;

public class OrderService {
    private final OrderRepository repository = new OrderRepository();

    public void saveOrder(Order order) {
        repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public void deleteOrder(Long id) {
        repository.delete(id);
    }

    public void handleUpdateAndDelete(String deleteOrderID, String placeOrder, HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserService();

        try {
            if (deleteOrderID != null) {
                Long orderID = Long.parseLong(deleteOrderID);
                deleteOrder(orderID);
                response.sendRedirect("/cart");

            } else if (placeOrder != null) {
                Integer total = Integer.parseInt(placeOrder);
                User user = (User) request.getSession(false).getAttribute("auth");
                if (user.getBalance() < total) {
                    response.sendRedirect("/cart?error");
                    return;
                }

                user.setBalance(user.getBalance() - total);
                userService.updateUser(user);

                getAllOrders().forEach(order -> {
                    if (order.getUserID().equals(user.getId()) && !order.isExecuted()) {
                        deleteOrder(order.getId());
                    }
                });

                response.sendRedirect("/home");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
