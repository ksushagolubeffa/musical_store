<%@ page import="utils.UserUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar" role="navigation" aria-label="main navigation" style="position: sticky; top: 0; border-bottom: 1px solid #f5f5f5">
	<div class="navbar-brand">

		<a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
			<span aria-hidden="true"></span>
			<span aria-hidden="true"></span>
			<span aria-hidden="true"></span>
		</a>
	</div>

	<div id="navbarBasicExample" class="navbar-menu">
		<div class="navbar-start">
			<a class="navbar-item" href="<c:url value="/home"/>">
				Home
			</a>

			<a class="navbar-item" href="<c:url value="/instruments"/>">
				Musical instruments
			</a>

			<div class="navbar-item has-dropdown is-hoverable">
				<a class="navbar-link">
					More
				</a>

				<div class="navbar-dropdown">
					<a class="navbar-item" href="<c:url value="/instruments/add"/>">
						Add instrument
					</a>
					<a class="navbar-item" href="<c:url value="/cart"/>">
						Cart
					</a>
				</div>
			</div>
			<% if (UserUtils.isAuth(request.getSession(false))) { %>
				<a class="navbar-item">
					Balance: <%= UserUtils.getBalance(request.getSession(false)) %> rub
				</a>
			<% } %>
		</div>

		<div class="navbar-end">
			<div class="navbar-item">
				<div class="buttons">
					<a class="button is-primary" href="<c:url value="/signup"/>">
						<strong>Sign up</strong>
					</a>
					<a class="button is-light" href="<c:url value="/login"/>">
						Log in
					</a>
				</div>
			</div>
		</div>
	</div>
</nav>