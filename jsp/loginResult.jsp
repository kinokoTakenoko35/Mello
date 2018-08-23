<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mello Login</title>
		<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/Mello/css/logout.css">
		 <script src="https://use.typekit.net/fya1swr.js"></script>
		 <script>try {
		    Typekit.load({async: true});
		  } catch (e) {
		  }</script>
	</head>
	<body>
		<div id="root">
			<div id="header">
				<div class="title"><h1>Mello</h1></div>
			</div>
			<div id="content">
				<div class="inner">
				<h1>Welcome Mello.</h1>
					<c:choose>
						<c:when test="${ not empty loginUser }">
							<p>ログインに成功しました</p>
							<p>ようこそ<c:out value="${ loginUser.name }"/></p>
							<a href="/Mello/Main">つぶやき投稿・閲覧へ</a>
						</c:when>
						<c:otherwise>
							<p>ログインに失敗しました</p>
							<p>${ msg }</p>
							<a href="/Mello/jsp">TOP</a>
						</c:otherwise>
					</c:choose>
                </div>
            </div>
        </div>
			<div id="sidebar">
			</div>
			<div id="footer">footer</div>
	</body>
</html>