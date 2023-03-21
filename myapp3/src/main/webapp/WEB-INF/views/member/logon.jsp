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
<%@include file="../include/nav.jsp" %>

<div class="container mt-3">
  <h3>Login</h3>
  <p></p>

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
			    <input type="text" class="form-control" placeholder="아이디를 입력하세요" name="user_id" id="user_id">
			 </div>
        </td>
      </tr>
      <tr>
        <th>패스워드</td>
        <td>
        	 <div class="input-group">
			    <input type="password" class="form-control" placeholder="패스워드를 입력하세요" name="password" id="password">
			 </div>
        </td>
      </tr>
      <tr>
    </tbody>
  </table>
 </div>


 <div class="container" style="text-align:right">
	<!-- <button type="button" class="btn btn-dark"><a href="<%=request.getContextPath()%>/member/findid">아이디 찾기</a></button>
	<button type="button" class="btn btn-dark"><a href="<%=request.getContextPath()%>/member/findpassword">비밀번호 찾기</a></button> -->
	<button type="button" class="btn btn-dark" id="btnLogon">Login</button>
 </div>

</form>
</body>
<script>
$( ()=>{
	$("#btnLogon").click(()=>{
		
		
		$.ajax({
			url:"<%=request.getContextPath()%>/member/logon_proc",
			data:{user_id:$("#user_id").val(),
				password:$("#password").val()},
			type:"post",
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
			console.log( error );
		})
	});
	
	
})
</script>
</html>