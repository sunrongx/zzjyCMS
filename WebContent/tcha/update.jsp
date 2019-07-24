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

<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<link rel="stylesheet" href="res/css/style.css" />
<title>栏目修改</title>

</head>
<body>

<script type="text/javascript">

	var CHKCNAME = /^\D[0-9a-zA-Z\u4e00-\u9fa5]{1,}$/;
	var CHKSORT = /[1-9]{1,}/;
	var CHKLEV = /[1-9]{1,}/;
	
	function chkCname(){
		var cname = $("#cname").val();
		if(CHKCNAME.test(cname)){
			//给页面提示验证成功
			//$("#spancname").html("√")
			//$("#spancname").css("color","green");
			if(chkExistCname(cname)){
				return true;
			}else{
				return false;
			}
			
		}else {
			//如果错误要告诉错误原因
			$("#spancname").html("栏目名称至少两个字符且不能以数字开头");
			$("#spancname").css("color","red");
			//重新获取焦点
			return false;
		}
	}
	
	function chkSort(){
		if(CHKSORT.test($("#sort").val())){
			//给页面提示验证成功
			$("#spansort").html("√")
			$("#spansort").css("color","green");
			
			return true;
		}
		else {
			//如果错误要告诉错误原因
			$("#spansort").html("顺序必须为数字");
			$("#spansort").css("color","red");
			//重新获取焦点
			return false;
		}
	}
	
	function chkLev(){
		if(CHKLEV.test($("#lev").val())){
			if($("#lev").val()==1&&$("#pid").val()!=0){
				//如果错误要告诉错误原因
				$("#spanlev").html("拥有上级栏目的栏目级别不为1");
				$("#spanlev").css("color","red");
				//重新获取焦点
				return false;
			}else{
				//给页面提示验证成功
				$("#spanlev").html("√")
				$("#spanlev").css("color","green");
				return true;
			}
			
		}else{
			//如果错误要告诉错误原因
			$("#spanlev").html("栏目级别必须为数字");
			$("#spanlev").css("color","red");
			//重新获取焦点
			return false;
		}
	}
	
	//验证栏目名是否重复
	function chkExistCname(cname){
		//定义boolean类型返回值，默认false
		var chk1 = false;
		$.ajax({
			url:"chktcha.do",
			type:"post",
			data:"cname="+cname,
			async:false,
			dataType:"text",
			success:function(flag){
				//如果返回true表示未重复
				if(flag=="true"){
					$("#spancname").html("√");
					$("#spancname").css("color","green");
					chk1=true;
				}else{
					//重复时
					$("#spancname").html("栏目名已存在");
					$("#spancname").css("color","red");
					chk1=false;
				}
			},
			error:function(){
				$("#spancname").html("请求数据失败，请联系管理员");
				$("#spancname").css("color","red");
			}
			
		});
		return chk1;
	}
	
	//返回所有方法的boolean返回，全true才返回true
	function chkAll() {
		return chkCname()&&chkSort()&&chkLev();
	}
	
</script>

<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='tcha.do ';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="tchaupdate.do?id=${tcha.id }" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">  ${ msg } </span>
					</td>
				</tr> 
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						栏目名:</td><td width="80%" class="pn-fcontent">
						<input type="text" id="cname" class="required" name="cname" maxlength="100" value="${tcha.cname}" onblur="chkCname()" />
						<span id="spancname"></span>
					</td>
				</tr>	
			
<%-- 				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						上级栏目:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="pid" maxlength="100"  value="${ tcha.pid } "   />
					</td>
				</tr>
--%>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						上级栏目:</td><td width="80%" class="pn-fcontent">
					<select id="pid" name="pid">
							<option value="0" >无上级栏目</option>
							<c:forEach items="${tchas}" var="tcha">
								<%-- <c:if test="${tcha.pid!=0}"><option value=" ${tcha.id}" >${tcha.cname}</option></c:if> --%>
								<option value=" ${tcha.id}" >${tcha.cname}</option>
							</c:forEach>
							
					</select>
					</td>
				</tr>
 				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						栏目级别:</td><td width="80%" class="pn-fcontent">
						<input type="text" id="lev" class="required" name="lev" maxlength="100"  value="${tcha.lev}" onblur="chkLev()" />
						<span id="spanlev"></span>
					</td>
				</tr>
				
				<input type="hidden" name="isleaf" value="${tcha.isleaf}" />
<%-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否叶子:</td><td width="80%" class="pn-fcontent">
						<c:if test="${tcha.isleaf==1 }">
							<input type="radio" class="required" name="isleaf"  maxlength="80" value="1" checked="checked"/>是
							<input type="radio" class="required" name="isleaf"  maxlength="80" value="2"/>不是
						</c:if>
						<c:if test="${tcha.isleaf==2 }">
							<input type="radio" class="required" name="isleaf" maxlength="80" value="1"/>是
							<input type="radio" class="required" name="isleaf" maxlength="80" value="2" checked="checked"/>不是
						</c:if>
					</td>
				</tr>
 --%>				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						栏目顺序:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="sort" maxlength="100"  value="${ tcha.sort } "   />
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