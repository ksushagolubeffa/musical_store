<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Cart</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<jsp:include page="/jsp/breadcrumb.jsp" />

	<div class="p-5 content" style="width: 50%; margin: 0 auto">
		<h2 class="title is-2 has-text-centered">Корзина</h2>

		<table>
			<thead>
			<tr>
				<th>Товар</th>
				<th>Удалить</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${orders}">
					<tr>
						<td>${order.getProductName()}, ${order.getProductPrice()}</td>
						<td>
							<form action="" method="post">
								<button name="deleteOrderID" value="${order.getOrderID()}" class="button is-danger"><i class="fa-solid fa-trash"></i></button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<c:if test="${orders.size() > 0}">
			<h5 class="title is-5">Итого: ${total} руб</h5>
			<form action="" method="post">
				<form method="post" action="">
					<button name="placeOrder" value="${total}" class="button is-primary">Оформить заказ<i style="margin-left: 5px;" class="fa-solid fa-cart-shopping"></i></button>
				</form>
			</form>
		</c:if>



	</div>
</body>
</html>
