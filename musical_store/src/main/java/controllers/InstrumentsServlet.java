package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Order;
import models.Product;
import models.User;
import repositories.OrderRepository;
import services.OrderService;
import services.ProductService;
import utils.UserUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InstrumentsServlet", value = "/instruments")
public class InstrumentsServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = productService.getAllProducts();

        request.setAttribute("products", products);

        request.setAttribute("isAuth", UserUtils.isAuth(request.getSession(false)));
        request.setAttribute("isAdmin", UserUtils.isAdmin(request.getSession(false)));

        request.getRequestDispatcher("/WEB-INF/jsp/instruments.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("productID");

        if (product != null) {
            Long productID = Long.parseLong(product);
            User authUser = (User) request.getSession(false).getAttribute("auth");
            Long userID = authUser.getId();

            Order order = Order.builder().userID(userID).productID(productID).isExecuted(false).build();
            orderService.saveOrder(order);
            response.sendRedirect("/instruments");
        }
    }
}
