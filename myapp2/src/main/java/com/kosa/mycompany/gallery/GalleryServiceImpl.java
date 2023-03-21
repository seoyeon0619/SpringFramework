package com.kosa.mycompany.gallery;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("galleryService")
public class GalleryServiceImpl implements GalleryService{
	
	@Resource(name="galleryDao")
	GalleryDao galleryDao;

	@Override
	public List<GalleryDto> getList(GalleryDto dto) {
		return galleryDao.getList(dto);
	}

	@Override
	public int getTotalCnt(GalleryDto dto) {
		return galleryDao.getTotalCnt(dto);
	}

	@Override
	public void insert(GalleryDto dto) {
		galleryDao.insert(dto);
	}

	@Override
	public GalleryDto getView(GalleryDto dto) {
		return galleryDao.getView(dto);
	}
	
}
