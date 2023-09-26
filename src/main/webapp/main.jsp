<%@page import="com.five.dto.Bcategory"%>
<%@page import="com.five.dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<title>웹 블루마블</title>
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
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="0" class="active"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="1"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="2"></button>
					</div>
					<div class="carousel-inner">	<!-- 이미지 목록 -->
						<div class="carousel-item active">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=165&pagenum=1"><img alt="" src="img/main/bluemarble_intro.png" width="90%"></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=166&pagenum=1"><img alt="" src="img/main/bannertest2.gif" width="90%"></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img alt="" src="img/main/bannertest3.gif" width="90%"></a>
						</div>
					</div>
					<!-- 왼쪽 이동 버튼 -->
					<button class="carousel-control-prev" type="button" data-bs-target="#mainslide" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</button>
					<!-- 오른쪽 이동 버튼 -->
					<button class="carousel-control-next" type="button" data-bs-target="#mainslide" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
					</button>			
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12">
						<% if(session.getAttribute("Login") != null) {%>
						<br><br>
						
						<a href="game/lobby.jsp"> <img id="start" alt="" src="/Five/img/main/start_button.gif" > </a>
				
						<%} else { %>
						<br><br>
						<img id="start" alt="" src="/Five/img/main/start_button.gif"  onclick="nonlogin()">
						
						<%} %>
					
						
					</div>
					<div class="col-md-12 text-center" style="font-weight: bold;">
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
				
		</div>
		
		
		</div>
	</div>
	
	<%@include file="footer.jsp" %>


	<script type="text/javascript" src="/Five/js/main.js"></script>

</body>
</html>