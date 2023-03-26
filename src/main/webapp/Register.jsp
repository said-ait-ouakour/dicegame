<jsp:include page="headerRegister.jsp" />


<div class="login-pg">
	<div class="row align-items-center" style="height: 100vh;">
		<div
			class="form-section mx-auto col-10 col-md-8 col-lg-3 p-3 border border-dark rounded">
			<div class="d-flex justify-content-center">
				<img class="logo w-25 h-25" alt="logo" src="public/img/logo.png"
					width="100%" height="20%" />
			</div>
			<h4 class="d-flex justify-content-center mt-3">Join Us</h4>

			<form id="signup" action="register" method="POST">
				<div class="form-group">
					<label for="username-input">Username</label> <input name="username"
						type="text" class="form-control" id="username"
						aria-describedby="emailHelp" placeholder="Enter username" required>
					<small></small>
				</div>
				<div class="form-group">
					<label for="username-input">E-mail</label> <input name="email"
						type="text" class="form-control" id="email"
						aria-describedby="emailHelp" placeholder="Enter valid email"
						required> <small></small>
				</div>
				<div class="form-group">
					<label for="passwd-input">Password</label> <input type="password"
						class="form-control" id="password" placeholder="Enter password"
						name="password" required> <small></small>
				</div>
				<div class="form-group">
					<label for="passwd-input">Confirme Password</label> <input
						type="password" class="form-control" id="confirm-password"
						placeholder="Confirme password" name="confirme-password" required>
					<small></small>
				</div>
				<div class="d-flex">
					<button type="submit" class="btn btn-dark mr-auto p-2">Register</button>
					<a href="/game/login" class="p-2"> login?</a>
				</div>
				<small> <%
 String message = request.getAttribute("message") != null ? (String) request.getAttribute("message") : "";
 out.print(message);
 %>
				</small>
			</form>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />
<script src="js/signup.js"></script>