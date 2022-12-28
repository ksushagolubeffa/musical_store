<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Edit</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<jsp:include page="/jsp/breadcrumb.jsp" />
	<div class="p-5" style="width: 50%; margin: 0 auto">
		<h2 class="title is-2 has-text-centered">Редактировать</h2>
		<form action="" method="post" enctype="multipart/form-data">

			<div class="field">
				<label class="label">Название</label>
				<div class="control">
					<input class="input" type="text" name="name" value="${product.getName()}" required>
				</div>
			</div>

			<div class="field">
				<label class="label">Описание</label>
				<div class="control">
					<textarea class="textarea" name="description" required>${product.getDescription()}</textarea>
				</div>
			</div>

			<div class="field">
				<label class="label">Цена</label>
				<div class="control">
					<input class="input" type="number" name="price" required value="${product.getPrice()}">
				</div>
			</div>

			<div class="file has-name is-primary mb-5">
				<label class="file-label">
					<input class="file-input" type="file" name="image">
					<span class="file-cta">
				  <span class="file-icon">
					<i class="fas fa-upload"></i>
				  </span>
				  <span class="file-label">
					Выберите картинку
				  </span>
    				</span>

				</label>
			</div>

			<div class="field is-grouped">
				<div class="control">
					<button class="button is-link">Сохранить</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>
