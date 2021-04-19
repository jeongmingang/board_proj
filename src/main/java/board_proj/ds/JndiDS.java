package board_proj.ds;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JndiDS {
	private static DataSource ds;

	private JndiDS() {}
	
	static {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/board_proj");
			System.out.println("ds :"  + ds);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
