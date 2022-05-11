<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//뭘 update할 건데? - 내가 누른 num에 해당하는 거 - 그걸 어떻게 아는데
	//num값 가져와 - request.getparameter("num") 갖고 와
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

		//setString 맞아?, DB에서 num은 int잖아 할때 사용
		stmt.setInt(1, Integer.parseInt(num));
		//SELECT = Query() , INSERT, UPDATE, DELETE - Update()
		rs = stmt.executeQuery();
		
		//rs에 담긴 값들을 들고 오자.
		//title, content, writer
		if(rs.next()){

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
<!-- num값을 받아온 걸 넣어주자. 
사이트에는 출력되지 않아야 하므로 type="hidden"으로 바꿔준다.
넘겨받은 값은 pk, 그에 담긴 rs값들을 아래 value에 넣어준다. -->
	<form method="post" action="updateAct.jsp">
	<input type="hidden" name="num" value="<%=num %>">
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input name="title" class="form-control" value="<%=title %>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" class="form-control"><%=content %></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input name="writer" class="form-control" value="<%=writer %>"></td>
			</tr>
		</table>
		<div class="pull-right"><button class="btn btn-default">글수정</button></div>
	</form>
</div>
</body>
</html>