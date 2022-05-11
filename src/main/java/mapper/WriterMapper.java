package mapper;

//sql에서 import 해 올 것이 많으므로 줄이고자 '*' 사용
import java.sql.*;

import domain.BoardVO;

public class WriterMapper {

	public void insert(BoardVO vo) {
		
		//DriverManager.getConnection(url, user, pass) 값 String으로 입력
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		
		//Query문 작성(WriterAct.jsp의 쿼리문은 insert)
		String sql = " INSERT INTO board (title, content, writerDate) ";
		sql+= " VALUES (?, ?, ?, now()) ";
		
		//finally에서 stmt와 conn을 닫아주기 위해
		//Connection conn과 PreparedStatement stmt를 전역변수로 설정한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			//DB 저장
			//드라이버 로드 - url, user, pass 연결 - 쿼리준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "smart");
			stmt = conn.prepareStatement(sql);
			
			//Query문에 값 넣어줘야 하므로 setString 사용하여 값 넣어주기
			//vo값을 받아서 넣어주므로 vo.getXxx()사용
			//Query문에 넣어줘야 하는 값의 개수는 ?의 개수와 같다. (총 3개)
			//자신이 현재 어떤 쿼리문을 수행하는지, 무슨 페이지를 만드는지, 그 페이지에는 어떤 값을 넣어야 하는지에 대해 인지하자.
			//insert문 써놓고 read문 값 받는 멍청한 짓은 삼가자.
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getWriter());
			
			//INSERT, UPDATE, DELETE문은 executeUpdate()
			//SELECT문은 executeQuery()
			//Query문에 값을 입력받았으면 실행을 시켜주자.
			stmt.executeUpdate();
		} catch (Exception e) {
			//e.printStackTrace();
			e.getLocalizedMessage();
		} finally {
				try {
					//닫을 때는 먼저 연 것을 나중에 닫는다.
					//Connection conn을 먼저 호출했으므로 conn을 뒤에 닫아준다.
					if( stmt != null) stmt.close();
					if( conn != null) conn.close();
				} catch (SQLException e) {
					//e.printStackTrace();
					e.getLocalizedMessage();
				}
			
		}
	}

}
