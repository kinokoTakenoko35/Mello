<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your favorite in here!</title>
	<link rel="stylesheet" type="text/css" href="/Mello/css/main.css?">
	<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="/Mello/css/loves.css">
	<script src="https://use.typekit.net/fya1swr.js"></script>
	<script>
		try {
			Typekit.load({async: true});
		} catch (e) {
		}
	</script>
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
			$('.toggle-heart').change(function() {
				$(this).parent().submit();
			});
			$('.toggle-heart_del').change(function() {
				$(this).parent().submit();
			});
		});
	</script>


</head>

<body>
	<header>
			<div id="header">
				<div class="bg-slider_menu" style="background: none;">
					<div class="title"><h1>Mello</h1></div>
					<div class="menu">Hi!<c:out value="${ loginUser.name }"/>.
					<a href="/Mello/Logout">LOGOUT.</a>
					</div>
				<div class="menu_clear;"></div>
				</div>
			</div>
	</header>

		<div class="container">
			<section class="maincol">
					<nav style="float:left;">
						<div class="container">
							<ul class="nav-items">
								<li class="nav-item active"><a href="./Main">POST</a></li>
								<li class="nav-item"><a href="#food-sweets">FOLLOWING</a></li>
								<li class="nav-item"><a href="#drink-beer">FOLLOWERS</a></li>
								<li class="nav-item"><a href="./Loves">LOVES</a></li>
								<li class="nav-item"><a href="./Profile">EDIT PROFILR</a></li>
							</ul>
						</div>
						<div class="search-view">
							<form action="/Mello/Loves" method="post">
								<input type="text" name="text" placeholder="Your favorite in here!" style="float:center;">
								<input type="submit" value="POST" style="float:center;">
							</form>
						</div>
					</nav>

					<aside class="search-view" style="float:left;">
						<div class="favariteForm">
							<span class="box">
								<c:if test="${ empty mutterList }">
								</c:if>
								<c:forEach var="mutter" items="${ mutterList }">
									<div class="FavList">
										<div>
											<div class="img">
												<img src="http://localhost/photo/${ mutter.img }" width="50" height="50" style="border-radius: 0.3125rem;">
											</div>
											<div class="text_css">
												<c:out value="${ mutter.userName }" />：<c:out value="${ mutter.text }" />
											</div>
											<!-- javaScriptでaction -->
												<form action="/Mello/Loves" method="post">
													<input id="toggle-heart${ mutter.id }" class="toggle-heart" type="checkbox" name="mutter_id" value="${ mutter.id }"/>
													<label class="toggle-heart-label" for="toggle-heart${ mutter.id }">❤</label>
												</form>
											<!-- </div> -->

										</div>
									</div>

								</c:forEach>
							</span>
						</div>
						<div style="clear:both;"></div>

						<div class="favariteResult">
						<p>お気に入り一覧</p>
							<span class="box2">
								<c:if test="${ empty lovesList }">
									<p>collect your favorite things.</p>
								</c:if>
								<c:forEach var="loves" items="${ lovesList }">
									<div class="FavList">
										<div class="img">
											<img src="http://localhost/photo/${ loves.img }" width="50" height="50" style="border-radius: 0.3125rem;">
										</div>
										<div class="text_css">
											<c:out value="${ loves.userName }" />：<c:out value="${ loves.text }" />
										</div>
										<!-- javaScriptでaction -->
										<form action="/Mello/Loves" method="post">
											<input id="toggle-heart_del${ loves.id }" class="toggle-heart_del" type="checkbox" name="del_loves_id" value="${ loves.id }"/>
											<label class="toggle-heart-label_del" for="toggle-heart_del${ loves.id }">❤</label>
										</form>
										</div>
								</c:forEach>
							</span>


						</div>
					</aside>
					<div style="clear:both;"></div>
			</section>


		</div>

	<footer>
		<div class="container">
			©......
		</div>
	</footer>
</body>
</html>