<%@page import="java.util.ArrayList"%>
<%@page import="game.po.Dice"%>
<%@page import="game.po.User"%>
<jsp:include page="headerGame.jsp" />

<div class="dice-wrapper">

	<div
		class="main-section container d-flex flex-column justify-content-center mt-4 border">

		<div class="d-flex flex-row justify-content-between">

			<a href="leaderboard">
				<button class="btn btn-warning">leaderboard</button>
			</a>

			<%
			if (request.getAttribute("score") != null) {
				Double score = (Double) request.getAttribute("score");
			%>
			<h3 class="title">
				score:
				<%=score%></h3>
			<%
			}
			%>


			<a href="logout">
				<button class="btn btn-danger">signOut</button>
			</a>



		</div>


		<div class=" d-flex flex-column">
			<div class="p-2">

				<%
				String msg = "";
				if (request.getAttribute("msg") != null) {
					msg = (String) request.getAttribute("msg");
				%>

				<h3 class="title"><%=msg%>

					<%
					if (msg.trim().equals("YOU WIN!!")) {
					%>
					&#127881;
					<%
					}
					%>
					<%
					if (msg.trim().equals("GAME OVER")) {
					%>
					&#128577;
					<%
					}
					%>
				</h3>
				<%
				}
				%>
				<div class="d-flex flex-row justify-content-between">
					<%
					ArrayList<Dice> dices = request.getAttribute("dices") != null ? (ArrayList<Dice>) request.getAttribute("dices")
							: new ArrayList<Dice>();

					String src = null;
					String src2 = null;
					String src3 = null;
					int diceNum = 0;
					int diceNum2 = 0;
					int diceNum3 = 0;

					if (dices == null) {
					%>
					<div class="die-container d-flex flex-column p-2">
						<img src="public/img/dice-1.png" id="die-1" class="dice-image">
					</div>
					<div class="die-container d-flex flex-column p-2">
						<img src="public/img/dice-2.png" id="die-2" class="dice-image">
					</div>
					<div class="die-container d-flex flex-column p-2">
						<img src="public/img/dice-3.png" id="die-3" class="dice-image">
					</div>
					<%
					} else {
					for (Dice dice : dices) {
						if (dice.getName() == 1) {
							diceNum = dice.getValue();
							src = "public/img/dice-" + dice.getValue() + ".png";
						}
					}
					if (src != null) {
					%>
					<div class="die-container d-flex flex-column p-2">
						<img src=<%=src%> class="dice-image" id="die-1">
						<p>
							<%=diceNum%>
						</p>
					</div>
					<%
					} else {
					%>
					<img src="public/img/dice-1.png" class="dice-image" id="die-2">
					<%
					}
					%>
					<%
					for (Dice dice : dices) {
						if (dice.getName() == 2) {
							diceNum2 = dice.getValue();
							src2 = "public/img/dice-" + dice.getValue() + ".png";
						}
					}
					if (src2 != null) {
					%>
					<div class=" die-container d-flex flex-column p-2">
						<img src=<%=src2%> class="dice-image" id="die-2">
						<p>
							<%=diceNum2%>
						</p>
					</div>
					<%
					} else {
					%>
					<img src="public/img/dice-1.png" class="dice-image" id="die-2" />

					<%
					}
					%>
					<%
					for (Dice dice : dices) {
						if (dice.getName() == 3) {
							diceNum3 = dice.getValue();
							src3 = "public/img/dice-" + dice.getValue() + ".png";
						}
					}
					if (src3 != null) {
					%>
					<div class="die-container d-flex flex-column p-2">
						<img src=<%=src3%> class="dice-image" id="die-3" />
						<p>
							<%=diceNum3%>
						</p>
					</div>
					<%
					} else {
					%>
					<img class="dice-image" src="public/img/dice-1.png" id="die-3" />

					<%
					}
					}
					%>
				</div>
			</div>
			<div
				class="p-2 mt-3 d-flex flex-row 
							
				<%if (!(msg.trim().equals("GAME OVER") || msg.trim().equals("YOU WIN!!"))) {%>

			justify-content-between">
				<a href="GameServlet?play&roll=1">
					<button class="roll-btn btn btn-info w-100" onClick="roll()">roll
						dice 1</button>
				</a> <a href="GameServlet?play&roll=2">
					<button class="roll-btn btn btn-info w-100" onClick="roll()">roll
						dice 2</button>
				</a> <a href="GameServlet?play&roll=3">
					<button class="roll-btn btn btn-info w-100" onClick="roll()">roll
						dice 3</button>
				</a>
				<%
				} else {
				%>
				justify-content-center"> <a href="GameServlet?play&reinit">
					<button class="replay-btn btn btn-success w-100">replay</button>
				</a>
				<%
				}
				%>
			</div>
		</div>

	</div>
</div>

<jsp:include page="footer.jsp" />
<!-- script -->
<script src="js/home.js"></script>