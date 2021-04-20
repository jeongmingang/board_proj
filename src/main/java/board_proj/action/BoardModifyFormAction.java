package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardModifyServlce;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
//		System.out.println("board_num >> " + board_num);
		BoardModifyServlce service = new BoardModifyServlce();
		
		BoardDTO article = service.getArticle(board_num);
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		System.out.println("article >> " + article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_modify.jsp");
		return forward;
	}

}
