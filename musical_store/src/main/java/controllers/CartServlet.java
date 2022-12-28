package controllers;

import dto.OrderDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import models.User;
import services.OrderService;
import services.ProductService;
import utils.OrderUtils;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("auth");

        List<OrderDTO> orders = orderService.getAllOrders().stream()
                .filter(order -> order.getUserID().equals(user.getId()))
                .map(order -> {
                    Product product = productService.getProductById(order.getProductID());
                    return new OrderDTO(order.getId(), product.getName(), product.getPrice());
                })
                .collect(Collectors.toList());

        request.setAttribute("orders", orders);
        request.setAttribute("total", OrderUtils.getTotalSum(user.getId()));
        request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String deleteOrderID = request.getParameter("deleteOrderID");
        String placeOrder = request.getParameter("placeOrder");

        orderService.handleUpdateAndDelete(deleteOrderID, placeOrder, request, response);

    }
}
