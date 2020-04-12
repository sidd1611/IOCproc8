<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- use "choose" tag for "if-else" condition -->
<c:choose>                                 <!-- ${} as part of Expression Language will be used to get data from 
                                                different scopes:  page, request, session, application-->
	<c:when test="${!empty listDTO}"> <!-- for 'if' i.e. when condition is true-->
	 <table>
	  <tr bordercolor="red">
	   <th>srNo</th><th>EmpNo</th><th>EmpName</th><th>Desg</th><th>Salary</th><th>DeptNo</th>
	  </tr>
	  <!-- displaying records, elements of listDTO usinf forEach tag, similar to enhanced-for loop of java -->
	  <c:forEach var="emp" items="${listDTO}">   <!-- var attribute value represent counter variable of loop -->
	   <tr bgcolor="cyan">
	     <td>${emp.srNo}</td>   <!-- These statements will act like a call to getter method -->
	     <td>${emp.empNo}</td>
	     <td>${emp.ename}</td>
	     <td>${emp.job}</td>
	     <td>${emp.sal}</td>
	     <td>${emp.deptNo}</td>
	   </tr>
	  </c:forEach>  
	 </table>
	</c:when>
	<c:otherwise> <!-- for 'else' i.e. when condition is false -->
		<h1 style="color:red;text-align:center">Records Not Found</h1>
	</c:otherwise>
</c:choose>

<br/><br/>
<a href="welcome.htm">home</a>
<!-- <a href="welcome.htm"><img src="images/home.png" widht="50" height="50"></a> --><!-- images are generally kept in src/main/webapp/images folder -->