<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MVC 게시판 삭제</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/qna_board_delete.css">
</head>
<body>
${page }<br>${board_num }

	<section id="passForm">
		<form name="deleteForm" 
		action="boardDeletepro.do?board_num=${board_num}" method="post">
		<input type="hidden" name="page" value="${page }"/>
		<fieldset>
		<legend>게시글 삭제</legend>
			<ul>
				<li>
					<label for="pass">글 비밀번호 : </label>
					<input name="BOARD_PASS" type="password" size="20" id="pass" autofocus> 
				</li>
				<li id="btn">
					<input type="submit" value="삭제"/>&nbsp;&nbsp;
					<input type="button" value="돌아가기" onClick ="javascript:history.go(-2)"/>
				</li>
			</ul>
		</fieldset>
		</form>
	</section>
</body>
</html>