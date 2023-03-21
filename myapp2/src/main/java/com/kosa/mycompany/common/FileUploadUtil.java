package com.kosa.mycompany.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static String filePath="";

	
	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		FileUploadUtil.filePath = filePath;
	}

	public static String getFileName(String orifilename) {
		String newFilename="";
		int pos = orifilename.lastIndexOf(".");
		String ext=""; 
		String filename="";
		if(pos!=-1)
		{
			filename = orifilename.substring(0,  pos);
			ext = orifilename.substring(pos+1); 
		}
		else 
		{
			filename = orifilename;
			ext="";
		}
		
		// 컨트롤러에서 MultipartFile 객체리스트 전달하면 파일 저장 후 파일의 이름 목록 전달
		// 파일명 충돌 방지
			// a.jpg  a(1).jpg  a(2).jpg
			// 202302021530133.jpg (마지막 세자리 랜덤값)
		System.out.println("파일이름 : " + filename);
		System.out.println("확장자 : " + ext);
		newFilename = filename + "." + ext;
		File newFile = new File(filePath + "/" + newFilename);
		int i=1;
		while(newFile.exists()) // 파일 존재
		{
			// 새로운 파일명 만들기 : a(1).jpg, a(2).jpg....
			newFilename = filename + "("+i+")"+"."+ext;
			i++;
			newFile = new File(filePath + "/" + newFilename);
		}
		System.out.println(newFilename);
		return newFilename;
	}
	
	public static void upload(List<MultipartFile> fileList, 
			List<String>fileNameList)
	{
		File file  = new File(filePath);
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		if( fileList!=null && fileList.size()>0)
		{
			for(MultipartFile mfile : fileList)
			{
				String orifilename=mfile.getOriginalFilename();
				String newFileName = getFileName( orifilename );
				try {
					mfile.transferTo(new File(filePath+"/"+newFileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				fileNameList.add(newFileName);
			}
		}
	}
	
}





