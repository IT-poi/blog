<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello</h2>
<h3>请填写以下信息</h3>
<div>
	<form action="<%=request.getContextPath()%>/test/commit">
	<table>
		<tr>
			<td><lable>文章标题</lable></td>
			<td><input name="article.title" type ="text" value="" /></td>
		</tr>
		<tr>
			<td><label>文章内容</label></td>
			<td><input name="article.content" type ="text" /></td>
		</tr>
		<tr>
			<td><lable>文章概要</lable></td>
			<td><input name="article.brief" type ="text" value="" /></td>
		</tr>
		<tr>
			<td><lable>图片url</lable></td>
			<td><input name="article.imgUrl" type ="text" value="" /></td>
		</tr>
		<tr>
			<td><lable>文章浏览量</lable></td>
			<td><input name="article.pageViews" type ="text" value="" /></td>
		</tr>
		<tr>
			<td><lable>文章标签</lable></td>
			<td><input name="article.label" type ="text" value="" /></td>
		</tr>
		<tr>
			<td><lable>作者</lable></td>
			<td><input name="article.author" type ="text" value="" /></td>
		</tr>
		<tr><td colspan="2"><button type="submit">提交</button></button></td></tr>
	</table>
	</form>
</div>
</body>
</html>
