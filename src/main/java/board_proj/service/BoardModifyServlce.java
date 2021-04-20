package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDTO;

public class BoardModifyServlce {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardModifyServlce() {
		dao.setCon(con);
	}
	
	public BoardDTO getArticle(int board_num) {
		return dao.selectArticle(board_num);
		
	}
}
