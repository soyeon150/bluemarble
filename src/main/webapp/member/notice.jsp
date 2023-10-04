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
	<style type="text/css">
	#start:hover {
	  -webkit-transform: scale(0.8);
	  -moz-transform: scale(0.8);
	  -ms-transform: scale(0.8);
	  -o-transform: scale(0.8);
	  transform: scale(0.8);
	}
	</style>
<title>공지사항</title>
<script>
</script>
</head>
<body>
	<%@include file="../header.jsp" %>
		
<div class="col-md-3  right_board">	<!-- 우측 메뉴 바 -->
	<div class="right_first">
		<div>	
		<h4 class="text-center"> 공지 </h4>
		<div class="right-info">
			<br>
			<input type="button" class="insert" value="등록" onClick="location.href='noticeInsert.do'">
			<c:forEach items="${notice}" var="notice">
				<ul>
					<li>
						<a href="nContentServlet?notice=${notice.ntitle }">${notice.ntitle }</a>
					</li>
				</ul>
			</c:forEach>
		</div>
		</div>
	</div>
</div>		
	
	
	
	<%@include file="../footer.jsp" %>


	<script type="text/javascript" src="js/main.js"></script>

</body>
</html>