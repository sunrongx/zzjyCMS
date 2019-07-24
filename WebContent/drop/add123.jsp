<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 将JQuery引入 -->
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 将日期空间引入 -->
<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="res/css/style.css" />
<title>用户添加</title>

<script type="text/javascript">
	//不能以数字开头、不能纯数字、位数6-18
	var CHKLOGINNAME = /^\D[0-9a-zA-Z_]{5,15}$/;
	var CHKPASSWORD = /^\D[0-9a-zA-Z_]{5,15}$/;
	//邮箱：xxxx@xxx.xxx.xx	
	var CHKEMAIL = /^[a-zA-Z0-9_]{1,11}@[a-zA-Z0-9]{1,10}\.com$/;
	//真实姓名
	var CHKREALNAME = /^[\u4e00-\u9fa5]{2,}$/;

	function chknames(){
		var loginname = document.getElementById("loginname").value;
		var spanEle = document.getElementById("spanname").value;
		if(CHKLOGINNAME.test(loginname)){
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			spanEle.style.size="30px";
			return true;
		}else{
			spanEle.innerHTML="登录名格式不正确，请重新输入";
			spanEle.style.color="red";
			return false;
		}
	}
	
	function chkpasss(){
		var password = document.getElementById("password").value;
		var spanEle = document.getElementById("spanpass").value;
		if(CHKPASSWORD.test(password)){
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
		}else{
			spanEle.innerHTML="密码格式错误，请重新输入";
			spanEle.style.color="red";
			return false;
		}
	}
	
	function chkpasss2(){
		var chkpass = document.getElementById("chkpass").value;
		var password = document.getElementById("password").value;
		var spanEle = document.getElementById("spanchkpass").value;
		if(chkpass==password){
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
		}else{
			spanEle.innerHTML="两次输入的密码不符，请重新输入";
			spanEle.style.color="red";
			return false;
		}
	}
	
	function chkemails(){
		var chkemail = document.getElementById("email").value;
		var spanEle = document.getElementById("chkemail").value;
		if(CHKEMAIL.test(chkemail)){
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
		}else{
			spanEle.innerHTML="邮箱格式错误，请重新输入";
			spanEle.style.color="red";
			return false;
		}
	}
	
	function chkrealnames(){
		var chkrealname = document.getElementById("realname").value;
		var spanEle = document.getElementById("spanreal").value;
		if(CHKREALNAME.test(chkrealname)){
			spanEle.innerHTML="√";
			spanEle.style.color="green";
			return true;
		}else{
			spanEle.innerHTML="真实姓名格式错误，请重新输入";
			spanEle.style.color="red";
			return false;
		}
	}
	
	function chkAll(){
		return chknames()&&chkpasss()&&chkpasss2()&&chkemails()&&chkrealnames();
	}
</script>

</head>

<body>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='userlist.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="useradd.do" method="post" onsubmit="chkAll()">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${msg}</span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						用户名:</td><td width="80%" class="pn-fcontent">
						<input id="loginname" type="text" class="required" name="loginname" maxlength="100" onblur="chknames()"/>
						<span id="spanname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:</td><td width="80%" class="pn-fcontent">
						<input id="password" type="text" class="required" name="password" maxlength="100" onblur="chkpasss()"/>
						<span id="spanpass"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:</td><td width="80%" class="pn-fcontent">
						<input id="chkpass" type="text" class="required" name="repwd" maxlength="100" onblur="chkpasss2()"/>
						<span style="color:red">${pwdMsg }</span>
						<span id="spanchkpass"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:</td><td width="80%" class="pn-fcontent">
						<input id="realname" type="text" class="required" name="realname" maxlength="100" onblur="chkrealnames()"/>
						<span id="spanreal"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="sex" value="1" checked="checked"/>男
						<input type="radio" name="sex" value="2"/>女
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出生日期:</td><td width="80%" class="pn-fcontent">
						<!-- 将class换成日期控件 -->
						<input type="text" class="Wdate" onclick="WdatePicker()" name="birthday" maxlength="80"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}">${dept.deptname}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input id="email" type="text" class="required" name="email" maxlength="80" onblur="chkemails()" />
						<span id="spanemail"></span>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>