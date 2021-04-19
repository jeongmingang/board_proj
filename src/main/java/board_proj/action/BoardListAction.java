package board_proj.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.dto.PageInfo;
import board_proj.service.BoardListService;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardListService service= new BoardListService();
		
		int page = 1;
		int limit = 10;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<BoardDTO> list = service.getArticleList(page, limit);
//		list.stream().forEach(System.out::println);
		
		request.setAttribute("articleList", list);
		
		//페이지 처리
		
		// 총 리스트 겟수
		int listCount = service.getListCount(); 
		// 21.0/5 =5
		int maxPage = (int) (Math.ceil((double)listCount/limit)); 
		// 1page 1~5, 2page 6~10, 11~5...
		// 11page 51~56,
		// [이전][1]  [2][3][4][5][6][7][9][10][다음]
		// [이전][11]  [12][13][14][15][16][17][19][20][다음]
		// [이전][21]  [22][23][24][25][26][27][29][30][다음]
//		page = 15;
//		int startPage = ((int)((double)page/limit+0.9)-1)*limit+1;
//		int startPage = (page/limit)*10+1;
		int startPage = (int)Math.floor(page/10)*limit+1;
		
		int endPage = startPage+limit-1;
		
		if(endPage>maxPage){
			endPage=maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		
		return forward;
	}
	
}
