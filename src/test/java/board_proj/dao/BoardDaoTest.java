package board_proj.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.JdbcUtil;
import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test03SelectListCount() {
		System.out.println("test03SelectListCount");
		int res = dao.selectListCount();
		Assert.assertNotEquals(-1, res);
		System.out.println(res);
	}

	@Test
	public void test02SelectArticleList() {
		System.out.printf("%s()%n", "02testSelectArticleList");
		int page = 1;
		int limit = 10;
		List<BoardDTO> list = dao.selectArticleList(page, limit);
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
		System.out.println("============================================================================================================");
		dao.selectArticleList(2, 10).stream().forEach(System.out::println);
	}

	@Test
	public void test04SelectArticle() {
		System.out.println("test04SelectArticle");
		int board_num = 22;
		BoardDTO article = dao.selectArticle(board_num);
		Assert.assertNotNull(article);
		System.out.println(article);
	}

	@Test
	public void test05InsertArticle() {
		System.out.println("test05InsertArticle");
		BoardDTO newBoard = new BoardDTO(
				"김상건",
				"1234",
				"5시 퇴근가능할까?", 
				"불가능함",
				"test.txt"
						);
		int res = dao.insertArticle(newBoard);
		Assert.assertEquals(1, res);
		System.out.printf("InsertArticle >> " + newBoard);
		
	}

	@Test
	public void test10InsertReplyArticle() {
		System.out.println("test10InsertReplyArticle");
		BoardDTO replyArticle = new BoardDTO("유한솔", "1111", "앙데4", "절대루4", "");
		replyArticle.setBoard_re_ref(20);
		
		int res = dao.insertReplyArticle(replyArticle);
		Assert.assertEquals(1, res);
		System.out.printf("res >> " + res);
	}

	@Test
	public void test08UpdateArticle() {
		System.out.println("test08UpdateArticle");
		BoardDTO updateBoard = new BoardDTO(30, "수업시작 시간입니다.", "9시에 시작");
		int res = dao.updateArticle(updateBoard);
		Assert.assertEquals(1, res);
		System.out.printf("UpdateBoard >> " + updateBoard);
	}

	@Test
	public void test09DeleteArticle() {
		System.out.println("test07DeleteArticle");
		int board_num = dao.nextBoardNum() -1;
		int res = dao.deleteArticle(board_num);
		Assert.assertEquals(1, res);
		System.out.println(res);
	}
	

	@Test
	public void test06UpdateReadCount() {
		System.out.println("test06UpdateReadCount");
		int board_num = 22;
		int article = dao.updateReadCount(board_num);
		Assert.assertEquals(1, article);
		System.out.println(article);
	}

	@Test
	public void test07IsArticleBoardWriter() {
		System.out.println("test07IsArticleBoardWriter");
		int board_num = 22;
		boolean res = dao.isArticleBoardWriter(board_num, "1234");
		Assert.assertEquals(true, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void test01NextBoardNum() {
		System.out.println("test01NextBoardNum");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.printf("next Number >> " + res);
	}

}
