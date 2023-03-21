package com.kosa.mycompany.gallery;

import java.util.List;

public interface GalleryService {
	List<GalleryDto> getList(GalleryDto dto);
	GalleryDto getView(GalleryDto dto);
	int getTotalCnt(GalleryDto dto);
	void insert(GalleryDto dto);
}
