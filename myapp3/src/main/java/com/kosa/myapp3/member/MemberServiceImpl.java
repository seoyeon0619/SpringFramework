package com.kosa.myapp3.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.myapp3.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired 
	MemberMapper memberDao;

	@Override
	public void Member_insert(MemberDto dto) {
		memberDao.Member_insert(dto);
	}

	@Override
	public boolean isUse(MemberDto dto) {
		// ��밡���ϸ� : �ߺ��� �ƴ϶�� true
		// ���Ұ����ϸ� : false 
		int result = memberDao.Member_idcheck(dto);
		if(result==0)
			return true;
		
		return false;
	}

	@Override
	public MemberDto member_logon(MemberDto dto) {
		return memberDao.member_logon(dto);
	}
	
}
