package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDTO;

public class BoardDaoImpl implements BoardDao{
	private static final BoardDaoImpl instance = new BoardDaoImpl();
	private Connection con;
	
	public static BoardDaoImpl getInstance() {
		return instance;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectListCount() {
		String sql = "select count(*) from board";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public ArrayList<BoardDTO> selectArticleList(int page, int limit) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT "  
				+ 	 "	  , BOARD_FILE, BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE "
				+ 	 "	from board " 
				+ 	 " order by BOARD_RE_REF desc, BOARD_RE_SEQ asc "
				+	 " limit ?, ?";
		int startRow = (page-1) * limit; //해당 페이지 시작 위치
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
					do {
						list.add(getBoardDTO(rs));
					}while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private BoardDTO getBoardDTO(ResultSet rs) throws SQLException {
		// BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
		int board_num = rs.getInt("BOARD_NUM");
		String board_name = rs.getString("BOARD_NAME");
		String board_pass = rs.getString("BOARD_PASS");
		String board_subject = rs.getString("BOARD_SUBJECT");
		String board_content = rs.getString("BOARD_CONTENT");
		String board_file = rs.getString("BOARD_FILE");
		int board_re_ref = rs.getInt("BOARD_RE_REF");
		int board_re_lev = rs.getInt("BOARD_RE_LEV");
		int board_re_seq = rs.getInt("BOARD_RE_SEQ");
		int board_readcount = rs.getInt("BOARD_READCOUNT");
		Date board_date = rs.getDate("BOARD_DATE");
		
		return new BoardDTO(board_num, board_name, board_pass, board_subject, board_content, board_file, board_re_ref, board_re_lev, board_re_seq, board_readcount, board_date);
	}

	@Override
	public BoardDTO selectArticle(int board_num) {
		String sql = "select * from board where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getBoardDTO(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int insertArticle(BoardDTO article) {
		String sql = "INSERT INTO board " 
				   + " (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF) "
				   + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		//BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextBoardNum());
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, nextNum);
			
//			pstmt.setInt(8, article.getBoard_re_lev());
//			pstmt.setInt(9, article.getBoard_re_seq());
//			pstmt.setInt(10, article.getBoard_readcount());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int insertReplyArticle(BoardDTO article) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateArticle(BoardDTO article) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int deleteArticle(int board_num) {
		String sql = "delete " 
				+    "  from board " 
				+  	 " where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateReadCount(int board_num) {
		String sql = "update board " 
				  +  "	set BOARD_READCOUNT = BOARD_READCOUNT + 1 " 
				  +  " where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean isArticleBoardWriter(int board_num, String pass) {
		String sql = "select * from board "
				+ 	 " where BOARD_NUM = ? "
				+ 	 "  and BOARD_PASS = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(BOARD_NUM) from board";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			if (rs.next()) {
				return rs.getInt(1)+1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
}

