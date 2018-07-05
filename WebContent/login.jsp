<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script type="text/javascript">
	function _change(){
		var imgEle = document.getElementById("img");
		imgEle.src="VerifyServlet?a=" + new Date().getTime();
	}
</script>
</head>
<body>
<%
	String username="";
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c: cookies) {
			if ("username".equals(c.getName())){
				username = c.getValue();
			}
		}
	}
	String msg=(String) request.getAttribute("msg");
	if (msg != null){
%>
	<font color="red"><b><%=msg %></b></font>
<%} %>
	<form action="/LoginServlet" method="post">
		用户名：<input type="text" name="username" value="<%= username%>"></br>
		密码：<input type="password" name="password" ></br>
		验证码：<input type="text" name="code" size="4" />
		<img id="img" src="/VerifyServlet" />
		<a href="javascript: _change()">换一张</a></br>
		<input type="submit" name="submit" value="提交">
	</form>
</body>
</html>