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
			map.put("msg", "��밡���� ���̵��Դϴ�");
		}
		else
		{
			map.put("result", "fail");
			map.put("msg", "������� ���̵��Դϴ�");
		}
		return map;
	}
	
	@RequestMapping("/member/logon")
	String member_logon()
	{
		return "member/logon";
	}
	
	//���ǰ�ü�� ����ڰ� �������� ���ؼ� ������ �����ϸ� ����ڸ��� �̹� ������� �ִ� 
	@ResponseBody
	@RequestMapping("/member/logon_proc")
	Map<String, Object> member_logon_proc(MemberDto dto, 
			HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
	
		//�ý��ۿ��� ���� ���� ��û�� ������(���������ڸ���) �ϳ��� ��ü�� ����� ���� 
		HttpSession session = request.getSession(); //request,session, map
		MemberDto resultDto = service.member_logon(dto);
		
		if(resultDto == null)
		{
			map.put("result", "fail");
			map.put("msg", "�ش� ���̵� �������� �ʽ��ϴ�");
			return map; //���⼭ �Լ��� �����Ѵ� 
		}
		
		if( !resultDto.getPassword().equals(dto.getPassword()))
		{
			map.put("result", "fail");
			map.put("msg", "�н����尡 ��ġ���� �ʽ��ϴ�.");
			return map;
		}
	
		//������ ���ǿ� �α׿� ������ �����Ѵ� 
		//���ǿ� �ʹ� ���� ������ �����ϸ� �ý����� �����ϰ� �ɸ��� 
		session.setAttribute("user_id", resultDto.getUser_id());
		session.setAttribute("user_name", resultDto.getUser_name());
		session.setAttribute("email", resultDto.getEmail());
		
		map.put("result", "success");
		map.put("msg", "�α׿µǾ����ϴ�");
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/member/logout")
	Map<String, Object> member_logout(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		session.invalidate(); //���� ���� ���� 
		map.put("result", "success");
		map.put("msg", "�α׾ƿ�!");
		return map;
	}
	
	
	
}




