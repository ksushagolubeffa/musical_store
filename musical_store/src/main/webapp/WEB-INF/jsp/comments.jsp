<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Comments</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<jsp:include page="/jsp/breadcrumb.jsp" />
	<div class="p-5" style="width: 50%; margin: 0 auto">
		<h2 class="title is-2 has-text-centered">Анонимные комментарии</h2>
		<ul class="content">
			<c:forEach var="comment" items="${comments}">
				<div class="box mb-2">
					${comment.getText()}
				</div>
			</c:forEach>
		</ul>

		<c:if test="${isAuth}">
			<form action="" method="post">

				<div class="field">
					<label class="label">Название инструента</label>
					<div class="control">
						<input class="input" type="text" name="comment" required>
					</div>
				</div>
				<button class="button is-primary">Отправить</button>
			</form>
		</c:if>
	</div>
</body>
</html>
