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
		// �θ�� ���� ��������
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	
	@RequestMapping(value="/board/modify")
	public String modify(BoardDto dto, Model model)
	{
		// ������ �� ���� ��������
		BoardDto tempDto = service.getView(dto);
		model.addAttribute("boardDto", tempDto);
		return "board/board_write";
	}
	

	
	// Ajax�� �ۼ�
	@ResponseBody
	@RequestMapping(value="/board/reply_save")
	public Map<String, String> reply_save(BoardDto dto, Model model, MultipartHttpServletRequest multi)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		// Ʈ����� ó���� �س����� Controller���� ���� ����ó���� �������
		// ���񽺿��� ����ó���ϸ� rollback ���� �Ұ�
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
		return map; // ����Ʈ��
	}
	
	@ResponseBody
	@RequestMapping(value="/board/modify_save")
	public Map<String, String> modify_save(BoardDto dto, Model model, String []del, String []old_name, MultipartHttpServletRequest multi)
	{
		Map<String, String> map= new HashMap<String, String>();
		//���� ÷�θ� ���� �ʴ��� ������ ���ϸ��� ���� �־�� �Ѵ� 
		//��񿡼� ������ �⺻���� �־� ���� 
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
		
		//÷�ε� ���� ó���ϱ� 
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
		// dto�� �ٸ� �Ķ���Ͱ����� �̹� �����־ ����ó���� �ϸ��
		
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
	
	//board/view?seq=12   ���� �̷��� �ѱ��. 
	//board/view?seq=12&board_seq=12   �̷��� �ȳѱ��. 
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
	
	//�������ְ� ���⿡ ������ �ڸ�Ʈ�� �ްų� ��õ�� �Ҷ� �׺κи� ������ �������� �־�� 
	//Ajax ���� ����� ����. 
	@ResponseBody 
	@RequestMapping(value="/comment/write")
	public Map<String, Object> comment_write(CommentDto commentDto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//Map<Ű��, �����ҵ�����> Ű���� ���� �׻� String ������ 
		//�����ҵ����ʹ� String�ϼ��� �ٸ� ��ü�ϼ��� �ִ�. �׷��� � ��ü�� ���尡���ϵ���
		//�ֻ��� Ŭ������ Object�� �����Ѵ� 
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
