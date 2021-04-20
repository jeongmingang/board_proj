package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardModifyProServlce;

public class BoardModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));	
		String pass = request.getParameter("BOARD_PASS");
		
		String page = request.getParameter("page");
		request.setAttribute("page", page);
		
		BoardModifyProServlce service = new BoardModifyProServlce();
		
		ActionForward forward = null;
		
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		response.setContentType("text/html;charset=UTF-8");
		if(!isArticleWriter) {
			sendMessage(response, "수정할 권한이 없습니다.");
			return forward;
		}
		
		BoardDTO article = new BoardDTO();
		article.setBoard_num(board_num);
		
		String board_subject = request.getParameter("BOARD_SUBJECT");
		article.setBoard_subject(board_subject);
		
		String board_content = request.getParameter("BOARD_CONTENT");
		article.setBoard_content(board_content);
		
		boolean isModifySuccess = service.modifyArticle(article);
		response.setContentType("text/html;charset=UTF-8");
		if(!isModifySuccess) {
			sendMessage(response, "수정 실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardDetail.do?board_num=" + board_num + "&page=" + page);
			
		return forward;
	}

	private void sendMessage(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("history.back()");
		out.println("</script>");
		out.close();
	}

}
