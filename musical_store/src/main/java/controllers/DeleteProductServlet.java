package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.CommentService;
import services.ProductService;

import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", urlPatterns = "/instruments/delete/*")
public class DeleteProductServlet extends HttpServlet {

    private final CommentService commentService = new CommentService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getPathInfo().substring(1));
        commentService.getAllComments().forEach(comment -> {
            if (comment.getProductID().equals(id)) {
                commentService.deleteComment(comment.getId());
            }
        });

        productService.deleteProduct(id);
        response.sendRedirect("/instruments");
    }
}
