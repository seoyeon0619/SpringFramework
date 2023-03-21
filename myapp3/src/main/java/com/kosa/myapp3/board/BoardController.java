package com.kosa.myapp3.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.myapp3.comment.CommentDto;
import com.kosa.myapp3.comment.CommentService;
import com.kosa.myapp3.common.CommonConst;
import com.kosa.myapp3.common.FileUploadUtil;

@Controller
public class BoardController {
	@Resource(name="boardService")
	BoardService service;
	
	@RequestMapping(value="/board/list")
	public String getList(BoardDto dto, CommentDto commentDto, Model model)
	{
		model.addAttribute("totalCnt", service.getTotalCnt(dto));
		model.addAttribute("boardList", service.getList(dto));
		return "board/board_list";
	}
	
	@RequestMapping(value="/board/write")
	public String write(BoardDto dto, CommentDto commentDto, Model model)
	{
		BoardDto tempDto = new BoardDto();
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	// view(seq) -> reply(seq)
	@RequestMapping(value="/board/reply")
	public String reply(BoardDto dto, Model model)
	{
		// 부모글 정보 가져오기
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	@RequestMapping(value="/board/modify")
	public String modify(BoardDto dto, Model model)
	{
		// 수정할 글 정보 가져오기
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	

	
	// Ajax로 작성
	@ResponseBody
	@RequestMapping(value="/board/reply_save")
	public Map<String, String> reply_save(BoardDto dto, Model model, MultipartHttpServletRequest multi)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		// 트랜잭션 처리를 해놓으면 Controller에서 따로 예외처리를 해줘야함
		// 서비스에서 예외처리하면 rollback 적용 불가
		try {
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			fileList.add(multi.getFile("file1"));
			fileList.add(multi.getFile("file2"));
			fileList.add(multi.getFile("file3"));
			
			List<String> fileNameList = new ArrayList<String>();
			
			String path = CommonConst.UPLOADPATH+"\\board";
			FileUploadUtil.setFilePath(path);
			
			FileUploadUtil.upload(fileList, fileNameList);
			
			dto.setFilename1(fileNameList.get(0));
			dto.setFilename2(fileNameList.get(1));
			dto.setFilename3(fileNameList.get(2));
			
			service.reply(dto);
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map; // 리스트로
	}
	
	@ResponseBody
	@RequestMapping(value="/board/modify_save")
	public Map<String, String> modify_save(BoardDto dto, Model model, String []del, String []old_name, MultipartHttpServletRequest multi)
	{
		Map<String, String> map= new HashMap<String, String>();
		//파일 첨부를 하지 않더라도 본래의 파일명은 갖고 있어야 한다 
		//디비에서 가져온 기본값을 넣어 놓자 
		dto.setFilename1(old_name[0]);
		dto.setFilename2(old_name[1]);
		dto.setFilename3(old_name[2]);
		
		String path = CommonConst.UPLOADPATH+"\\board";
		FileUploadUtil.setFilePath(path);
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add( multi.getFile("file1"));
		fileList.add( multi.getFile("file2"));
		fileList.add( multi.getFile("file3"));
		
		List<String> fileNameList = new ArrayList<String>();
		
		FileUploadUtil.upload(fileList, fileNameList);
		
		//첨부된 파일 처리하기 
		if(del[0].equals("1"))
			dto.setFilename1(fileNameList.get(0));
		if(del[1].equals("2"))
			dto.setFilename2(fileNameList.get(1));
		if(del[2].equals("3"))
			dto.setFilename3(fileNameList.get(2));
		
		service.modify(dto);
		
		map.put("result", "success");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/board/save")
	public Map<String, String> save(BoardDto dto, MultipartHttpServletRequest multi) {
		// dto에 다른 파라미터값들은 이미 들어와있어서 파일처리만 하면됨
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add(multi.getFile("file1"));
		fileList.add(multi.getFile("file2"));
		fileList.add(multi.getFile("file3"));
		
		List<String> fileNameList = new ArrayList<String>();
		
		String path = CommonConst.UPLOADPATH+"\\board";
		FileUploadUtil.setFilePath(path);
		
		FileUploadUtil.upload(fileList, fileNameList);
		
		dto.setFilename1(fileNameList.get(0));
		dto.setFilename2(fileNameList.get(1));
		dto.setFilename3(fileNameList.get(2));
		
		service.insert(dto);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", "success");
		return resultMap;
		
	}
	
	//board/view?seq=12   보통 이렇게 넘긴다. 
	//board/view?seq=12&board_seq=12   이렇게 안넘긴다. 
	@RequestMapping(value="/board/view")
	public String getView(BoardDto dto, Model model)
	{
		CommentDto commentDto = new CommentDto();
		commentDto.setBoard_seq(dto.getSeq());
		
		//request.setAttribute("boardDto", service.getView(dto));
		model.addAttribute("boardDto", service.getView(dto));
		model.addAttribute("commentList", commentService.getList(commentDto));
		return "board/board_view";
	}
	
	@ResponseBody
	@RequestMapping(value="/board/delete")
	public Map<String, String> delete(BoardDto dto)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		service.delete(dto);
		resultMap.put("result", "success");
		return resultMap;
	}
	
	@Resource(name="commentService")
	CommentService commentService;
	
	//원래글있고 여기에 별도로 코맨트를 달거나 추천을 할때 그부분만 별도의 움직임이 있어야 
	//Ajax 말고 방법이 없다. 
	@ResponseBody 
	@RequestMapping(value="/comment/write")
	public Map<String, Object> comment_write(CommentDto commentDto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//Map<키값, 저장할데이터> 키값은 거의 항상 String 이지만 
		//저장할데이터는 String일수도 다른 객체일수도 있다. 그래서 어떤 객체든 저장가능하도록
		//최상의 클래스인 Object를 지정한다 
		commentService.insert(commentDto);
		map.put("result", "success");
		
		return map;
	}
	
	//http://localhost:9000/myapp3/comment/list?board_seq=37 
	@ResponseBody 
	@RequestMapping(value="/comment/list")
	public Map<String, Object> comment_list(CommentDto commentDto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", commentService.getList(commentDto));
		return map;
	}
	
}
