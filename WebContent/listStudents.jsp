<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "javax.servlet.RequestDispatcher" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>


<a href="<%=request.getContextPath()%>/studentForm.jsp">Add Student</a>
<br>
<br>

<a id="list" href="<%=request.getContextPath()%>/list">Refresh List</a>

				
<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Age</th>
						<th>Branch</th>
						<th>Section</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
				


					<c:forEach 	var="student" items="${listStudents}">
					
					<c:if test="${student == null}">
					<%
					     RequestDispatcher rd = request.getRequestDispatcher("/list");
					     rd.forward(request, response);
					%>
					</c:if>
						<tr>
							<td><c:out value="${student.id}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.age}" /></td>
							<td><c:out value="${student.branch}" /></td>
							<td><c:out value="${student.section}" /></td>
							<td><a href="edit?id=<c:out value='${student.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${student.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>


</body>
</html>