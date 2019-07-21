<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>
<link rel="stylesheet" href="../res/css/style.css" />
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>User-list</title>
<script type="text/javascript">

function del(){
 	var con=confirm("是否删除该用户");
 	if (con) {
		return true;
	} else {
		return false;
	}
}
$(function(){
	$("#zxc").click(function(){
		var likeName=$("#likeName").val();
	
		location.href="userlist.do?name="+likeName;
	});
	
});
//文档加载
$(function(){
//当选取表头一行的复选框时 下面的全部选中
$("#ids").click(function(){
	var eles = $(":checkbox").prop("checked",this.checked);
	$.each(eles,function(){
		var ids = $(this).val();	//这里即获取到多选框选中的值的数组，使用.length属性可获取数组长度
		alert(ids);
		$.get("Servlet",{info:ids});
	});

})
})


/* function delAll(){
	var ids = document.getElementsByName("ids").value;
	
	return ids;
	
} */

//onclick="javascript:window.location.href='userlist.do'"
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 列表</div>
	<form class="ropt">
	<div  class="clear">
	<input  type="text" name="name" id="likeName" class="" value="${name }" placeholder="模糊查询......."/>
	<input type="button" value="查询" id="zxc" />
	 <input class="add" type="button" value="添加" onclick="javascript:window.location.href='dept.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<input type="hidden" name="pageNo" value=""/>
<form method="post" id="tableForm"  action="userdelete.do">
<input type="hidden" value="" name="pageNo"/>
<input type="hidden" value="" name="queryName"/>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>用户编号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>性别</th>
			<th>出生年月</th>
			<th>邮箱</th>
			<th>部门名称</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${users }" var="user">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input id="ids" type="checkbox" name="ids" value="${user.id }"/></td>
			<td align="center">${user.id }</td>
			<td align="center">${user.loginname }</td>
			<td align="center">${user.realname }</td>
			<td align="center">${user.sex }</td>
			<td align="center">${user.birthday }</td>
			<td align="center">${user.email }</td>
			<td align="center">${user.deptname }</td>
			<td align="center">${user.enabledTxt }</td>
			<td align="center">
			<a href="userget.do?id=${user.id } " class="pn-opt">修改</a>
			<a href="userdelete.do?id=${user.id }"  onclick="return del()" class="pn-opt">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="page pb15" >

<input action="userdelete.do" id="ids" class="del-button" name="${user.id }" type="submit" value="批量删除"   onclick="if(!confirm('确定删除吗？')){return false; }"/>
</div>
<div class="page pb15"  style="float:right;">
<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		[${requestScope.currentPage }/${requestScope.pageCount }]
		<a href="userlist.do?currentPage=1&&name=${name }">首页</a>
		<c:if test="${requestScope.currentPage-1>0 }">
			<a href="userlist.do?currentPage=${requestScope.currentPage-1 }&&name=${requestScope.name}">上一页</a>
		</c:if>
		<c:if test="${requestScope.currentPage+1<=requestScope.pageCount }">
			<a href="userlist.do?currentPage=${requestScope.currentPage+1 }&&name=${requestScope.name}">下一页</a>
		</c:if>
		<a href="userlist.do?currentPage=${requestScope.pageCount }&&name=${requestScope.name}">尾页</a>
	</span>
</div>


<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>
</form>
<div class="page pb15"  style="float:right;margin:-15px 30px 0 0" >
	<form action="userlist.do?currentPage=${requestScope.currentPage}">转到第<input type="text" width="10px" name="currentPage"/>页<input type="submit" value="确定"/></form>
</div>
</div>
</body>
</html>