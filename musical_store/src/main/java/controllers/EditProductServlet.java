package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import services.ProductService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EditProductServlet", value = "/instruments/edit")
@MultipartConfig
public class EditProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private Product product;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productID = Long.parseLong(request.getParameter("edit"));
        product = productService.getProductById(productID);

        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Part image = request.getPart("image");
        String fileName = UUID.randomUUID() + "_" + image.getSubmittedFileName();

        productService.handleUpdateProduct(name, description, price, image, fileName, product);

        response.sendRedirect("/instruments");
    }
}
