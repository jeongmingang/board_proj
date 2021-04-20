<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MVC 게시판 글 목록</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/qna_board_list.css" >
</head>
<body>
	<!-- 게시판 리스트 -->
	<section id = "listForm">
	<h2>글목록</h2>
		<h4><a href="boardWriteForm.do">게시판 글쓰기</a> </h4>
		<table>
			 <tr id="tr_top">
				 <td>번호</td>
				 <td>제목</td>
				 <td>작성자</td>
				 <td>날짜</td>
				 <td>조회수</td>
			 </tr>
			 <c:choose>
			 	<c:when test="${fn:length(articleList) != 0 && pageInfo.listCount > 0 }">
				 <c:forEach var="list" items="${articleList }">
				 	<tr>
					 	<td>${list.board_num } </td>
					 	<td>
					 	<a href="boardDetail.do?board_num=${list.board_num }&page=${pageInfo.page}" >${list.board_subject} </a>
					 	</td>
					 	<td>${list.board_name} </td>
					 	<td>${list.board_date } </td>
					 	<td>${list.board_readcount } </td>
				 	</tr>
				 </c:forEach>
				 </c:when>
				 <c:otherwise>
				 	<tr><td colspan="5">등록된 글이 없습니다.</td></tr>
				 </c:otherwise>
			 </c:choose>
		</table>
		
		${pageInfo}
		
		<section id="pageList">
			<c:choose>
				<c:when test="${pageInfo.page<=1}">
				<span>[이전]</span>&nbsp;
				</c:when>
				<c:otherwise>
				<a href="boardList.do?page=${pageInfo.page-1}">[이전]</a>&nbsp;&nbsp;
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="a" begin="${pageInfo.startpage}" end="${pageInfo.endpage}">
			 <c:choose>
				<c:when test="${a==pageInfo.page}">
				 <span>[${a}]</span>
				</c:when>
				<c:otherwise>
				<a href="boardList.do?page=${a}">[${a}]</a>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${pageInfo.page >=pageInfo.maxpage}">
				<span>[다음]</span>&nbsp;
				</c:when>
				<c:otherwise>
				&nbsp;&nbsp;<a href="boardList.do?page=${pageInfo.page+1}">[다음]</a>
				</c:otherwise>
			</c:choose>
		</section>
</section>
</body>
</html>