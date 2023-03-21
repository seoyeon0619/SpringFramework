<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kosa.mycompany.guestbook.*" %>
<%@ page import="com.kosa.mycompany.common.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<%
String pg=request.getParameter("pg");
%>
<form name="myform" id="myform">
		<input type="hidden" name="pg" id="pg" value="<%=pg%>">

<!-- 메뉴 -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="/myhome2/images/img_avatar1.png" alt="Avatar Logo" 
         style="width:40px;" class="rounded-pill"> 
    </a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>  
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Link</a></li>
            <li><a class="dropdown-item" href="#">Another link</a></li>
            <li><a class="dropdown-item" href="#">A third link</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<%
List<GuestbookDto> list = (List<GuestbookDto>)request.getAttribute("list");
int totalCnt = (Integer)request.getAttribute("totalCnt");
%>
<div class="container mt-3">
  <h3>Navbar With Dropdown</h3>
  <p>This example adds a dropdown menu in the navbar.</p>
  
   <div class="input-group mt-3 mb-3">
	  <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
	    Dropdown button
	  </button>
	  <ul class="dropdown-menu">
	    <li><a class="dropdown-item" href="#">Link 1</a></li>
	    <li><a class="dropdown-item" href="#">Link 2</a></li>
	    <li><a class="dropdown-item" href="#">Link 3</a></li>
	  </ul>
	  <input type="text" class="form-control" placeholder="Username">
	  <div class="input-group-append">
	  	<button class="btn btn-primary" type="button" >GO</button>
	  </div>
	</div>

   <table class="table">
   	<colgroup>
   		<col width="8%">
   		<col width="*%">
   		<col width="12%">
   		<col width="12%">
   		<col width="8%">
   		<col width="8%">
   	</colgroup>
    <thead class="table-dark">
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>내용</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    	<%for(int i=0; i<list.size(); i++){
    		GuestbookDto dto = list.get(i);
    	%>
      <tr>
        <td><%=dto.getSeq()%></td>
        <td><a href="<%=request.getContextPath()%>/guestbook/view?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></td>
        <td><%=dto.getWriter()%></td>
        <td><%=dto.getContents()%></td>
        <td><%=dto.getWdate()%></td>
        <td><%=dto.getHit()%></td>
      </tr>
      <%} %>
      
    </tbody>
  </table>
  
 </div>
 <div class="container" style="text-align:right">

 	<%=Pager.makeTag(request, totalCnt, 10) %>
</div>

 <div class="container" style="text-align:right">
	<button type="button" class="btn btn-primary" onclick="goWrite()">글쓰기</button>
 </div>

</form>
</body>
<script>
function goPage(pg)
{
	document.getElementById("pg").value = pg;
	var frm = document.myform;
	frm.action = "<%=request.getContextPath()%>/guestbook/list";
	frm.submit();
}

function goWrite()
{
	var frm = document.myform;
	frm.action = "<%=request.getContextPath()%>/guestbook/write";
	frm.method = "post";
	frm.submit();
}
</script>
</html>