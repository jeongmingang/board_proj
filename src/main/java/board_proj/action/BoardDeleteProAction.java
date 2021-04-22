package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//boardDeletePro.do?board_num=26
		//hidden page=1
		//BOARD_PASS=aaaa
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));	
		String page = request.getParameter("page");
		String pass = request.getParameter("BOARD_PASS");
		
		BoardDeleteService service = new BoardDeleteService();
		
		ActionForward forward = null;
		
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		response.setContentType("text/html;charset=UTF-8");
		if(!isArticleWriter) {
//			SendMessage.sendMessage(response, "삭제할 권한이 없습니다.");
			SendMessage.sendMessage(response, "삭제할 권한이 없습니다.");
			return forward;
		}
		
		boolean isDeleteSuccess = service.removeArticle(board_num);
		response.setContentType("text/html;charset=UTF-8");
		if(!isDeleteSuccess) {
			SendMessage.sendMessage(response, "삭제실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardList.do?page=" + page);
			
		return forward;
	}
}
