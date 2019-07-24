<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<!-- 先将JQuery引入 -->
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 将日期控件引入 -->
<script type="text/javascript" src="js/datepicker/WdatePicker.js"></script>

<link rel="stylesheet" href="res/css/style.css" />
<title>用户修改</title>

<script type="text/javascript">
	//不能以数字开头、不能纯数字、位数6-18
	var CHKLOGINNAME=/^\D[0-9a-zA-Z_]{5,15}$/;
	var CHKPASSWORD=/^\D[0-9a-zA-Z_]{5,15}$/;
	//邮箱：       xxxx@xxx.xxx.xx
	var CHKEMAIL=/^[a-zA-Z0-9_]{1,11}@[a-zA-Z0-9]{1,10}\.com$/;
	//真实姓名
	var CHKREALNAME=/^[\u4e00-\u9fa5]{2,}$/;
	
	//验证登录名
	function chkloginname() {
		//获取输入框元素
		//var loginEle=document.getElementById("loginname");
		//获取输入框的值
		//var loginname=loginEle.value;
		//获取span元素
		//var spanEle=document.getElementById("spanlogin");
		//使用test()方法验证是否符合条件
		var loginname = $("#loginname").val();
		if(CHKLOGINNAME.test(loginname)){
			//给页面提示验证成功
			//spanEle.innerHTML="√";
			//$("#spanlogin").html("√");
			//spanEle.style.color="green";
			//$("#spanlogin").css("color","green");
			if(chkExistLoginname(loginname)){
				return true;
			}else{
				return false;
			}
		}	//return true;
		else {
			//如果错误要告诉错误原因
			//spanEle.innerHTML="登录名不能以数字开头，长度在6-16位之间";
			$("#spanlogin").html("登录名不能以数字开头，长度在6-16位之间");
			//spanEle.style.color="red";
			$("#spanlogin").css("color","red");
			//输入验证有问题，清空输入框
			//loginEle.value="";
			//重新获取焦点
			//loginEle.focus();
			
			return false;
		}
	}
	//验证密码
	function chkpassword() {
		//获取输入框元素
		//var pwdEle=document.getElementById("password");
		//获取输入框的值
		//var password=pwdEle.value;
		//获取span元素
		//var spanEle=document.getElementById("spanpwd");
		//使用test()方法验证是否符合条件
		if(CHKPASSWORD.test($("#password").val().trim())){
			//给页面提示验证成功
			//spanEle.innerHTML="√";
			$("#spanpwd").html("√")
			//spanEle.style.color="green";
			$("#spanpwd").css("color","green");
			
			return true;
		}
		else {
			//如果错误要告诉错误原因
			//spanEle.innerHTML="密码不能以数字开头，长度在6-16位之间";
			$("#spanpwd").html("密码不能以数字开头，长度在6-16位之间");
			//spanEle.style.color="red";
			$("#spanpwd").css("color","red");
			//输入验证有问题，清空输入框
			//pwdEle.value="";
			//重新获取焦点
			//pwdEle.focus();
			
			return false;
		}
	}
	
	//验证邮箱
	function chkemail() {
		//获取输入框元素
		//var emlEle=document.getElementById("email");
		//获取输入框的值
		//var email=emlEle.value;
		//获取span元素
		//var spanEle=document.getElementById("spaneml");
		//使用test()方法验证是否符合条件
		var email = $("#email").val();
		if(CHKEMAIL.test(email)){
			//给页面提示验证成功
			//spanEle.innerHTML="√";
			//$("#spaneml").html("√");
			//spanEle.style.color="green";
			//$("#spaneml").css("color","green");
			
			if(chkExistEmail(email)){
				return true;
			}else{
				return false;
			}
			//return true;
		}
		else {
			//如果错误要告诉错误原因
			//spanEle.innerHTML="邮箱格式错误";
			$("#spaneml").html("邮箱格式错误");
			//spanEle.style.color="red";
			$("#spaneml").css("color","red");
			//输入验证有问题，清空输入框
			//emlEle.value="";
			//重新获取焦点
			//pwdEle.focus();
			return false;
		}
	}
	
	//验证两次输入的密码是否一致
	function chkrepwd() {
		//1.获取两次输入的密码
		//var password=document.getElementById("password").value;
		//var repwd=document.getElementById("repwd").value;
		
		//var spanEle=document.getElementById("spanrepwd");
		
		//2.进行匹配
		if($("#repwd").val()!=null&&($("#repwd").val().trim()!="")&&($("#password").val().trim()==$("#repwd").val().trim())){
			//spanEle.innerHTML="√";
			$("#spanrepwd").html("√");
			//spanEle.style.color="green";
			$("#spanrepwd").css("color","green");
			
			return true;
		}
		else{
			//spanEle.innerHTML="两次密码输入不一致";
			$("#spanrepwd").html("两次密码输入不一致");
			//spanEle.style.color="red";
			$("#spanrepwd").css("color","red");
			
			return false;
		}

	}
	
	//验证真实姓名
	function chkreal() {
		//1.获取两次输入的密码
		//var realname=document.getElementById("realname").value;
		//var spanEle=document.getElementById("spanreal");
		
		//2.进行匹配
		if(CHKREALNAME.test($("#realname").val().trim())){
			//spanEle.innerHTML="√";
			$("#spanreal").html("√");
			//spanEle.style.color="green";
			$("#spanreal").css("color","green");
			return true;
		}
		else{
			//spanEle.innerHTML="真实姓名格式错误，请重新输入";
			$("#spanreal").html("真实姓名格式错误，请重新输入");
			//spanEle.style.color="red";
			$("#spanreal").css("color","red");
			
			return false;
		}

	}
	
	//验证用户名是否重复
	function chkExistLoginname(loginname){
		//定义boolean类型返回值，默认false
		var chk1 = false;
		$.ajax({
			url:"chkuser.do",
			type:"post",
			data:"type=1&loginname="+loginname+"&id="+${user.id},
			async:false,
			dataType:"text",
			success:function(flag){
				//如果返回true表示未重复
				if(flag=="true"){
					$("#spanlogin").html("√");
					$("#spanlogin").css("color","green");
					chk1=true;
				}else{
					//重复时
					$("#spanlogin").html("用户名已存在");
					$("#spanlogin").css("color","red");
					chk1=false;
				}
			},
			error:function(){
				$("#spanlogin").html("请求数据失败，请联系管理员");
				$("#spanlogin").css("color","red");
			}
			
		});
		return chk1;
	}

	//验证邮箱是否重复
	function chkExistEmail(email){
		//定义boolean类型返回值，默认false
		var chk2 = false;
		$.ajax({
			url:"chkuser.do",
			type:"post",
			data:"type=2&email="+email+"&id="+${user.id},
			async:false,
			dataType:"text",
			success:function(flag){
				//如果返回true表示未重复
				if(flag=="true"){
					$("#spaneml").html("√");
					$("#spaneml").css("color","green");
					chk2=true;
				}else{
					//重复时
					$("#spaneml").html("邮箱已存在");
					$("#spaneml").css("color","red");
					chk2=false;
				}
			},
			error:function(){
				$("#spaneml").html("请求数据失败，请联系管理员");
				$("#spaneml").css("color","red");
			}
			
		});
		return chk2;
	}
	
	//做一个方法将所有的验证方法串起来，并且返回boolean
	function chkAll() {
		return chkloginname()&&chkpassword()&&chkemail()&&chkrepwd()&&chkreal();
	}
	
	
</script>

</head>
<body>

<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='userlist.do?page=1';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="userupdate.do?id=${user.id}" method="post" onsubmit="return chkAll()">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<%-- <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${msg}</span>
					</td>
				</tr> --%>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						用户名:</td><td width="80%" class="pn-fcontent">
						<input  type="text" id="loginname" class="required" name="loginname" maxlength="100" value="${user.loginname}" onblur="chkloginname()"  />
						<span id="spanlogin"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:</td><td width="80%" class="pn-fcontent">
						<input type="text" id="password" class="required" name="password" maxlength="100"  onblur="chkpassword()" />
						<span id="spanpwd"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:</td><td width="80%" class="pn-fcontent">
						<input  type="text" id="repwd" class="required"  maxlength="100"  onblur="chkrepwd()"    />
						<span id="spanrepwd"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						真实姓名:</td><td width="80%" class="pn-fcontent">
						<input  type="text" id="realname" class="required" name="realname" maxlength="100"  value="${user.realname}" onblur="chkreal()"   />
						<span id="spanreal"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<c:if test="${user.sex=='男'}">
							<input type="radio" name="sex" value="1" checked="checked"/>男
							<input type="radio" name="sex" value="2"/>女
						</c:if>
						<c:if test="${user.sex=='女'}">
							<input type="radio" name="sex" value="1" />男
							<input type="radio" name="sex" value="2" checked="checked"/>女
						</c:if>
					</td>
				</tr>
				
				
<%-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="birthday" maxlength="80"  value=" ${ user.birthday } "  />
					</td>
				</tr>
 --%>				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" onclick="WdatePicker()" value="${user.birthday}" name="birthday" maxlength="80"/>
					</td>
				</tr>
				
				<%-- <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept">
						
							<c:forEach items="${depts}" var="dept">
								<!--  显示所属部门  -->
								<c:if test=" ${ user.dept==dept.id } ">
									<option value="${dept.id}"  selected=" selected" >${dept.deptname}</option>
								</c:if>
								<c:if test=" ${ user.dept!=dept.id }  ">
									<option value="${dept.id}"  >${dept.deptname}</option>
								</c:if>
								
							</c:forEach>
					</select>
					</td>
				</tr> --%>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept">
							<c:forEach items="${depts}" var="dept">
							<!-- 显示所属部门 -->
							<c:if test="${user.dept==dept.id }">
							<option value="${dept.id}" selected="selected">${dept.deptname}</option>
							</c:if>
								<!-- 显示其他部门 -->
								<c:if test="${user.dept!=dept.id}">
								<option value="${dept.id}" >${dept.deptname}</option></c:if>
							</c:forEach>
					</select>
					</td>
				</tr>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input type="text" id="email" class="required" name="email" maxlength="80"  value="${user.email}" onblur="chkemail()" />
						<span id="spaneml"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否可用:</td><td width="80%" class="pn-fcontent">
						<c:if test="${user.enabled==1}">
							<input type="radio" class="required" name="enabled"  maxlength="80" value="1" checked="checked"/>可用
							<input type="radio" class="required" name="enabled"  maxlength="80" value="2"/>不可用
						</c:if>
						<c:if test="${user.enabled==2}">
							<input type="radio" class="required" name="enabled" maxlength="80" value="1"/>可用
							<input type="radio" class="required" name="enabled" maxlength="80" value="2" checked="checked"/>不可用
						</c:if>
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