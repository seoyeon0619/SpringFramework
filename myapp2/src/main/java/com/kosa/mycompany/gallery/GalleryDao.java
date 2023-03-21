package com.kosa.mycompany.gallery;

import java.util.List;

import com.kosa.mycompany.guestbook.GuestbookDto;

public interface GalleryDao {
	List<GalleryDto> getList(GalleryDto dto);
	GalleryDto getView(GalleryDto dto);
	int getTotalCnt(GalleryDto dto);
	void insert(GalleryDto dto);
}
