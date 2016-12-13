<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello</h2>
<h3>请填写以下信息</h3>
<div>
	<form action="<%=request.getContextPath()%>/ArticleAction/getArticle",method="get">
	<table>
		<tr>
			<td><lable>文章id</lable></td>
			<td><input name="articleId" type ="text" value=""/></td>
		</tr>
		<tr><td colspan="2"><button type="submit">提交</button></button></td></tr>
	</table>
	</form>
</div>
</body>
</html>
