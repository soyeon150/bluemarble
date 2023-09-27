<%@page import="com.five.dao.Member_Dao"%>
<%@page import="com.five.dto.Bcategory"%>
<%@page import="com.five.dto.Notice"%>
<%@page import="com.five.dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>공지 수정</title>
</head>
<body>
<%@include file="../header.jsp" %>
<form action="noticeUpdate.do" method="post">
<c:forEach items="${notice}" var="notice">
	<table>
		<tr>
			<td>제목 : </td>
			<td><input type="text" name="ntitle" value="${notice.ntitle }"></td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td><textarea rows="20" cols="100" name="ncontent">${notice.ncontent }</textarea></td>
		</tr>
	</table>
<input type="submit" value="완료">
<input type="hidden" name="t" value="${notice.ntitle }">
</c:forEach>
</form>
<%@include file="../footer.jsp" %>

</body>
</html>