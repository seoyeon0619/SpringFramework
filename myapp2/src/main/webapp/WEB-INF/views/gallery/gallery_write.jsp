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
        <th>제목</td>
        <td>
        	 <div class="input-group">
			    <input type="text" class="form-control" placeholder="제목을 입력하세요"
			    name="title" id="title">
			 </div>
        </td>
      </tr>
      <tr>
        <th>작성자</td>
        <td>
        	 <div class="input-group">
			    <input type="text" class="form-control" placeholder="이름을 입력하세요"
			    name="writer" id="writer">
			 </div>
        </td>
      </tr>
      <tr>
        <th>내용</td>
        <td>
        	 <div class="input-group">
			    <textarea class="form-control" placeholder="내용 입력하세요"
			    name="contents" id="contents" rows="5"></textarea>
			 </div>
        </td>
      </tr>
      <tr>
        <th>이미지</td>
        <td>
        	 <div class="input-group">
			    <input class="form-control" placeholder="파일을 첨부하세요"
			    type="file" name="file1" id="file1"/>
			 </div>
        </td>
      </tr>
      
      
    </tbody>
  </table>
 </div>


 <div class="container" style="text-align:right">
	<button type="button" class="btn btn-primary" id="btnSave">글쓰기</button>
 </div>
 </form>
 
</body>
<script>
$(()=>{
	$("#btnSave").click(()=>{
		// 파일 전송 시, FormData 객체를 만들어서 보내야함
		// 자바스크립트로 파일 전송 시 FormData 개체를 만듦
		let frmData = new FormData($("#myform")[0]);
		console.log(frmData);
		
		// image1의 내용 가져와서 type 체크
		for(key of frmData.keys())
			console.log(key, frmData.get(key));
		
		$.ajax({
			url : "<%=request.getContextPath()%>/gallery/save",
			data : frmData,
			processData : false, // fileupload시 꼭 필요
			contentType : false, // fileupload시 꼭 필요
			enctype : "multipart/form-data", // fileupload시 꼭 필요
			timeout : 600000, // 전송시간 제한
			dataType:"json", // 데이터 수신 시 json으로 받기
			type:"POST" // fileupload시 꼭 필요, 전송방식 반드시 post로
		})
		.done((res)=>{
			console.log(res);
		})
		.fail((res, status, error)=>{
			console.log(status);
		})
	});
})
</script>
</html>