package com.kosa.mycompany.fileupload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.mycompany.common.FileUploadUtil;

@Controller 
public class FileController {
	@RequestMapping(value="/fileupload")
	public String fileupload() 
	{
		return "/fileupload/fileupload";
	}
	
	@RequestMapping(value="/fileupload/save") 
	public String fileuploadsave(MultipartHttpServletRequest multi)
	// MultipartHttpServletRequest 객체의 getFile함수 호출
	// 이 함수가 업로드된 파일의 정보를 가지고 있음
	// input type="file" 태그의 name 속성값 가져옴
	{
		MultipartFile file = multi.getFile("file1");
		String path="C:\\web_programming\\spring_workspace1\\myapp2\\src\\main\\webapp\\upload";
		/*File dir = new File(path);
		if( !dir.exists()) // 디렉토리가 존재하지 않으면
			dir.mkdirs();  // 디렉토리 생성
		
		try
		// Spring3 : 임시서버에 파일은 이미 올라와 있음
		// 스프링 부트는 다린 방식
		// "새로운파일명" : 기존의 파일명 가져와서 만들지, 완전히 새로 만들지
		{
			file.transferTo(new File(path + "//a.jpg"));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/
		
		List<MultipartFile> multiList = new ArrayList<MultipartFile>();
		multiList.add(multi.getFile("file1"));
		
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add("filename1");
		
		FileUploadUtil.setFilePath(path);
		FileUploadUtil.upload(multiList, fileNameList);
		
		
		return "redirect:/";
	}
}



