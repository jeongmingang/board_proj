package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDTO;

public class BoardReplyServlce {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardReplyServlce() {
		dao.setCon(con);
	}
	
	public BoardDTO getArticle(int board_num) {
		return dao.selectArticle(board_num);
	}
	
	public boolean replyArticle(BoardDTO article) {
		return dao.insertReplyArticle(article)==1?true:false;
	}
}
