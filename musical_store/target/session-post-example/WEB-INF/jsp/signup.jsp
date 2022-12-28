<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Sign UP</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<div>
		<jsp:include page="/jsp/breadcrumb.jsp" />
		<form action="" method="post">
			<div class="p-5" style="width: 50%; margin: 0 auto">
				<h2 class="title is-2 has-text-centered">Регистрация</h2>
				<div class="field">
					<p class="control has-icons-left has-icons-right">
						<input name="login" class="input" type="text" placeholder="Username">
						<span class="icon is-small is-left">
      <i class="fas fa-user"></i>
    </span>
						<span class="icon is-small is-right">
      <i class="fas fa-check"></i>
    </span>
					</p>
				</div>
				<div class="field">
					<p class="control has-icons-left">
						<input name="password" class="input" type="password" placeholder="Password">
						<span class="icon is-small is-left">
      <i class="fas fa-lock"></i>
    </span>
					</p>
				</div>
				<div class="field">
					<p class="control">
						<button class="button is-success">
							Регистрация
						</button>
					</p>
				</div>
			</div>
		</form>

	</div>
</body>
</html>
