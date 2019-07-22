<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
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
<title>栏目列表</title>
</head>
<body>

<script type="text/javascript">
	function del(){
	 	var con=confirm("是否删除该栏目");
	 	if (con) {
			return true;
		} else {
			return false;
		}
	}
	$(function(){
		$("#zxc").click(function(){
			var likeName=$("#likeName").val();
		
			location.href="tcha.do?name="+likeName;
		});
		
	});
</script>

<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 列表  </div>
	<form class="ropt">
		<input  type="text" name="name" id="likeName" class="" value="${name }" placeholder="模糊查询......."/>
		<input type="button" value="查询" id="zxc" />
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='tchaaddget.do'"/>
	</form>
	<div class="clear">
		
	</div>
</div>
<div class="body-box">
<input type="hidden" name="pageNo" value=""/>
<form method="post" id="tableForm">
<input type="hidden" value="" name="pageNo"/>
<input type="hidden" value="" name="queryName"/>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>栏目编号</th>
			<th>栏目名</th>
			<th>上级栏目</th>
			<th>栏目级别</th>
			<th>是否叶子</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${tchas }" var="tcha">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="73"/></td>
			<td align="center">${tcha.id }</td>
			<td align="center">${tcha.cname }</td>
			<td align="center">${tcha.pname }</td>
			<td align="center">${tcha.strlev }</td>
			<td align="center">${tcha.strIsleaf }</td>
			<td align="center">
			<a href="tchaget.do?id=${ tcha.id } " class="pn-opt">修改</a>
			<a href="tchadelete.do?id=${ tcha.id } " onclick="return del()" class="pn-opt">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="page pb15" >

	<input action="tchadelete.do" id="ids" class="del-button" name="${tcha.id }" type="submit" value="批量删除"   onclick="if(!confirm('确定删除吗？')){return false; }"/>
</div>
<div class="page pb15"  style="float:right;">
<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		
		[${requestScope.currentPage }/${requestScope.pageCount }]
		<a href="tcha.do?currentPage=1&&name=${name }">首页</a>
		<c:if test="${requestScope.currentPage-1>0 }">
			<a href="tcha.do?currentPage=${requestScope.currentPage-1 }&&name=${requestScope.name}">上一页</a>
		</c:if>
		<c:if test="${requestScope.currentPage+1<=requestScope.pageCount }">
			<a href="tcha.do?currentPage=${requestScope.currentPage+1 }&&name=${requestScope.name}">下一页</a>
		</c:if>
		<a href="tcha.do?currentPage=${requestScope.pageCount }&&name=${requestScope.name}">尾页</a>
	</span>
</div>
<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>
</form>
</div>
</body>
</html>