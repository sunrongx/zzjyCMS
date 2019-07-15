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

<link rel="stylesheet" href="res/css/style.css" />
<title>文章修改</title>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 文章管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='tart.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="tartupdate.do?id=${tart.id }" method="post">
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
						文章名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" maxlength="100"     value="${ tart.title } "  />
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						内容:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="content" maxlength="100"  value="${ tart.content } "   />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						作者:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="auther" maxlength="80"  value=" ${ tart.auther } "  />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						添加日期:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="ctime" maxlength="80"  value=" ${ tart.ctime } "  />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						所属栏目:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="channel" maxlength="80"  value=" ${ tart.strChannel } "  />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否推荐:</td><td width="80%" class="pn-fcontent">
						<c:if test="${tart.isremod=='1' }">
							<input type="radio" name="isremod" value="1" checked="checked"/>是
							<input type="radio" name="isremod" value="2"/>不是
						</c:if>
						<c:if test="${tart.isremod=='2' }">
							<input type="radio" name="isremod" value="1" />是
							<input type="radio" name="isremod" value="2" checked="checked"/>不是
						</c:if>
					</td>
				</tr> 
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否热点:</td><td width="80%" class="pn-fcontent">
						<c:if test="${tart.ishot=='1' }">
							<input type="radio" name="ishot" value="1" checked="checked"/>是
							<input type="radio" name="ishot" value="2"/>不是
						</c:if>
						<c:if test="${tart.ishot=='2' }">
							<input type="radio" name="ishot" value="1" />是
							<input type="radio" name="ishot" value="2" checked="checked"/>不是
						</c:if>
					</td>
				</tr> 
				
				
				
				<%-- <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<c:if test="${user.sex=='男' }">
							<input type="radio" name="sex" value="1" checked="checked"/>男
							<input type="radio" name="sex" value="2"/>女
						</c:if>
						<c:if test="${user.sex=='女' }">
							<input type="radio" name="sex" value="1" />男
							<input type="radio" name="sex" value="2" checked="checked"/>女
						</c:if>
					</td>
				</tr>  --%>
				
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
				
				<%-- <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select name="dept">
							<c:forEach items="${depts}" var="dept">
							<!-- 显示所属部门 -->
							<c:if test="${user.dept==dept.id }">
							<option value="${dept.id}" selected="selected">${dept.deptname}</option>
							</c:if>
								<!-- 显示其他部门 -->
								<c:if test="${user.dept!=dept.id }">
								<option value="${dept.id}" >${dept.deptname}</option></c:if>
							</c:forEach>
					</select>
					</td>
				</tr> --%>
				
				
				<%-- <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						邮箱:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" maxlength="80" value=" ${ user.email } "  />
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否可用:</td><td width="80%" class="pn-fcontent">
						<c:if test="${user.enabled==1 }">
							<input type="radio" class="required" name="enabled"  maxlength="80" value="1" checked="checked"/>可用
							<input type="radio" class="required" name="enabled"  maxlength="80" value="2"/>不可用
						</c:if>
						<c:if test="${user.enabled==2 }">
							<input type="radio" class="required" name="enabled" maxlength="80" value="1"/>可用
							<input type="radio" class="required" name="enabled" maxlength="80" value="2" checked="checked"/>不可用
						</c:if>
					</td>
				</tr> --%>
				
				
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