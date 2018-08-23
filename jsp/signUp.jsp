<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
	<link href="https://fonts.googleapis.com/earlyaccess/kokoro.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="/Mello/css/index.css">
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
					<h1 style="color:#dddddd">Join The Creators Network.</h1>
						<div class="text" style="color:#dddddd">Hello Hello.</div>
						<div class="login">
							<form action="/Mello/UserCreate" method="post">
							<label for="form-button">
							<input type="text" name="name" placeholder="Enter your user_name" ><br>
							<input type="password" name="pass" placeholder="Enter your password" ><br>
							<input type="submit" value="Create account"><br>
							</label>
							</form>
						</div>
					</div>
				</div>
			<div id="sidebar">
				</div>
			<div id="footer"></div>
	</div>
</body>
</html>