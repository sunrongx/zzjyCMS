<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/theme.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/jquery.validate.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/jquery.treeview.css" rel="stylesheet"
	type="text/css" />
<link href="res/common/css/jquery.ui.css" rel="stylesheet"
	type="text/css" />

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>


<link rel="stylesheet" href="res/css/style.css" />
<title>文章添加</title>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 文章管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='tart/list.jsp';"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="tartadd.do" method="post">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired"></span> <span class="pn-frequired">${msg}</span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 文章名:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="title" maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 内容:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="content" maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 作者:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="auther" maxlength="100" /></td>
					</tr>
<!-- 				<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 日期:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="ctime" maxlength="100" /></td>
					</tr> 
-->
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 所属栏目:</td>
						<td width="80%" class="pn-fcontent">
							<!-- <input type="text"class="required" name="channel" maxlength="100" /> -->
							<select name="channel">
								<option value="0" >-无所属栏目-</option>
								<c:forEach items="${chas}" var="cha">
									
									<option value=" ${cha.id}" >${cha.cname}</option>
								</c:forEach>
							
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="isremod" value="1" checked="checked" />是 <input
							type="radio" name="isremod" value="2" />不是</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="ishot" value="1" checked="checked" />是 <input type="radio"
							name="ishot" value="2" />不是</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>