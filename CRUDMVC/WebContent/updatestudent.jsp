<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<body>
<div id="wrapper">
<div id="header">
<h1>Tracking students</h1>
</div>
</div>
<div id="container">
<h1>Update students</h1>
<form action="StudentController" >
<input type="hidden" name="command" value="update">
"<input type="hidden" name="studentid" value="${updating.id}"/>
<table>
<tbody>
<tr>
<td><label>First Name : </label></td>
<td><input type="text" name="firstname" value="${updating.firstname}"><br/></td>
</tr>
<tr>
<td><label>Last Name : </label></td>
<td><input type="text" name="lastname" value="${updating.lastname}"><br/></td>
</tr>
<tr>
<td><label>Email : </label></td>
<td><input type="text" name="email" value="${updating.email}"><br/></td>
<tr>
<td><input type="submit" value="Save" class="save"></td>
</tr>
</tbody>
</table>
</form>
<div style="clear:both:"></div>
<p>
<a href="StudentController">Back to list</a>
</div>
</body>
</html>