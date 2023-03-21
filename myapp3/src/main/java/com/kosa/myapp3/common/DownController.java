package com.kosa.myapp3.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class DownController {
	@RequestMapping("/down")
	public void download(HttpServletRequest req, 
			             HttpServletResponse res,
			             String path,
			             String filename)
	{
		//path - ��� ������ ���� ������ ���ε��ϸ� ������ ��ƴ� 
		// /upload/board, /upload/gallery   /upload/qna 
		// �̷������� ����� ���ε带 �Ѵ�. 
		// path���� board, gallery, qna  �� /upload �Ʒ�
		// �������� ����. 
		//  filename : �ٿ�ε��� ���ϸ� 
		
		//���� 3.0 �̻����   ���������� ���� ������ ������ �˷��ִ� 
		//�Լ��� ������(�������� �־��µ� Ŭ������ �Լ��̸��� �޶��� 
		//ServletContext ctx = req.getServletContext();
		//String fullPath = ctx.getRealPath("/upload/"+path);
		String fullPath=CommonConst.UPLOADPATH+"/"+path;
		System.out.println(fullPath);
		System.out.println(filename);
		
		//������ �ִ� ������ �о Ŭ���̾�Ʈ�� �����ؾ� �Ѵ� 
		InputStream in = null; //������, ������ �о  
		OutputStream out = null; //Ŭ���̾�Ʈ ���� �����Ѵ� 
		File file=null;
		boolean skip=false; //������ ������ �����ؾ� �����Ѵ�. 
		                    //�� ������ ����  true ���� ������ �����Ѵ� 
		//������ �������� �ִ� 
		//��������(�̹���, ������, exe, class ���ϵ�)��, �׸���, window player 
		//�ؽ�Ʈ����(txt,  �ҽ����ϵ�) - Ư���� ���α׷� ���� ���� �� ������  
		// ��ǻ�� ������ �޸𸮸� ������ �ؼ� ���� , ���������� ��ǻ�� ���� �޸� �״�� 
		// ���Ͽ� �����Ѵ�. �������� �ؽ�Ʈ�� ����ϸ� ���� �Ұ� 
		// �������� ó���� �ؾ� ���� ������ �ջ��Ű�� �ʴ´�. �������·� ������ �Ѵ� 
		try
		{
			file = new File(fullPath, filename); //������ ã�Ƽ� 
			in = new FileInputStream(file); //���� �غ����̴� 
		}
		catch(Exception e) //������ �������� �ʰų� �׹��� ������ ���ۺҰ���
		{
			e.printStackTrace();
			skip = true;
		}
		
		if(skip== true)
			return;
		
		//�ѱ�ó�� 
		try {
			String client = req.getHeader("User-Agent");
			if(client.indexOf("MSIE")!=-1)
				filename = new String(filename.getBytes("KSC5601"),"ISO8859_1");
			else 
				filename = new String(filename.getBytes("KSC5601"),"iso-8859-1");
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
				
				
		//���� �غ� 
		res.reset(); //Ȥ�� �������� Ŭ���̾�Ʈ�� ���� ������ ����������� �����
		res.setContentType("application/octet-stream"); 

		//2�����·� ������ �����ڴٴ� ����� Ŭ���̾�Ʈ�� �˸���.
		//attachment; filename="test.txt"
		res.setHeader("Content-Disposition", 
				"attachment; filename=\""+  filename + "\"");
		res.setHeader("Content-type", "application/octet-stream");
		res.setHeader("Content-length", file.length()+"");
		
		try {
			out = res.getOutputStream();
			//������ byte������ �о�� �Ѵ� 
			//������ ���� Ŭ��쿡 �ѹ��� �޸� Ȯ���� �ȵɰ�� �߶� �о� ������ �Ѵ�. 
			//����� ���� �ϳ��� ������ byte �迭�� �������
			byte b[] = new byte[(int)file.length()];
			int leng=0;
			//in.read(b) - �־��� �迭ũ�⸸ŭ �����͸� �о�´�. 
			//��ȯ���� ������ ���� ������ ũ���̴�. 
			while((leng=in.read(b))>0)  //>0 ���� �����Ͱ� �ִٸ� 
			{
				out.write(b, 0, leng); //Ŭ���̾�Ʈ�� �����϶� 
			}
			out.close();// ������ �ݴ´�
			in.close(); //������ �ݴ´� 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //������ ������ ���� ��� ����,
	}
}




