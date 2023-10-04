<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지 게시판</title>
    <style>     
        h1 {
            text-align: center;
            color: #333;
        }

        .notice-list {
            list-style: none;
            padding: 0;
        }

        .notice-item {
            border: 1px solid #ddd;
            margin-bottom: 10px;
            padding: 15px;
            background-color: #fff;
            border-radius: 5px;
            transition: transform 0.2s;
        }


        .notice-title {
            font-size: 18px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .notice-date {
            font-size: 14px;
            color: #777;
        }

        .notice-content {
            font-size: 16px;
            color: #444;
        }
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<c:forEach items="${notice}" var="notice">
    <div class="container">
        <h1>공지 게시판</h1>
        <ul class="notice-list">
            <li class="notice-item">
            <input type="submit" name="delete" value="삭제" onClick="location.href='noticeDelete.do?num=${notice.num}'">
            <input type="submit" name="update" value="수정" onClick="location.href='noticeUpdate.do?ntitle=${notice.ntitle}'">
            <input type="button" value="목록" onClick="location.href='notice.do'">
				<table>
				<tr>
					<th>제목 : ${notice.ntitle }</th>
				</tr>
				<tr>
					<td>내용 : ${notice.ncontent }</td>
				</tr>
				</table>
            </li>
            <!-- 여러 개의 공지사항 아이템을 추가할 수 있습니다. -->
        </ul>
    </div>
</c:forEach>
<%@include file="../footer.jsp" %>
</body>
</html>