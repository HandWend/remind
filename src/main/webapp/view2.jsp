<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	request.setCharacterEncoding("UTF-8");
    
    String num = request.getParameter("num");
    
    String title = "";
    String content = "";
    String writer = "";

	//DB불러오기
	String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	String user = "root";
	String password = "smart";
	String sql = " SELECT * FROM board WHERE num = ? ";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn = DriverManager.getConnection(url, user, password);
		stmt = conn.prepareStatement(sql);
		//String으로 해도 나오긴 나온다 
		stmt.setString(1, num);
		//setInt로 하고 싶을 때 사용
		//stmt.setInt(1, Integer.parseInt(num));
		rs = stmt.executeQuery();
		//첫 행부터 다음 행에 해당하는 내용들을 들고 온다.
		//밑에서 close() 당하므로 변수에 담아주자.
		if (rs.next()) {
			title = rs.getString("title");
			content = rs.getString("content");
			writer = rs.getString("writer");
		}
	} catch (Exception e){
		e.getLocalizedMessage();
	} finally {
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch(Exception e){
			e.getLocalizedMessage();
		}
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<table class="table">
		<tr>
			<th>제목</th>
			<td><%=title %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=content %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=writer %></td>
		</tr>
	</table>
	<!-- 다시보기 -->
	<div class="pull-right"><a href="update.jsp?num=<%=num %>"><button class="btn btn-default">글수정</button></a></div>
</div>
</body>
</html>