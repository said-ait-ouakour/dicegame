package game.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import game.po.Dice;
import game.po.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GameServlet")
public class GamePlay extends HttpServlet {

//	Get method implementation
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

//	Post method implementation
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("connecteduser");

		StringBuffer sb = new StringBuffer();

		if (user != null) {

			if (request.getParameter("reinit") != null) {

				s.setAttribute("nbLnacement", 0);

				s.setAttribute("dices", new ArrayList<Dice>(3));

				s.setAttribute("score", 0);

				sb.append("Nouvelle Partie");

				request.setAttribute("msg", sb.toString());

				request.getRequestDispatcher("Game.jsp").forward(request, response);

				return;
			}

			double lastScore = user.getBestScore();

			ServletContext cntx = request.getServletContext();

			int nbLancement = s.getAttribute("nbLnacement") != null ? (Integer) s.getAttribute("nbLnacement") : 0;

			if (request.getParameter("play") != null) {

				double score = 0;
				while (nbLancement < 3) {

					nbLancement = s.getAttribute("nbLnacement") != null ? (Integer) s.getAttribute("nbLnacement") : 0;

					sb = new StringBuffer();
					int diceIndex = Integer.parseInt(request.getParameter("roll"));

					ArrayList<Dice> dices = s.getAttribute("dices") != null ? (ArrayList<Dice>) s.getAttribute("dices")
							: new ArrayList<Dice>(3);

					boolean rolled = false;

					for (Dice d : dices) {
						if (d.getName() == diceIndex) {
							rolled = true;
						}
					}

					if (!rolled) {

						Random rd = new Random();

						int resultat = 1 + rd.nextInt(6);

						Dice dice = new Dice(diceIndex, resultat);

						dices.add(nbLancement, dice);

						nbLancement += 1;

						s.setAttribute("nbLnacement", nbLancement);

						s.setAttribute("dices", dices);

//					check for the second roll 
						if (nbLancement == 1) {

							int dice1Key = dices.get(0).getName();
							int dice1Val = dices.get(0).getValue();

							if ((dice1Key == 2 && dice1Val == 6) || (dice1Key == 2 && dice1Val == 1)) {
								score = 0;
								request.setAttribute("dices", dices);
								sb.append("GAME OVER");
								request.setAttribute("score", score);
								request.setAttribute("msg", sb.toString());

								cntx.getRequestDispatcher("/Game.jsp").forward(request, response);
								
								return;
							}
							
							sb.append("Second roll");

						}
						if (nbLancement == 2) {

							int dice2Val = dices.get(1).getValue();
							int dice1Key = dices.get(0).getName();
							int dice1Val = dices.get(0).getValue();
							int dice2Key = dices.get(1).getName();

							if (dice1Val == dice2Val) {
								score = 0;
								request.setAttribute("dices", dices);
								sb.append("GAME OVER");
								request.setAttribute("score", score);
								request.setAttribute("msg", sb.toString());

								cntx.getRequestDispatcher("/Game.jsp").forward(request, response);

								return;
							}

//						check for  4 for 1 and 5 for 2 
							if (((dice1Key == 1 && dice1Val == 5) && (dice2Key == 2 && dice2Val == 6))
									|| ((dice1Key == 2 && dice1Val == 6) && (dice2Key == 1 && dice2Val == 5))) {
								score = 0;
								request.setAttribute("dices", dices);
								sb.append("GAME OVER");
								request.setAttribute("score", score);
								request.setAttribute("msg", sb.toString());

								cntx.getRequestDispatcher("/Game.jsp").forward(request, response);

								return;
							}

							if ((dice1Key == 2 && dice1Val == 6) || (dice2Key == 2 && dice2Val == 6)) {
								score = 0;
								request.setAttribute("dices", dices);
								sb.append("GAME OVER");
								request.setAttribute("score", score);
								request.setAttribute("msg", sb.toString());

								cntx.getRequestDispatcher("/Game.jsp").forward(request, response);

								return;
							}

//						check for d3=3 and d2=1
							if ((dice2Key == 2 && dice2Val == 1) || (dice1Key == 2 && dice1Val == 1)) {
								score = 0;
								request.setAttribute("dices", dices);
								sb.append("GAME OVER");
								request.setAttribute("score", score);
								request.setAttribute("msg", sb.toString());

								cntx.getRequestDispatcher("/Game.jsp").forward(request, response);
								return;
							}

							sb.append("Last roll");
						}

						if (nbLancement == 3) {

							int dice1Val = 0;
							int dice2Val = 0;
							int dice3Val = 0;

							for (Dice d : dices) {
								if (d.getName() == 1) {
									dice1Val = d.getValue();
								}
								if (d.getName() == 2) {
									dice2Val = d.getValue();
								}
								if (d.getName() == 3) {
									dice3Val = d.getValue();
								}
							}

							if ((dice1Val > dice2Val) && (dice2Val > dice3Val)) {
								score = dice1Val + dice2Val + dice3Val;
							}

							if ((dice3Val > dice2Val) && (dice2Val > dice1Val)) {
								score = dice1Val * dice2Val * dice3Val;
							}

							if (lastScore < score) {
								user.setBestScore(score);
							}

							s.setAttribute("connecteduser", user);
							request.setAttribute("dices", dices);
							if (score>0) {
								sb.append("YOU WIN!!");								
							} else {
								sb.append("GAME OVER");																
							}
							request.setAttribute("score", score);
							request.setAttribute("msg", sb.toString());

							cntx.getRequestDispatcher("/Game.jsp").forward(request, response);
							return;
						}

						request.setAttribute("dices", dices);

						request.setAttribute("msg", sb.toString());

						cntx.getRequestDispatcher("/Game.jsp").forward(request, response);

						return;
					}

					else {

						score = -1;

						user.setBestScore(score);

						request.setAttribute("score", score);

						sb.append("GAME OVER");

						request.setAttribute("msg", sb.toString());

						cntx.getRequestDispatcher("/Game.jsp").forward(request, response);

						return;
					}

				}
			} else {

				cntx.getRequestDispatcher("/Game.jsp").forward(request, response);
				response.sendRedirect("/GameServlet");
				return;
			}

		} else {

			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;

		}

	}

}
