<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%--  <%@page import="java.util.*,mvcdemo.*" %> --%>
   
   <!-- using JSTL, we need not to import any packages -->
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a" %>
   
   
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tracking Students</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>



<%--Jsp scriplet <%
/* get the students from the request object sent by servlet */
List<Student> studentsdisplay=
			(List<Student>) request.getAttribute("studentlist");
%> --%>



<body>
<div id="wrapper">
<div id="header">
<h1>Students Data</h1>
</div>
</div>
<div id="container">
<div id="content">
<input type="button" value="Add Student" onclick="window.location.href='adding-students.jsp'; return false " class="add-button"/><br/>
<table border="1">
<tr>
<th>First Name </th>
<th>Last Name </th>
<th>Mail </th>
<th>Action</th>
</tr>


<%-- JSP Scriplet
<%for (Student temp:studentsdisplay) { %>
<tr>
<td><%=temp.getFirstname()%></td>
<td><%=temp.getLastname()%></td>
<td><%=temp.getEmail()%></td>
</tr>
<% } %> --%>

<a:forEach var="temp" items="${studentlist}">
<!-- setting link for each student -->
<a:url var="link" value="StudentController">
<a:param name="command" value="Load"></a:param>
<a:param name="studentid" value="${temp.id}"></a:param></a:url>
<a:url var="deletelink" value="StudentController">
<a:param name="command" value="delete"></a:param>
<a:param name="studentid" value="${temp.id}"></a:param></a:url>
<tr>
<td>${temp.firstname}</td>
<td>${temp.lastname}</td>
<td>${temp.email}</td>
<td><a href="${link}">Update</a>| 
<a href="${deletelink}" onclick="if(!(confirm('Sure to Delete?'))) return false">Delete</td>
</tr>
</a:forEach>
</table>
</div>
</div>
</body>
</html>