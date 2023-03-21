<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kosa.mycompany.gallery.*" %>
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
String searchKey= request.getParameter("searchKey");
String searchKeyword= request.getParameter("searchKeyword");
if(searchKey==null) searchKey="";
if(searchKeyword==null) searchKeyword="";
%>
<form name="myform" id="myform">
	<input type="hidden" name="pg" id="pg" value="0"/>
<!-- 메뉴 -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="/mycompany/upload/apple_logo_PNG19695.png" alt="Avatar Logo" 
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

<div class="container mt-3">
  <h3>Gallery</h3>
  
   <div class="input-group mt-3 mb-3">
	  <button type="button" id="select" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown">
	    SELECT</button>
	  <ul class="dropdown-menu">
	    <li><a class="dropdown-item" href="#none" onclick="goSearchKey('0')">SELECT</a></li>
	    <li><a class="dropdown-item" href="#none" onclick="goSearchKey('1')">TITLE</a></li>
	    <li><a class="dropdown-item" href="#none" onclick="goSearchKey('2')">CONTENTS</a></li>
	    <li><a class="dropdown-item" href="#none" onclick="goSearchKey('3')">TITLE+CONTENTS</a></li>
	  </ul>
	  <!-- ul태그는 값저장을 못해서 서버에 봬려면 input태그에 담아서 보내야 함 -->
	  <input type="hidden" name="searchKey" id="searchKey" value="<%=searchKey%>"/>
	  <input type="text" class="form-control" name="searchKeyword" id="searchKeyword"  value="<%=searchKeyword%>" placeholder="입력하세용">
	  <div class="input-group-append">
	  	<button class="btn btn-dark" type="button" onclick="goSearch()">🔍</button>
	  </div>
	</div>

<div id="contents">
</div> <!-- end contents  -->

 <div class="container" style="text-align:right">
  <ul class="pagination justify-content-center">
  	<li class="page-item"><a class="page-link" href="#">first</a></li>
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item"><a class="page-link" href="#">6</a></li>
    <li class="page-item"><a class="page-link" href="#">7</a></li>
    <li class="page-item"><a class="page-link" href="#">8</a></li>
    <li class="page-item"><a class="page-link" href="#">9</a></li>
    <li class="page-item"><a class="page-link" href="#">10</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
    <li class="page-item"><a class="page-link" href="#">last</a></li>
  </ul>
</div>

 <div class="container" style="text-align:right">
	<button type="button" class="btn btn-dark" onclick="goWrite()">글쓰기</button>
 </div>
</form>
</body>
</html>
<script>
$(()=>{
	load_data();
})

function load_data()
{
	$.ajax({
		url:"<%=request.getContextPath()%>/gallery/list_data",
		data:{
				pg:0, 
				searchKey:$("#searchKey").val(),
				searchKeyword:$("#searchKeyword").val()
				},
		dataType:"json"
	})
	.done((res)=>{
		console.log(res);
		data = res.data;
		let temp="";
		for (i=0; i<data.length; i++)
		{
		if(i%4==0)
			temp += '<div class="row">' ;
			temp += '<div class="col-sm-3">';
			temp += ' 			<div class="thumbnail">';
			temp += '    			<a href="<%=request.getContextPath()%>/upload/' +data[i].image1+ '">';
			temp += '     			<img src="<%=request.getContextPath()%>/upload/' +data[i].image1+ '" alt="image" style="width:100%">';
			temp += '      		<div class="caption">';
			temp += '       	<p>' + data[i].title + '</p>';
			temp += '     	 	</div>';
			temp += '   		</a>';
			temp += ' 		</div>';
			temp += '		</div>';
		if(i%4==3 || i==data.length -1)
			temp += '	</div>';
		}
		
		console.log(temp);
		$("#contents").html( temp );
	})
	.fail((res, status, error)=>{
		console.log(status);
		console.log(error);
	})
}

function goSearchKey(key)
{
	var select = ["SELECT", "TITLE", "CONTENTS", "TITLE + CONTENTS"];
	$("#select").html(select[key]);
	$("#searchKey").val(key);	
	
}

function goSearch()
{
	$("#myform").prop("action", "<%=request.getContextPath()%>/gallery/gallery_list");
	$("#myform").submit();
}

</script>