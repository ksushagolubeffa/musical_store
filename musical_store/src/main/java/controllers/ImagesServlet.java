package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImagesServlet", value = "/images/*")
public class ImagesServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgName = request.getPathInfo().substring(1);
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            if (product.getImageName().equals(imgName)) {
                byte[] img = product.getImage();
                response.setContentType(getServletContext().getMimeType(imgName));
                response.setContentLength(img.length);
                response.getOutputStream().write(img);
                return;
            }
        }
    }

}
