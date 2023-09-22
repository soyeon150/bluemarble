<%@page import="com.five.dto.Bcategory"%>
<%@page import="com.five.dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="header.jsp" %>
    <title>메인 페이지</title>
    <link rel="stylesheet" type="text/css" href="board_list.css">
    <style>
        /* 글자 폰트 변경 */
        body {
            font-family: 'Nanum Gothic', sans-serif; /* 원하는 폰트로 변경 */
        }

        /* 페이지 스타일 추가 */
        .cta-button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }

        .cta-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <header>
        <h1>웹 마블 홈페이지 – 웹 마블 게임의 새로운 모험을 시작하세요!</h1>
    </header>
    <div class="container">
        <p>웹 마블 홈페이지는 웹 마블 게임의 열정적인 팬들을 위한 공간입니다. 현실과 가상이 만나는 이 곳에서는 웹 마블의 최신 소식과 게임 전략을 확인하실 수 있습니다. 게임 업데이트, 이벤트 소식, 그리고 다양한 컨텐츠를 편리하게 탐색하며 웹 마블 세계의 미지의 땅을 탐험하세요.</p>
        <p>웹 마블 애호가, 게임 전략가, 그리고 커뮤니티의 일원들을 위한 우리의 웹사이트에서는 다른 곳에서 찾을 수 없는 독특한 가치를 제공합니다. 실시간 업데이트와 화려한 그래픽, 직관적인 내비게이션으로 게임 세계로의 여정을 더욱 흥미롭게 만들어드립니다.</p>
        <p>지금 바로 웹 마블 홈페이지를 방문하고 게임 세계의 놀라운 여행을 시작해보세요! 우리와 함께 웹 마블의 모든 비밀과 재미를 즐겨보시기 바랍니다.</p>
        <p>먼저 저희와 함께 웹 마블의 세계로 들어가시려면 회원가입이 필요합니다.</p>
        <p>오른쪽 상단의 회원가입을 눌러 약관에 동의를 해주세요. 약관에 동의하시고 다음으로 넘어가시면 회원가입 절차가 자동으로 올라옵니다. 절차에 따라 필요한 정보를 입력하시고 회원가입을 누르시면 성공적으로 가입이 완료됩니다.</p>
        <h2>웹 마블 하는 법 소개</h2>
        <p>저희 마블에 기본 규칙입니다.</p>
        <ul>
            <li>게임 보드: 웹 마블은 보드 게임으로, 일반적으로 2~4명의 플레이어가 참여합니다. 게임 보드에는 여러 칸과 속성이 있으며, 플레이어는 주사위 굴리기로 보드를 이동합니다.</li>
            <li>목표: 웹 마블의 목표는 게임 보드를 돌며 소유한 땅에 건물을 지어 부자가 되는 것입니다. 모든 플레이어가 파산하면 게임에서 승리합니다.</li>
            <li>주사위 굴리기: 플레이어는 주사위를 굴려 나온 숫자만큼 보드를 이동합니다. 주사위를 굴린 후, 플레이어는 해당 위치에서 다음 행동을 선택합니다.</li>
        </ul>
        <a href="login.jsp" class="cta-button">지금 시작하기</a>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
