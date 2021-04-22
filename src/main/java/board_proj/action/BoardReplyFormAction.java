package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardReplyServlce;

public class BoardReplyFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
//		System.out.println("board_num > " + board_num + " page > " + page);

		BoardReplyServlce service = new BoardReplyServlce();
		
		BoardDTO article = service.getArticle(board_num);
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
//		System.out.println("article > " + article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_reply.jsp");
		
		return forward;
	}

}
