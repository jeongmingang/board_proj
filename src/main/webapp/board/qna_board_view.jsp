<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MVC 게시판 상세보기</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/qna_board_view.css">
</head>
<body>
<%-- ${page } <br> ${article } --%>
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<article id="basicInfoArea">
			<span id="title">제 목 : ${article.board_subject}</span>
			<span id="file">
				첨부파일 :
				<c:if test="${article.board_file ne null}">
					<a href="file_down.do?downFile=${article.board_file ne null}"> ${article.board_file}	</a>
				</c:if>
			</span>
		</article>
		<article id="articleContentArea">
			${article.board_content}
		</article>
	</section>
	<section id="commandList">
		<a href="boardReplyForm.do?board_num=${article.board_num}&page=${page}"> [답변] </a>
		<a href="boardModifyForm.do?board_num=${article.board_num}&page=${page}"> [수정] </a> 
		<a href="boardDeleteForm.do?board_num=${article.board_num}&page=${page}"> [삭제] </a> 
		<a href="boardList.do?page=${page}"> [목록]</a>&nbsp;&nbsp;
	</section>
</body>
</html>