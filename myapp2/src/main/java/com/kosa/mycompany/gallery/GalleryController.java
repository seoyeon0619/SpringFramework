package com.kosa.mycompany.gallery;

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

import com.kosa.mycompany.common.FileUploadUtil;


@Controller
public class GalleryController {
	
	@Resource(name="galleryService")
	GalleryService galleryService;

	@RequestMapping(value="/gallery/gallery_write")
	public String gallery_write() {
		return "gallery/gallery_write";
	}
	
	@ResponseBody
	@RequestMapping(value="/gallery/save")
	public Map<String, String> gallery_save(GalleryDto dto, MultipartHttpServletRequest multi) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		// 주의사항 : 파일 받을 때 변수명과 태그명이 같으면 문제가 발생 
		// 파일을 받아서 별도의 처리 필요, 파일명을 변수에 할당
		System.out.println(dto.getTitle());
		System.out.println(dto.getWriter());
		System.out.println(dto.getContents());
		System.out.println(dto.getImage1());
		
		MultipartFile file = multi.getFile("file1");
		System.out.println(file.getOriginalFilename());
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		fileList.add(file);
		
		List<String> fileNameList = new ArrayList<String>();
		
		String path="C:\\web_programming\\spring_workspace1\\myapp2\\src\\main\\webapp\\upload";
		FileUploadUtil.setFilePath(path);
		
		FileUploadUtil.upload(fileList, fileNameList);
		dto.setImage1(fileNameList.get(0));
		
		System.out.println(dto.getImage1());
		
		galleryService.insert(dto);
		
		resultMap.put("result", "0");
		resultMap.put("message", "글이 등록되었습니다");
		return resultMap;
	}
	
	@RequestMapping(value="/gallery/gallery_list")
	public String gallery_list(Model model, GalleryDto dto)
	{
		System.out.println(dto.getSearchKey());
		System.out.println(dto.getSearchKeyword());
		
		model.addAttribute("totalCnt", galleryService.getTotalCnt(dto));
		
		return "gallery/gallery_list";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/gallery/list_data")
	public Map<String, Object> gallery_listdata(GalleryDto dto)
	{
		dto.setPgSize(12);
		System.out.println(dto.getSearchKey());
		System.out.println(dto.getSearchKeyword());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCnt", galleryService.getTotalCnt(dto));
		map.put("data", galleryService.getList(dto));
		
		return map;
	}
	

}
