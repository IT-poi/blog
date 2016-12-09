<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello</h2>
<h3>请填写以下信息</h3>
<div>
	<form action="<%=request.getContextPath()%>/managerAction/getInfo">
	<table>
		<tr>
			<td><lable>用户名</lable></td>
			<td><input name="number" type ="text" value=""/></td>
		</tr>
		<tr>
			<td><label>密码</label></td>
			<td><input name="password" type ="password"/></td>
		</tr>
		<tr><td colspan="2"><button type="submit">提交</button></button></td></tr>
	</table>
	</form>
</div>
</body>
</html>
