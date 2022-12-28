package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.AnonymousComment;
import services.CommentService;
import utils.UserUtils;

import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CommentsServlet", urlPatterns = "/instruments/comments/*")
public class CommentsServlet extends HttpServlet {

    private final CommentService commentService = new CommentService();
    private Long productId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            productId = Long.parseLong(request.getPathInfo().substring(1));
            List<AnonymousComment> comments = commentService.getAllComments().stream()
                    .filter(comment -> comment.getProductID().equals(productId)).collect(Collectors.toList());


            request.setAttribute("isAuth", UserUtils.isAuth(request.getSession(false)));
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/WEB-INF/jsp/comments.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("/instruments?error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        AnonymousComment cmt = AnonymousComment.builder().text(comment).productID(productId).build();

        commentService.saveComment(cmt);
        response.sendRedirect("/instruments/comments/" + productId);
    }
}
