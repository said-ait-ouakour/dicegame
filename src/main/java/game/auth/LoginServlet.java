package game.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import game.po.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		if (session.getAttribute("connecteduser") == null) {
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
			return;

		} else {
			User foundUser = (User) session.getAttribute("connecteduser");

			req.getRequestDispatcher("Game.jsp").forward(req, resp);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		ServletContext context = req.getServletContext();

		String username = (String) req.getParameter("username");

		String password = (String) req.getParameter("password");

		ArrayList<User> listUsers = context.getAttribute("users") != null
				? (ArrayList<User>) context.getAttribute("users")
				: new ArrayList<User>();

		for (User user : listUsers) {
//				validate username and password entry

			if ((user.getEmail().equals(username) || user.getUsername().equals(username))
					&& user.getPassword().equals(password)) {

				session.setAttribute("connecteduser", user);
				req.getRequestDispatcher("Game.jsp").forward(req, resp);
				return;
			}
			
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
		req.setAttribute("message", "The Credintails are not valid");
		rd.include(req, resp);
		return;

	}
}
