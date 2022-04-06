<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Servlet AcademicStandingCalculatorServlet</title>
	</head>
	<body>
		<form action="AcademicStandingCalculatorServlet" method="POST">
			<label for="courseGrade">Course Grade:</label>
			<select name="courseGrade">
				<option value="A_PLUS">A+ (90-100%)</option>
				<option value="A">A (80-89%)</option>
				<option value="B_PLUS">B+ (75-79%)</option>
				<option value="B">B (70-74%)</option>
				<option value="C_PLUS">C+ (65-69%)</option>
				<option value="C">C (60-64%)</option>
				<option value="D">D (50-59%)</option>
				<option value="F">F (0-49%)</option>
			</select>
			<br/>
			<input type="submit" name="actBtn" value="next" />
			<input type="submit" name="actBtn" value="done" />
		</form>
	</body>
</html>