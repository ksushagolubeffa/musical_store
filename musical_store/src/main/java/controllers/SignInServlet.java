package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import services.UserService;
import utils.MD5HashFunction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignInServlet", value = "/login")
public class SignInServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = MD5HashFunction.hashPassword(request.getParameter("password"));
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", user);
                response.sendRedirect("/home");
                return;
            }
        }

        response.sendRedirect("/login?invalidPassOrLogin");
    }
}
