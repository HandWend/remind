package mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import domain.BoardVO;

public class ListMapper {

	public Collection<BoardVO> read() {

		// DriverManager.getConnection(url, user, pass) 값 String으로 입력
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";

		// Query문 작성(list에서는 읽어와야 하니까 SELECT문을 사용)
		// 의뢰인이 원한 내용은 최근 작성글이 맨 위에 올라오게 해야 하므로 ORDER BY를 num값으로 내림차순 추가
		String sql = " SELECT * FROM board ORDER BY num desc ";

		// finally에서 stmt와 conn을 닫아주기 위해
		// Connection conn과 PreparedStatement stmt를 전역변수로 설정한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 이해부족(2) - collection, arraylist
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		
		try {
			// DB 저장
			// 드라이버 로드 - url, user, pass 연결 - 쿼리준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "smart");
			stmt = conn.prepareStatement(sql);

			// INSERT, UPDATE, DELETE문은 executeUpdate()
			// SELECT문은 executeQuery()
			// executeQuery()값은 Resultset 객체를 반환받는다. 
			// 그러므로 Resultset 객체의 변수명인 rs에 반환받은 값을 넣어준다.
			rs = stmt.executeQuery();
			
			// 반환받은 값들을 한 행씩 출력하겠다.
			// 행에 들어가는 값들은 num, title, writer, writerDate 4가지.
			while (rs.next()) {
				vo = new BoardVO();
				//vo에 num, title, writer, writerDate 값을 set해주고 한 행씩 뽑아낸다.
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				//BoardVO에서 writerDate의 값을 timestamp로 받겠다고 작성함.
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				
				//set한 값들을 list에 담아준다.
				list.add(vo);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			e.getLocalizedMessage();
		} finally {
			try {
				// 닫을 때는 먼저 연 것을 나중에 닫는다.
				// writerMapper: Connection conn을 먼저 호출했으므로 conn을 뒤에 닫아준다.
				// ListMapper: Resultset rs - PreparedStatement stmt - Connection conn 순으로 닫아준다.
				if (rs != null) 
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				e.getLocalizedMessage();
			}
		}
		return list;
	}

}
