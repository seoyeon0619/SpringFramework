package com.kosa.myapp3.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//���ͼ��� 
//HandlerInterceptorAdapter 
public class LoginInterceptor extends HandlerInterceptorAdapter{

	// /board/write ->  LoginInterceptor�� preHandle�� �鷶�� ���� 
	//�α׿� ���ο� ���� �α׿� �������� ������ �ƴϸ� �׳� ��Ʈ�ѷ��� ������ �����ؾ� �Ѵ� 
	
	//preHandler : ��Ʈ�ѷ��� ȣ��Ǳ� �� ����
	//postHandler : ��Ʈ�ѷ��� �Ϸ�� ���� ����
	//afterCompletion : ��Ʈ�ѷ� ���� �� view�� �۾����� �Ϸ�� �� ȣ��
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//���� ��ü�� ������ �α׿� �Ͽ����� ���θ� �Ǵ��Ѵ�. 
		HttpSession session = request.getSession();
		System.out.println("��� ���� ���İ��� ~~~~~~~~");
		//�ݵ�� �α׿��ؾ��ϴ� ��찡 �ƴ� url �� ����Ѵ� 
		List<String> excludeCheckList=new ArrayList<String>();
		excludeCheckList.add("/member/logon");
		excludeCheckList.add("/member/write");
		excludeCheckList.add("/member/logon_proc");
		excludeCheckList.add("/board/list");
		excludeCheckList.add("/board/view");
		excludeCheckList.add("/");
		
		String email = (String)session.getAttribute("email");
		String currentUrl = request.getRequestURI(); //
		System.out.println(currentUrl);
		if(email == null || email.equals(""))
		{
			//  /myapp3/member/logon
			currentUrl = currentUrl.substring(request.getContextPath().length());
			System.out.println(currentUrl);
			
			//�α׿� �������� �̵��Ѵ� - �α׿� ���� ����Ʈ�� ������ �α׿��������� ����
			//�̵��Ѵ� 
			if( excludeCheckList.indexOf(currentUrl)==-1 )
			{
				String url=request.getContextPath()+"/member/logon";
				response.sendRedirect(url);
				//�α׿� �������� �̵��Ѵ� 
				return false;//�α׿� �������� 
			}
		}
		
		
		
		return super.preHandle(request, response, handler);
		//�θ�Ŭ������ �Լ��� ȣ���ؾ� �Ѵ� 
	}

	
}
