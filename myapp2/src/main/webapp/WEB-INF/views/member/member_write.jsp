<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form name="myform" id="myform">
	<input type="hidden" name="idcheck" id="idcheck" value="N">

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




<div class="container mt-3">
  <h3>Navbar With Dropdown</h3>
  <p>This example adds a dropdown menu in the navbar.</p>
  

   <table class="table">
   	<colgroup>
   		<col width="30%">
   		<col width="*">
   	</colgroup>
    <tbody>
      <tr>
        <th>아이디</td>
        <td>
        	 <div class="input-group">
			    <input type="text" class="form-control" 
			    placeholder="아이디를 입력하세요"
			    name="user_id" id="user_id">
			    <span class="btn btn-dark" onclick="goIdCheck()">아이디중복체크</span>
			 </div>
        </td>
      </tr>
      <tr>
        <th>패스워드</td>
        <td>
        	 <div class="input-group">
			    <input type="password" class="form-control" 
			    placeholder="패스워드를 입력하세요"
			    name="password" id="password">
			 </div>
        </td>
      </tr>
      <tr>
        <th>패스워드확인</td>
        <td>
        	 <div class="input-group">
			    <input type="password" class="form-control" 
			    placeholder="패스워드를 입력하세요"
			    name="password2" id="password2">
			 </div>
        </td>
      </tr>
      <tr>
        <th>이름</td>
        <td>
        	 <div class="input-group">
			    <input type="text" class="form-control" 
			    placeholder="이름을 입력하세요"
			    name="user_name" id="user_name">
			 </div>
        </td>
      </tr>
      <tr>
        <th>이메일</td>
        <td>
        	 <div class="input-group">
			    <input type="email" class="form-control" 
			    placeholder="이름을 입력하세요"
			    name="email" id="email">
			 </div>
        </td>
      </tr>
      <tr>
        <th>전화번호</td>
        <td>
        	 <div class="input-group">
			    <input type="tel" class="form-control" 
			    placeholder="전화번호를 입력하세요"
			    name="phone" id="phone">
			 </div>
        </td>
      </tr>
    </tbody>
  </table>
 </div>


 <div class="container" style="text-align:right">
	<button type="button" class="btn btn-primary" onclick="goSave()">등록</button>
 </div>

</form>
</body>
<script>
function goSave()
{
	if( $("#idcheck").val()=="N")
		{
			alert("아이디 중복체크를 하세요");
			$("user_id").focus();
			return;
		}
	var frm = document.myform;
	frm.action = "<%=request.getContextPath()%>/member/save";
	frm.submit();
	
}

function goIdCheck()
{
	let user_id = $("#user_id").val().trim();
	if( user_id=="")
	{
		alert("아이디를 입력하세요");
		$("#user_id").focus();
		return; //함수 종료 
	}
		
	$.ajax({
		url:"<%=request.getContextPath()%>/member/idcheck",
		data:{"user_id":user_id},
		method:"POST",
		dataType:"JSON"
	})
	.done((response)=>{
		if(response.result=="success")
			{
				$("#idcheck").val("Y");
				$("#user_id").prop("disabled", true);
				alert("사용가능한 아이디입니다.");
			}
		else
			{
				alert("이미 사용중인 아이디입니다")
			}
	})
	.fail((response, status, error)=>{
		console.log(error);
	})
}
</script>
</html>