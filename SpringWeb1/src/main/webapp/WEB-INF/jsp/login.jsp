<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="checkLogin" method="post" modelAttribute="student">
<table>
<tr><td>Name:</td><td><form:input path="sname"/></td></tr>
<tr><td>contact:</td><td><form:input path="scontact"/></td></tr>
<tr><td></td><td><input type="submit" value="Login"/></td></tr>

</table>
</form:form>
</body>
</html>