package com.kosa.mycompany.guestbook;

import java.util.List;

public interface GuestbookService {
	List<GuestbookDto> getList(GuestbookDto dto);
	int getTotalCnt(GuestbookDto dto);
	
	GuestbookDto getView(GuestbookDto dto);
	
	void insert(GuestbookDto dto);
	void update(GuestbookDto dto);
	void delete(GuestbookDto dto);
}
