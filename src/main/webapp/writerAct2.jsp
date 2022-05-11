<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
//값 받기
//넘어온 값을 변수에 저장
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");

String sql = " INSERT INTO board (title, content, writer, writerDate) VALUES (?, ?, ?, now())";

String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
Connection conn = null;
PreparedStatement stmt = null;
//DB저장
try {
	//드라이버로드
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(url, "root", "smart");
	stmt = conn.prepareStatement(sql);

	stmt.setString(1, title);
	stmt.setString(2, content);
	stmt.setString(3, writer);
	stmt.executeUpdate();

} catch (Exception e) {
	e.getLocalizedMessage();
} finally {
	try {
		if (stmt != null)
	stmt.close();
	} catch (Exception e) {

	}
}

//PAGE 이동
response.sendRedirect("list.jsp");
%>