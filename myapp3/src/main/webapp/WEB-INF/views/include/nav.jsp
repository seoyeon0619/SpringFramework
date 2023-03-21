<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
   	<a class="navbar-brand" href="<%=request.getContextPath()%>/board/list">
      <img src="<%=request.getContextPath()%>/images/apple_logo.png" alt="Logo" style="width:40px;" class="rounded-pill">
    </a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      </a><span class="navbar-toggler-icon"></span></a>
    </button>
    
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/board/list">Board</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>  
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">abc</a></li>
            <li><a class="dropdown-item" href="#">Another link</a></li>
            <li><a class="dropdown-item" href="#">A third link</a></li>
          </ul>
        </li>
      </ul>
    </div>
    
    <%if(session.getAttribute("user_id")==null || session.getAttribute("user_id").equals("")){ %>
     <ul class="navbar-nav justify-content-end">
      <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/logon">Login</a></li>
      <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/write">Sign Up</a></li>
     </ul>
    <%}else { %>
     <ul class="navbar-nav justify-content-end">
     	<li class="nav-item"><a class="nav-link" href="#none"><%=session.getAttribute("user_name")%></a></li>
     	<li class="nav-item"><a class="nav-link" href="#none" id="btnLogout">Logout</a></li>
     	
     </ul>
     <%} %>
  </div>
</nav>

<script>
$(()=>{
	$("#btnLogout").click(()=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/member/logout",
			dataType:"json"
	})
	.done((res)=>{
		alert(res.msg);
		if(res.result=="success")
		{
			location.href="<%=request.getContextPath()%>/";
		}
	})
	.fail((res, status, error)=>{
			console.log( status );
			console.log( error );
		})
	})
})
</script>