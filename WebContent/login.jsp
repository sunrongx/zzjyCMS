<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<link rel="icon" type="text/css" href="images/知之教育.ico"/>
		<link rel="stylesheet" type="text/css" href="css/cloud.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<script type="text/javascript" >
			function getCode(){
				document.getElementById("chcode").src="kaptcha.jpg";
			}
		</script>
		
		
		<div class="box">
			<form action="login.do" method="post">
				<fieldset id="">
					<legend>登陆界面</legend>
					<div class="error">
						
					</div>
					<div>
						<input type="text" placeholder="请输入账号" name="username" value="${cookie.username.value }" />
					</div>
					<div>
						<input type="password" placeholder="请输入密码" name="password" value="${cookie.password.value }"/>
					</div>
<!-- 					<div>
						<input type="text" id="kaptcha" name="kaptcha" placeholder="验证码" class="code">
						<canvas id="canvas" width="210" height="70"></canvas>
						<span class="login-w"><img id="kaptchaImage" src="kaptcha"></span>
						<script src="js/checked.js" type="text/javascript" charset="utf-8"></script>
					</div>
 -->					
 					<div>
 						<div style="color:red;font-size:16px;">${MSG }</div>
						<input type="text" placeholder="验证码" class="code" name="code">
						
						<img alt="" src="kaptcha.jpg" id="chcode" onclick="getCode()">
						
						<%-- <canvas id="canvas" width="210" height="70"></canvas>--%>
						<script src="jsecked.js" type="text/javascript" charset="utf-8"></script>
					</div>
 					
 					
					<div class="rmb">
						<input type="checkbox" name="choose" value="1" id="rmb"/>
						<label for="rmb">是否记住密码</label>
					</div>
					
					<p>
						<button>登录</button>
					</p>
				</fieldset>
			
			</form>
		</div>
		
		<div class="cloud">
			<div></div>
			<div></div>
			<div></div>
		</div>
	</body>
</html>
    