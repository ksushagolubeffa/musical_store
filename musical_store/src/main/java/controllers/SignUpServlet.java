package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import services.UserService;
import utils.MD5HashFunction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {

    private final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = MD5HashFunction.hashPassword(request.getParameter("password"));
        List<User> users = service.getAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                response.sendRedirect("/signup?loginAlreadyExists");
                return;
            }
        }

        User user = User.builder().login(login).password(password).balance(0).build();
        service.saveUser(user);
        response.sendRedirect("/login");

    }
}
