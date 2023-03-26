<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="game.po.Dice"%>
<%@page import="game.po.User"%>
<jsp:include page="headerGame.jsp" />

<div class="dice-wrapper">


	<div
		class="main-section container d-flex flex-column justify-content-center mt-4 border">

		<div class="d-flex flex-row justify-content-between">

			<a href="GameServlet?play&reinit">
				<button class="btn btn-warning pr-5 pl-5">Play</button>
			</a> <a href="logout">
				<button class="btn btn-danger pr-5 pl-5">signOut</button>
			</a>
		</div>

		<div class="p-3 mt-4 ">
			<table class="table table-dark">
				<thead>
					<tr>
						<th scope="col">Rank</th>
						<th scope="col">Username</th>
						<th scope="col">Score</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
										
					
					if (users != null) {
						int i = 0;
						for (User user : users) {
							i += 1;
					%>
					<tr>
						<th scope="row"><%=i%></th>
						<td><%=user.getUsername()%></td>
						<td><%=user.getBestScore()%></td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>

		</div>




	</div>
</div>

<jsp:include page="footer.jsp" />
<!-- script -->
<script src="js/home.js"></script>