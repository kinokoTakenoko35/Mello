<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mello</title>
		<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/Mello/css/main.css">
		<link rel="stylesheet" type="text/css" href="/Mello/css/font.css">
		<link rel="stylesheet" type="text/css" href="/Mello/css/words.css">
		 <script src="https://use.typekit.net/fya1swr.js"></script>
		 <script>try {
		    Typekit.load({async: true});
		  } catch (e) {
		  }</script>
		<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Arvo|Lato:900" rel="stylesheet">
		<link href="https://fonts.googleapis.com/earlyaccess/nikukyu.css" rel="stylesheet" />
		<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
		<link href="https://fonts.googleapis.com/earlyaccess/hannari.css" rel="stylesheet" />

		<script type="text/javascript" src="/Mello/js/jquery.js"></script>
		<script type="text/javascript" src="/Mello/js/jquery.bgswitcher.js"></script>
		<script>
		jQuery(function($) {
		    $('.bg-slider_menu').bgSwitcher({
		        images: ['/Mello/img/ttt.jpg'], // 切替背景画像を指定
		    	interval: 2000, //	 背景画像を切り替える間隔を指定 3000=3秒
		            loop: true, // 切り替えを繰り返すか指定 true=繰り返す　false=繰り返さない
		            shuffle: true, // 背景画像の順番をシャッフルするか指定 true=する　false=しない
		            effect: "fade", // エフェクトの種類をfade,blind,clip,slide,drop,hideから指定
		            duration: 500, // エフェクトの時間を指定します。
		            easing: "swing" // エフェクトのイージングをlinear,swingから指定
		        });
		    });
		</script>
	</head>
	<body>
		<div id="root">
			<div id="header">
				<div class="bg-slider_menu" style="background: none;">
					<div class="title"><h1>Mello</h1></div>
					<div class="menu">Hi!<c:out value="${ loginUser.name }"/>.
					<a href="/Mello/Logout">LOGOUT.</a>
					<a href="/Mello/Main">UPDATE.</a>
						<form action="/Mello/Main" method="post">
							<label for="form-button">
								<input type="text" name="text" placeholder="Say hello...">
								<input type="submit" value="POST">
							</label>
						</form>
					</div>
				<div class="menu_clear;"></div>
				</div>
			</div>
				<div style="width:100%;">
					<div id="main">
						<div id="nav">
							<nav id="page-navigation" role="navigation" class="fixed" style="transition: none; top: 165.5px;">
								<ul class="nav-items"><li class="nav-item active"><a href="#food-snacks">POST</a></li>
								<li class="nav-item"><a href="./Following">FOLLOWING</a></li>
								<li class="nav-item"><a href="#drink-beer">FOLLOWERS</a></li>
								<li class="nav-item"><a href="./Loves">LOVES</a></li>
								<li class="nav-item"><a href="./Profile">EDIT PROFILR</a></li></ul>
							</nav>
						</div>
					<div id="content">
						<div class="inner">
							<div class="megForm">
								<c:if test="${ not empty errorMsg }">
									<p>${ errorMsg }</p>
								</c:if>
								<c:forEach var="mutter" items="${ mutterList }">
									<div class="img">
										<img src="http://localhost/photo/${ mutter.img }" width="50" height="50" style="border-radius: 0.3125rem; vertical-align: top;">
									</div>
									<div class="text_css">
										<span class="${ mutter.font } ${ mutter.words }"><c:out value="${ mutter.userName }" />：<c:out value="${ mutter.text }"/></span>
									</div>
									<div style="clear:both;"></div>
								</c:forEach>

							</div>
						</div>
					</div>

					</div>
				</div>
				<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</div>
	</body>
</html>