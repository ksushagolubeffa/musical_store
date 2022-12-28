package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import services.ProductService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddProductServlet", value = "/instruments/add")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Part image = request.getPart("image");
        String fileName = UUID.randomUUID() + "_" + image.getSubmittedFileName();

        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .image(image.getInputStream().readAllBytes())
                .imageName(fileName)
                .build();

        productService.saveProduct(product);
        response.sendRedirect("/instruments");
    }
}
