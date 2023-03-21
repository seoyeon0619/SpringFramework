package com.kosa.myapp3.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MemberController {
	@Resource(name="memberService")
	MemberService service;
	
	@ResponseBody
	@RequestMapping("/member/save")
	Map<String, Object>member_save(MemberDto dto)
	{
		service.Member_insert(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("msg", "Welcome!");
		
		return map;
	}
	
	@RequestMapping("/member/write")
	String member_write(MemberDto dto)
	{
		
		return "member/member";
	}
	
	@ResponseBody
	@RequestMapping("/member/isuse")
	Map<String, Object> member_isuse(MemberDto dto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(service.isUse(dto))
		{
			map.put("result", "success");
			map.put("msg", "사용가능한 아이디입니다");
		}
		else
		{
			map.put("result", "fail");
			map.put("msg", "사용중인 아이디입니다");
		}
		return map;
	}
	
	@RequestMapping("/member/logon")
	String member_logon()
	{
		return "member/logon";
	}
	
	//세션객체는 사용자가 브라우저를 통해서 서버로 접근하면 사용자마다 이미 만들어져 있다 
	@ResponseBody
	@RequestMapping("/member/logon_proc")
	Map<String, Object> member_logon_proc(MemberDto dto, 
			HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
	
		//시스템에서 서버 접근 요청이 있을때(서버접근자마다) 하나씩 개체가 만들어 진다 
		HttpSession session = request.getSession(); //request,session, map
		MemberDto resultDto = service.member_logon(dto);
		
		if(resultDto == null)
		{
			map.put("result", "fail");
			map.put("msg", "해당 아이디가 존재하지 않습니다");
			return map; //여기서 함수가 종료한다 
		}
		
		if( !resultDto.getPassword().equals(dto.getPassword()))
		{
			map.put("result", "fail");
			map.put("msg", "패스워드가 일치하지 않습니다.");
			return map;
		}
	
		//성공시 세션에 로그온 정보를 저장한다 
		//세션에 너무 많은 정보를 저장하면 시스템이 과부하가 걸린다 
		session.setAttribute("user_id", resultDto.getUser_id());
		session.setAttribute("user_name", resultDto.getUser_name());
		session.setAttribute("email", resultDto.getEmail());
		
		map.put("result", "success");
		map.put("msg", "로그온되었습니다");
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/member/logout")
	Map<String, Object> member_logout(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		session.invalidate(); //세션 내용 삭제 
		map.put("result", "success");
		map.put("msg", "로그아웃!");
		return map;
	}
	
	
	
}




