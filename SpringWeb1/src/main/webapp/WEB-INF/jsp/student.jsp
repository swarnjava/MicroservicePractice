<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function checkForm()
{
	var nm=document.getElementById("nm").value;
	var adr=document.getElementById("adr").value;
	var cont=document.getElementById("cont").value;
	var cig="OK";
	if(nm==""||nm==null)
		{
		cig="FAILED";
		document.getElementById("nm").focus();
		document.getElementById("messagediv").innerHTML="";
		document.getElementById("messagediv").innerHTML="Name Field is Mandatory";
		}
	else if(adr==""||adr==null)
		{
		cig="FAILED";
		document.getElementById("adr").focus();
		document.getElementById("messagediv").innerHTML="";
		document.getElementById("messagediv").innerHTML="Address Field is Mandatory";
		}
	else if(cont==""||cont==null)
		{
		cig="FAILED";
		document.getElementById("cont").focus();
		document.getElementById("messagediv").innerHTML="";
		document.getElementById("messagediv").innerHTML="Contact Field is Mandatory";
		}
	
	
	
	if(cig=="OK")
		{
		return true;
		}
	if(cig=="FAILED")
		{
		return false;
		}
}

</script>
</head>
<body>
<center>

<div id="messagediv">${message }</div>
<form:form action="saveStudent" modelAttribute="student" method="post" onsubmit="return checkForm()">
<form:hidden path="sid"/>
<input type="hidden" name="authtoken" value="${jwtToken }"/>
<table>
<tr><td>NAME:</td><td><form:input path="sname" id="nm"/></td></tr>
<tr><td>ADDRESS:</td><td><form:input path="saddr" id="adr"/></td></tr>
<tr><td>CONTACT:</td><td><form:input path="scontact" id="cont"/></td></tr>
<tr><td></td><td><input type="submit" value="Create Student"/></td></tr>
</table>
</form:form>
<br><br><br>
<table border="1" cellpadding="2" cellspacing="3">
<tr><td>NAME</td><td>ADDRESS</td><td>CONTACT</td><td>ACTION</td></tr>
<c:forEach items="${stdList }" var="std">
<tr>
<td>${std.sname }</td>
<td>${std.saddr }</td>
<td>${std.scontact }</td>
<td><a href="editStudent?sid=<c:out value="${std.sid}" />">Edit</a>
</c:forEach>
</table>
</center>
</body>
</html>