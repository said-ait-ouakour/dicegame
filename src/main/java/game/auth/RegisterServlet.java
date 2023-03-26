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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session.getAttribute("connecteduser") == null) {
			req.getRequestDispatcher("Register.jsp").forward(req, resp);
			return;

		} else {
			User foundUser = (User) session.getAttribute("connecteduser");

			req.getRequestDispatcher("Game.jsp").forward(req, resp);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext cntx = getServletContext();

		PrintWriter printWriter = resp.getWriter();

		String username = req.getParameter("username");

		String mail = req.getParameter("email");

		String password = req.getParameter("password");



//		check if there is a list of user if not add one
		ArrayList<User> list;
		
		if (cntx.getAttribute("users") == null) {
			list = new ArrayList<User>();
			cntx.setAttribute("users", list);
		}

		list = (ArrayList<User>) cntx.getAttribute("users");
		
		for ( User user: list) {
			
			if (user.getUsername().equals(username)|| user.getEmail().equals(mail)) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
				req.setAttribute("message", "This username or e-mail already used");
				rd.include(req, resp);
				return;
			}
			
			
		}
		
		User newUser = new User();

		newUser.setUsername(username);
		
		newUser.setEmail(mail);
		
		newUser.setPassword(password);
		
//		save user
		list.add(newUser);

		req.getSession().setAttribute("connecteduser", newUser);
		req.getRequestDispatcher("Game.jsp").forward(req, resp);
		return;
	}

}
