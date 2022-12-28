package utils;

import jakarta.servlet.http.HttpSession;
import models.User;

public class UserUtils {
    public static boolean isAuth(HttpSession session) {
        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("auth");
        }

        return user != null;
    }

    public static boolean isAdmin(HttpSession session) {
        if (isAuth(session)) {
            User user = (User) session.getAttribute("auth");
            return user.getRole().equals("admin");
        }

        return false;
    }

    public static Integer getBalance(HttpSession session) {
        User user = (User) session.getAttribute("auth");
        return user.getBalance();
    }

}
