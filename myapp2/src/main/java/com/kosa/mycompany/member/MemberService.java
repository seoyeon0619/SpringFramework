package com.kosa.mycompany.member;

import java.util.List;

public interface MemberService {
	void insert(MemberDto dto);
	boolean isDuplicate(MemberDto dto);
	MemberDto logon(MemberDto dto);
	MemberDto findid(MemberDto dto);
	MemberDto findpassword(MemberDto dto);
	List<MemberDto> getList(MemberDto dto);
	int getTotalCnt(MemberDto dto);
}
