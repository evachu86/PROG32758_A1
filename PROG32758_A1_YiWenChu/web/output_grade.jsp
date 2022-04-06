<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" session="true"%>
<%@ page import="java.util.ArrayList,java.util.List,edu.academicCal.Grade" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Servlet AcademicStandingCalculatorServlet</title>
	</head>
	<body>
		<h2>Grades</h2>
		<ul>
		<%
			Object grades = session.getAttribute("gradeList");
			if(grades != null) {
				List<Grade> gradeList = (ArrayList<Grade>)grades; 
				for(Grade grade: gradeList) {
					out.println("<li>"+grade.toString()+"</li>");
				}	
			}
		%>
		</ul>
		
		<h1>Your Academic Standing is: ${stdMsg}</h1>
		<a href="index.html">start over</a>
	</body>
</html>