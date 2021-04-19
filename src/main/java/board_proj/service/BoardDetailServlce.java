package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDTO;

public class BoardDetailServlce {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDetailServlce() {
		dao.setCon(con);
	}
	
	public BoardDTO getArticle(int board_num) {
		//조회수 증가
		dao.updateReadCount(board_num);
		//baord_num에 해당하는 BoardDTO return;
		return dao.selectArticle(board_num);
		
	}
}
