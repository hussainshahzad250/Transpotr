<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<center><font color="green" size='4'>${files}</font></center>
	 <c:forEach var="file" items="${msg}">
		ID:   <c:out value="${file.id}"/><br>
		Source State  <c:out value="${file.state}"/><br>
		Source City   <c:out value="${file.state1}"/><br>
		Destination State   <c:out value="${file.state2}"/><br>
		Destination City   <c:out value="${file.state3}"/><br>
		Distance  <c:out value="${file.distance}"/><br>
		Amount   <c:out value="${file.rate}"/><br>
		Vehicle   <c:out value="${file.state4}"/><br>
	</c:forEach>  
	
<%-- 	<c:forEach items="${list.map}" var="map" varStatus="status">
		<tr>
			<td>${map.key}</td>
			<td><input name="map['${map.key}']" value="${map.value}"/></td>
		</tr>
	</c:forEach> --%>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</body>
</html>