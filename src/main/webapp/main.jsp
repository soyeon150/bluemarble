<%@page import="com.five.dto.Bcategory"%>
<%@page import="com.five.dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<title>웹 블루마블</title>
<link href="/Five/css/main.css" rel="stylesheet">
</head>
<body>

	<%@include file="header.jsp" %>
	
	
	<% 
	int category_notice = 1;
	int category_tip =  2;
	int category_qna = 3;
	int category_free = 4;
	int offernum= 5;
	int hitnum=10;
	//Bcategory bcategory = new Bcategory();

	// 메인 페이지 view 용 게시판 호출
	// 1. 모든 게시물  호출 
		// 메인게시판 4개 최신글
 
 		
	%>
	
	<div class="container my-3">
		<div class="row">
			<div class="col-md-9">
				<div id="mainslide" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">	
					<!-- 슬라이드 하단 위치 버튼 -->
					<div  id ="slide_bottom"class="carousel-indicators">
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="0" class="active"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="1"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="2"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="3"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="4"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="5"></button>
					</div>
					<div id="banner_img" class="carousel-inner">	<!-- 이미지 목록 -->
						<div class="carousel-item active">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=165&pagenum=1"><img class="static" src="img/main/bluemarble_intro.png" width="85%">
							<img class="move" src="img/main/banner1.gif" width=85%></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=166&pagenum=1"><img class="static" src="img/main/notice.png" width="85%">
							<img class="move" src="img/main/notice.gif" width=85%></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img class="static" src="img/main/tip.png" width="85%">
							<img class="move" src="img/main/tip.gif" width=85%></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img class="static" src="img/main/qna.png" width="85%">
							<img class="move" src="img/main/qna.gif" width=85%></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img class="static" src="img/main/free.png" width="85%">
							<img class="move" src="img/main/free.gif" width=85%></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img class="static" src="img/main/rank.png" width="85%">
							<img class="move" src="img/main/rank.gif" width=85%></a>
						</div>
					</div>
						<div class="slide_icon" id="slide_icon">
					<!-- 왼쪽 이동 버튼 -->
					<button  class="carousel-control-prev" type="button" data-bs-target="#mainslide" data-bs-slide="prev">
						<span id="left_slide" class="carousel-control-prev-icon"></span>
					</button>
					<!-- 오른쪽 이동 버튼 -->
					<button  class="carousel-control-next" type="button" data-bs-target="#mainslide" data-bs-slide="next">
						<span id="right_slide" class="carousel-control-next-icon"></span>
					</button>	
						</div>		
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12">
						<% if(session.getAttribute("Login") != null) {%>
						<br><br>
						
						<a href="game/lobby.jsp"> 
				
						<img id="start_2" src="/Five/img/main/start_button.gif" >
						</a>
				
						<%} else { %>
						<br><br>
					
						<img id="start_2" src="/Five/img/main/start_button.gif" onclick="nonlogin()">
						
						<%} %>
					
						
					</div>
					<div id ="button_text" class="col-md-12 text-center" style="font-weight: bold;">
						<% if(session.getAttribute("Login") != null) {%>
						<%=account.getAc_nickname()%>님 환영 합니다
						<%} else { %>
						환영합니다. <br>
						로그인을 해 주세요
						<%} %>
					</div>
				</div>
			</div>
		</div>
		
		
				
		
		
		<div class="col-md-3  right_board">	<!-- 우측 메뉴 바 -->
				<div class="right_first">
				<div>
					
					<h4 class="text-center"> 웹 마블 일정 모음 </h4>
				</div>
				<div class="right-info">
					<br>
					<ul style="font-size : 22px;  ">
						<li> 09-22 : main page 완성  </li>
						<li> 09-25 : game page 완성 </li>
						<li> 09-26 : 오픈 </li>
					
					</ul>
					
				</div>
				</div>
				
				<div class="right_second">
				
				     </div>
				     <br>
				     
				     
					
												
				    
				
				</div>
				
				</div>
				

	<%@include file="footer.jsp" %>


	<script type="text/javascript" src="/Five/js/main.js"></script>

</body>
</html>