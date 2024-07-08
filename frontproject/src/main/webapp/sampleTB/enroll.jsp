<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
 <%@ page import="frontproject.vo.SampleTBVO" %>
  <%
 String msg=request.getParameter("msg");
 if(msg !=null && msg.equals("fail")){
	 if(msg.equals("fail")){
		 %>
		 <script>
		 alert("등록에 실패했습니다.");
		 </script>
		 <% 
	 }
 }

 %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>등록 페이지 입니다</h2>
<form action="enroll.do" method="post"><!--메소드가 다르면 가상경로 두번쓸수 있다 같은걸로 -->

<table border="1">
	<tbody>
		
		<tr>
			<th align="right">제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th align="right">작성자</th>
			<td><input type="text" name="writer"></td>
		</tr>
		
		<tr>
			<th align="right">내용</th>
			<td><input type="textarea" name="body"></td>
		</tr>
	</tbody>
<button type="submit">저장</button>
</form>
</body>
</html>