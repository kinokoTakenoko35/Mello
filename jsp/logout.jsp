<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mello</title>
		<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/Mello/css/logout.css">
		 <script src="https://use.typekit.net/fya1swr.js"></script>
		 <script>try {
		    Typekit.load({async: true});
		  } catch (e) {
		  }</script>

		<script type="text/javascript" src="/Mello/js/jquery.js"></script>
		<script type="text/javascript" src="/Mello/js/jquery.bgswitcher.js"></script>
	</head>
	<body>
		<div class="bg-slider" style="background: none;">
			<div id="root">
				<div id="header">
						<div class="login">
							<form action="/Mello/Login" method="post">
							<label for="form-button">
							<input type="text" name="id" placeholder="Enter your username" >
							<input type="password" name="pass" placeholder="Enter your password" >
							<input type="submit" value="Login">
							</label>
							</form>
						</div>
					<div class="title"><h1>Mello</h1></div>
				</div>
				<div id="content">
					<div class="inner">
					<h1>Welcome back.</h1>
						<div class="text">Hello. Sign in.</div>
						<a href="/Mello/jsp/">Go to Top!</a>
					</div>
				</div>
				<div id="sidebar">
				</div>
				<div id="footer">
					<img class="live-image" src="http://localhost:8080/Mello/img/Footer_Graphic1b_Large.png" alt="Footer_Graphic1b_Large" style="">
				</div>
			</div>
		</div>
	</body>
</html>