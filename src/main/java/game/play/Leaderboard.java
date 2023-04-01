package game.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.UserDataHandler;

import game.po.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/leaderboard")
public class Leaderboard extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();
		User foundUser = (User) s.getAttribute("connecteduser");
		if (foundUser != null) {
			ServletContext cntx = request.getServletContext();

			ArrayList<User> users = (ArrayList<User>) cntx.getAttribute("users");

			// supported by jre 1.8
			users.sort(Collections
					.reverseOrder((user1, user2) -> Double.compare(user1.getBestScore(), user2.getBestScore())));

			if (users != null) {
				request.setAttribute("users", users);
				request.getRequestDispatcher("leaderboard.jsp").forward(request, response);
				return;
			}

		} else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}

	}
}
