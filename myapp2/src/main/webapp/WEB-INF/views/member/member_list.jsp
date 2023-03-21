<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kosa.mycompany.common.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  
</head>
<body>
<% 
String pg=request.getParameter("pg"); 
if(pg==null || pg.equals(""))
	pg="0";
%>
<form name="myform" id="myform">
	<input type="hidden" name="pg" id="pg" value="<%=pg %>" >

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
int totalCnt = (Integer)request.getAttribute("totalCnt");
%>


<div class="container mt-3">
  <h3>회원목록</h3>
  
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

   <table class="table" id="mytable">
    <thead class="table-dark">
    	<colgroup>
	    	<col width="8%">
	    	<col width="20%">
	    	<col width="*%">
	    	<col width="20%">
	    	<col width="20%">
    	</colgroup>
      <tr>
        <th>No.</th>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
      </tr>
    </thead>
    <tbody>
    	
    </tbody>
  </table>
 </div>
 <div class="container" style="text-align:right">
  <%=Pager.makeTag(request, totalCnt, 3) %>
</div>

 <div class="container" style="text-align:right">
	<button type="button" class="btn btn-dark">🤍</button>
 </div>
</form>
</body>
<script>
	$(()=>{
		load_data();
	})
	
	function goPage(pg)
	{
		$("#pg").val(pg);
		$("#myform").prop("action", "<%=request.getContextPath()%>/member/member_list");
		$("#myform").submit();
		
	}
	
	function load_data()
	{
		let pg = $("#pg").val(); // 바뀐 페이지값 읽어와서
		$.ajax({
			url:"<%=request.getContextPath()%>/member/list_data",
			data:{pg:pg}, // 페이지값 전달
			dataType:"json",
			method:"post"
		})
		.done((res)=>{
			// console.log(res);
			res.data.forEach((item)=>{
				tr = "<tr><td>"+item.num + "</td>";
				tr += "<td>"+item.user_id + "</td>";
				tr += "<td>"+item.user_name + "</td>";
				tr += "<td>"+item.email + "</td>";
				tr += "<td>"+item.phone + "</td></tr>";
				
				$("#mytable > tbody:last").append(tr);
				// > : mytable에 소속된
			})
		})
		.fail((res, status, error)=>{
			console.log(status);
			console.log(error);
		})
	}
</script>
</html>