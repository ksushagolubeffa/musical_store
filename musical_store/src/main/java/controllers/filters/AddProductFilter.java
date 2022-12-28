package controllers.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/instruments/add", "/instruments/edit"})
public class AddProductFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            res.sendRedirect("/login");
            return;
        }

        User user = (User) session.getAttribute("auth");
        if (user == null || !user.getRole().equals("admin")) {
            res.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, res);
    }
}
