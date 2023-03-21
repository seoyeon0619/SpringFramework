package com.kosa.myapp3.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 
//HandlerInterceptorAdapter 
public class LoginInterceptor extends HandlerInterceptorAdapter{

	// /board/write ->  LoginInterceptor의 preHandle를 들렀다 간다 
	//로그온 여부에 따라 로그온 페이지로 보낼지 아니면 그냥 컨트롤러로 보낼지 결정해야 한다 
	
	//preHandler : 컨트롤러가 호출되기 전 수행
	//postHandler : 컨트롤러가 완료된 이후 수행
	//afterCompletion : 컨트롤러 수행 후 view단 작업까지 완료된 후 호출
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체를 가져와 로그온 하였는지 여부를 판단한다. 
		HttpSession session = request.getSession();
		System.out.println("모두 여길 거쳐간다 ~~~~~~~~");
		//반드시 로그온해야하는 경우가 아닌 url 을 등록한다 
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
			
			//로그온 페이지로 이동한다 - 로그온 배제 리스트에 없으면 로그온페이지로 먼저
			//이동한다 
			if( excludeCheckList.indexOf(currentUrl)==-1 )
			{
				String url=request.getContextPath()+"/member/logon";
				response.sendRedirect(url);
				//로그온 페이지로 이동한다 
				return false;//로그온 안했을때 
			}
		}
		
		
		
		return super.preHandle(request, response, handler);
		//부모클래스의 함수를 호출해야 한다 
	}

	
}
