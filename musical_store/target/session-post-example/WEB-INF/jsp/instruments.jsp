<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Instruments</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<jsp:include page="/jsp/breadcrumb.jsp" />

	<div class="p-5" style="width: 50%; margin: 0 auto">
		<c:forEach var="product" items="${products}">
			<div class="card mb-3">
				<div class="card-image">
					<figure class="image is-4by3" style="margin: 0 auto;">
						<img style="object-fit: contain" src="/images/${product.getImageName()}" alt="Placeholder image">
					</figure>
				</div>
				<div class="card-content">
					<div class="media">
						<div class="media-content">
							<p class="title is-4">${product.getName()}</p>
						</div>
					</div>

					<div class="content mt-3">
						${product.getDescription()}
						<div class="mt-3"><b style="font-size: 25px;">${product.getPrice()}</b><i> руб</i></div>
					</div>

					<div>
						<form method="get" action="/instruments/comments/${product.getId()}">

							<button class="button is-dark">Комментарии</button>
						</form>
					</div>

					<c:if test="${isAuth}">
						<form method="post" action="">
							<div class="mt-3">
								<button name="productID" value="${product.getId()}" class="button is-primary">Добавить в корзину</button>
							</div>
						</form>
					</c:if>

					<c:if test="${isAdmin}">
						<form method="get" action="<c:url value="/instruments/edit"/>">
							<div class="mt-3">
								<button name="edit" value="${product.getId()}" class="button is-info">Редактировать</button>
							</div>
						</form>

						<form method="post" action="/instruments/delete/${product.getId()}">
							<div class="mt-3">
								<button name="edit" value="${product.getId()}" class="button is-danger">Удалить</button>
							</div>
						</form>
					</c:if>

				</div>

			</div>
		</c:forEach>
	</div>
</body>
</html>
