<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

<link rel="stylesheet" href="res/css/style.css" />
<title>广告修改</title>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 广告管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='tfas.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="tfasupdate.do?id=${tfas.id }" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
		
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						广告名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" maxlength="100"     value="${ tfas.title } "  />
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						广告内容:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="content" maxlength="100"  value="${ tfas.content } "   />
					</td>
				</tr>
				
<%-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						修改时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="ctime" maxlength="80"  value=" ${ tfas.ctime } "  />
					</td>
				</tr>
 --%>				
				
<%-- 			<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						发布人:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="crman" maxlength="80"  value=" ${ tfas.crman } "  />
					</td>
				</tr>
 --%>				
				
				
				
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