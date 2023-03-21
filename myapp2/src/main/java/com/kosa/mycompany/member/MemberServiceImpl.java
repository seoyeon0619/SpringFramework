package com.kosa.mycompany.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao memberDao;

	@Override
	public void insert(MemberDto dto) {
		memberDao.insert(dto);
	}

	@Override
	public boolean isDuplicate(MemberDto dto) {
		return memberDao.isDuplicate(dto);
	}

	@Override
	public MemberDto logon(MemberDto dto) {
		return memberDao.logon(dto);
	}

	@Override
	public MemberDto findid(MemberDto dto) {
		return memberDao.findid(dto);
	}

	@Override
	public MemberDto findpassword(MemberDto dto) {
		return memberDao.findpassword(dto);
	}

	@Override
	public List<MemberDto> getList(MemberDto dto) {
		return memberDao.getList(dto);
	}

	@Override
	public int getTotalCnt(MemberDto dto) {
		return memberDao.getTotalCnt(dto);
	}

	
}
