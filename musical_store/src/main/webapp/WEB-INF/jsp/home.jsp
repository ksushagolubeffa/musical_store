<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home</title>
	<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value="/css/bulma.min.css"/>">
</head>
<body>
	<div>
		<jsp:include page="/jsp/breadcrumb.jsp" />

		<div class="p-5">
			<h2 class="title is-1">Добро пожаловать в магазин музыкальных инструментов</h2>
			<h5 style="color: #00d1b2" class="title is-5"><strong>ksushagolubeffa store</strong></h5>
		</div>
	</div>

	<div><img src="img/start.png" alt="start" style="margin-left: 25px; border-radius: 8px;" width="600" height="600"></div>

</body>
</html>
