<%@page import="com.five.dto.Bcategory"%>
<%@page import="com.five.dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="../header.jsp" %>
    <title>메인 페이지</title>
    <link rel="stylesheet" type="text/css" href="board_list.css">
    <style>
        /* 글자 크기와 칸 간격 조정 */
        body {
            font-family: 'Nanum Gothic', sans-serif; /* 원하는 폰트로 변경 */
            font-size: 16px; /* 글자 크기 조절 */
            line-height: 1.5; /* 줄간격 조절 */
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

        /* 팁과 노하우 스타일 */
        .tip {
            margin-bottom: 20px;
            
        }
        
    </style>
</head>
<body>
    <header>
        <h1>팁과 노하우</h1>
        
    </header>
    <div class="container">
        <!-- 팁과 노하우 내용 -->
        <div class="tip">
            <h3>1. 자원 효율적 활용</h3>
            <p>게임에서 자원은 중요합니다. 턴마다 어떤 자원을 사용할지 신중하게 결정하세요. 예를 들어, 빌딩을 업그레이드하거나 새로운 땅을 구매할 때 어떤 자원을 사용해야 하는지 고려해 보세요.</p>
        </div>
        <div class="tip">
            <h3>2. 땅의 전략적 구매</h3>
            <p>게임 보드의 어떤 땅을 구매할지를 결정할 때, 그 땅이 어떤 자원을 생산하는지, 빌딩을 어떻게 업그레이드할 수 있는지를 고려하세요. 일정한 자원 생산을 보장하는 땅을 선택하는 것이 중요합니다.</p>
        </div>
        <div class="tip">
            <h3>3. 상대 플레이어 분석</h3>
            <p>경쟁 상대 플레이어들을 분석하고, 그들의 전략을 파악하세요. 상대 플레이어들이 어떤 땅을 주로 선택하고 있는지, 어떤 전략을 사용하는지를 알면 대응 전략을 세울 수 있습니다.</p>
        </div>
        <div class="tip">
            <h3>4. 이벤트 참여</h3>
            <p>게임 내 이벤트에 주기적으로 참여하세요. 이벤트에서는 게임 내 혜택을 얻을 수 있으며, 게임을 빠르게 진행하는 데 도움이 됩니다.</p>
        </div>
        <div class="tip">
            <h3>5. 팀 플레이</h3>
            <p>게임에서 다른 플레이어와 팀을 이루는 것이 가능하다면, 팀원들과의 협력을 강화하세요. 팀원들과 자원을 공유하고 전략을 공유하여 승리에 한 발짝 더 가까워집니다.</p>
        </div>
        <div class="tip">
            <h3>6. 주사위 관리</h3>
            <p>주사위 굴리기는 게임에서 중요한 요소 중 하나입니다. 주사위 숫자에 따라 다음 행동을 결정할 때, 미리 계획을 세우고 어떤 땅에 가야 하는지를 고려하세요.</p>
        </div>
        <div class="tip">
            <h3>7. 건물 업그레이드</h3>
            <p>건물 업그레이드에 대한 전략을 가지세요. 어떤 건물을 먼저 업그레이드할지, 어떤 건물이 높은 수익을 가져다주는지를 파악하고 업그레이드하세요.</p>
        </div>
        <div class="tip">
            <h3>8. 커뮤니티 참여</h3>
            <p>게임의 커뮤니티에 참여하여 다른 플레이어들과 정보를 공유하고 토론하세요. 다른 플레이어들의 경험과 조언을 듣는 것이 도움이 됩니다.</p>
        </div>
        <div class="tip">
            <h3>9. 자원 추적 도구 활용</h3>
            <p>게임을 더 효율적으로 관리하기 위해 자원 추적 도구를 활용하세요. 자원의 생산과 사용을 모니터링하고 최적의 결정을 내릴 수 있습니다.</p>
        </div>
       <a href="../main.jsp" class="cta-button">메인으로 돌아가기</a>


    </div>
    <%@include file="../footer.jsp" %>
</body>
</html>
