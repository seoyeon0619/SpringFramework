package com.kosa.myapp3.member;

public interface MemberService {
	public void Member_insert(MemberDto dto);
	public boolean isUse(MemberDto dto);
	public MemberDto member_logon(MemberDto dto);
}
