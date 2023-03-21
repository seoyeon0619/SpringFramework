<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kosa.myapp3.board.*" %>
<%@ page import="java.util.*, com.kosa.myapp3.comment.*" %>
<%@ page import="java.util.*, com.kosa.myapp3.common.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>  
</head>
<body>
<%
String pg=StringUtil.nullToValue(request.getParameter("pg"),"0");
String seq=StringUtil.nullToValue(request.getParameter("seq"),"");
BoardDto dto = (BoardDto)request.getAttribute("boardDto");
%>

<form name="myform" id="myform">
	<input type="hidden" name="pg"             id="pg"         value="<%=pg%>">
	<input type="hidden" name="seq"            id="seq"        value="<%=seq%>">
	<input type="hidden" name="board_seq"      id="board_seq"  value="<%=seq%>">
	<input type="hidden" name="userid"         id="userid"     value="tjdus">
	<input type="hidden" name="mode"           id="mode" >
	<input type="hidden" name="group_id"       id="group_id"   value="<%=dto.getGroup_id()%>">
	<input type="hidden" name="depth"          id="depth"      value="<%=dto.getDepth()%>">
	<input type="hidden" name="g_level"        id="g_level"    value="<%=dto.getG_level()%>">
	
<%@ include file = "../include/nav.jsp" %>

<div class="container">
	<h1> </h1>
  <h3>View</h3>
  <p></p>
  
  <table class="table">
  <colgroup>
  	<col width="15%">
  	<col width="18%">
  	<col width="15%">
  	<col width="18%">
  	<col width="15%">
  	<col width="18%">
  </colgroup>
    <tbody>
     
      
      <tr>
      <th>작성자</td>
      <td><%=dto.getUserid()%></td>
      <th>작성일</td>
      <td><%=dto.getRegdate()%></td>
      <th>조회수</td>
      <td><%=dto.getHit()%></td>
      </tr>
      
      <tr>
      <th>내용</td>
      <td colspan="5" style="word-break:break-all"><%=dto.getContents().replace("\n", "<br/>")%></td>
      </tr>
      
      <tr>
	      <th>첨부파일</td>
	      <td><a href="<%=request.getContextPath()%>/down?path=board&filename=<%=dto.getFilename1()%>" class="btn"><%=dto.getFilename1()%></a></td>
	      <td colspan="6">
	      <img 
	      	src="<%=request.getContextPath()%>/upload/board/<%=dto.getFilename1()%>"
	      	alt = "" style=" width:50% ">
      </tr>
      <tr>
          <th>첨부파일</td>
	      <td><a href="<%=request.getContextPath()%>/down?path=board&filename=<%=dto.getFilename2()%>" class="btn"><%=dto.getFilename2()%></a></td>
	      <td colspan="6">
	      <img 
      	src="<%=request.getContextPath()%>/upload/board/<%=dto.getFilename2()%>"
      	alt = "" style=" width:50% ">
      </tr>
      <tr>
      	  <th>첨부파일</td>	
	      <td><a href="<%=request.getContextPath()%>/down?path=board&filename=<%=dto.getFilename3()%>" class="btn"><%=dto.getFilename3()%></a></td>
	      <td colspan="6">
	      <img 
      	src="<%=request.getContextPath()%>/upload/board/<%=dto.getFilename3()%>"
      	alt = "" style=" width:50% ">
      </tr>
    </tbody>
  </table>
</div>

<div class="container" style="text-align:right">
	<button type="button" class="btn btn-dark" id="btnReply">답글</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-dark" id="btnModify">수정</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-dark" id="btnDelete">삭제</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-dark" id="btnList">목록</button>&nbsp;&nbsp;
</div>

<!-- 댓글 붙이기 -->
<div class="container mt-3">
<!-- 댓글 입력창 -->
 <table class="table">
    <tbody>
      <tr>
        <td style="width:80%">
        	 <div class="input-group">
			    <textarea type="text" class="form-control" rows="4" id="comment"></textarea>
			 </div>
        </td>
        <td style="width:20%">
        	<button class="btn btn-dark" id="btnComment">작성</button>
        </td>
      </tr>
 	</tbody>
 </table>
      
<!-- 댓글 리스트 -->
	<table class="table" id="commentTable">
		<tbody>
			<tr>
		        <td style="width:100%">
		        	 작성자 : 
		        	 내용 :      	
		        </td>
      		</tr>	
		</tbody>
	</table>
</div>

</form>
</body>
</html>

<script>
$(()=>{
	$("#btnReply").click(()=>{
		$("#mode").val("reply");
		$("#myform").prop("action", "<%=request.getContextPath()%>/board/reply");
		$("#myform").submit();
	});

	$("#btnModify").click(()=>{
		$("#mode").val("modify");
		$("#myform").prop("action", "<%=request.getContextPath()%>/board/modify");
		$("#myform").submit();
	});

	$("#btnDelete").click(()=>{
		
		let param = $("#myform").serialize();
		// 직렬화 : multipart가 아닌 경우
		// FormData : multipart인 경우 
		
		// seq=4&group_id=3......
		$.ajax({
			url:"<%=request.getContextPath()%>/board/delete",
			data:param,
			dataType:"json"
		})
		.done((res)=>{
			if(res.result=="success")
			{
				alert("삭제!")		
				location.href="<%=request.getContextPath()%>/board/list";
			}
			else
			{
				alert("실패!")
			}	
		})
		.fail((res, status, error)=>{
			console.log(status);
			console.log(error);
		})
	});

	$("#btnList").click(()=>{
		$("#mode").val("list");
		$("#myform").prop("action", "<%=request.getContextPath()%>/board/list");
		$("#myform").submit();
	});
	
	$("#btnComment").click(()=>{
		
	    //자바스크립트를 라이브러리화 한게  JQUERY serialize 는 자바스크립트가 아니고 Jquery가 만들었음
	    //board_seq=12&comments=문장&userid=test
		let param = $("#myform").serialize();
		console.log( param ); //디버깅용
		$.ajax({
			url:"<%=request.getContextPath()%>/comment/write",
			data:param,   
			dataType:"json"
		})
		.done((res)=>{
			if(res.result=="success")
			{
				alert("글이 등록되었습니다.");
				//commentlist  만 다시 불러오기 
				loadData();
			}
			else
			{
				alert("실패!");
			}
		})
		.fail((res, status, error)=>{  
			console.log( status );
			console.log( error );
		});
		
	});
	
	loadData();
})
</script>