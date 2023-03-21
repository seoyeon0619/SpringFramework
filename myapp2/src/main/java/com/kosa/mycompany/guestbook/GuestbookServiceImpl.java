package com.kosa.mycompany.guestbook;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("guestbookService")
public class GuestbookServiceImpl implements GuestbookService{

	@Resource(name="guestbookDao")
	GuestbookDao dao;
	
	@Override
	public List<GuestbookDto> getList(GuestbookDto dto) {
		return dao.getList(dto);
	}

	@Override
	public int getTotalCnt(GuestbookDto dto) {
		return dao.getTotalCnt(dto);
	}

	@Override
	public GuestbookDto getView(GuestbookDto dto) {
		return dao.getView(dto);
	}

	@Override
	public void insert(GuestbookDto dto) {
		dao.insert(dto);
	}

	@Override
	public void update(GuestbookDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(GuestbookDto dto) {
		dao.delete(dto);
	}
	

}
