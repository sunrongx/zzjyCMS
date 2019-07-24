<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 先将JQuery引入 -->
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>
<!-- 将日期控件引入 -->
<script type="text/javascript" src="../js/datepicker/WdatePicker.js"></script>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<link rel="stylesheet" href="../res/css/style.css" />
<title>广告添加</title>
</head>
<body>

<script type="text/javascript">
	
	var CHKTITLE = /^\D[0-9a-zA-Z\u4e00-\u9fa5]{1,}$/;
	
	function chkTitle(){
		var title = $("#title").val();
		if(CHKTITLE.test(title)){
			//给页面提示验证成功
			if(chkExistTitle(title)){
				return true;
			}else{
				return false;
			}
		}
		else {
			//如果错误要告诉错误原因
			$("#spantitle").html("广告名称至少两个字符且不能以数字开头");
			$("#spantitle").css("color","red");
			//重新获取焦点
			return false;
		}
	}
	
	//验证广告名是否重复
	function chkExistTitle(title){
		//定义boolean类型返回值，默认false
		var chk1 = false;
		$.ajax({
			url:"/zzcms/chktfas.do",
			type:"post",
			data:"title="+title,
			async:false,
			dataType:"text",
			success:function(flag){
				//如果返回true表示未重复
				if(flag=="true"){
					$("#spantitle").html("√");
					$("#spantitle").css("color","green");
					chk1=true;
				}else{
					//重复时
					$("#spantitle").html("广告名已存在");
					$("#spantitle").css("color","red");
					chk1=false;
				}
			},
			error:function(){
				$("#spantitle").html("请求数据失败，请联系管理员");
				$("#spantitle").css("color","red");
			}
			
		});
		return chk1;
	}
	
	function chkAll(){
		return chkTitle();
	}
	
</script>

<div class="box-positon">
	<div class="rpos">当前位置: 广告管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='tfas/tfas.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="../tfasadd.do" method="post" onsubmit="return chkAll()">
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
						广告名:</td><td width="80%" class="pn-fcontent">
						<input type="text" id="title" class="required" name="title" maxlength="100" onblur="chkTitle()" />
						<span id="spantitle"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						广告内容:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="content" maxlength="100"/>
					</td>
				</tr>
<!-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						发布时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" onclick="WdatePicker()" name="ctime" maxlength="100"/>
						
					</td>
				</tr>
-->
<!-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						发布人:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="crman" maxlength="100"/>
						
					</td>
				</tr>
 -->				
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