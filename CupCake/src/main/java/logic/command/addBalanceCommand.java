package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class addBalanceCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        UserController uc = new UserController();
        int balance;
        balance = (request.getParameter("balance").isEmpty()) ? 0 : Integer.parseInt(request.getParameter("balance"));

        uc.addBalance(user, balance);
        user = uc.getUser(username);
        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/cart");
    }

}