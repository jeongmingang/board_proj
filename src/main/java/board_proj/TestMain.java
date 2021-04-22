package board_proj;

public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = "board_proj.Sum";
		
		Class<?> cls = Class.forName(className); // 임포트 한것과 비슷
		Sum s = (Sum) cls.newInstance();
		
		s.add(5, 3);
		
		Sum s2 = new Sum();	// newInstance() 와 같은 기능
		s2.add(4, 2);
	}

}
