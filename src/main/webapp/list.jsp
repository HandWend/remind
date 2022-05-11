<%@page import="java.util.Iterator"%>
<%@page import="domain.BoardVO"%>
<%@page import="java.util.Collection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<BoardVO> list = (Collection)request.getAttribute("list");
	
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
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
<%
Iterator<BoardVO> it = list.iterator();
while(it.hasNext()){
	BoardVO vo = it.next();
%>		
			<tr>
				<td><%=vo.getNum() %></td>
				<td><a href="view?num=<%=vo.getNum() %>"><%=vo.getTitle() %></a></td>
				<td><%=vo.getWriter() %></td>
				<td><%=vo.getWriterDate() %></td>
			</tr>
<%

}
%>			
		</tbody>
	</table>
	<div class="pull-right"><a href="writer" class="btn btn-default">글쓰기</a></div>
</div>
</body>
</html>
