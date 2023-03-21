package com.kosa.mycompany.home;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("HomeService")
public class HomeServiceImpl implements HomeService{
	
	@Resource(name="HomeDao")
	HomeDao HomeDao;
	
	@Override
	public List<HomeDto> getList(HomeDto dto) {
		return HomeDao.getList(dto);
	}

	@Override
	public void insert(HomeDto dto) {
		HomeDao.insert(dto);
	}

}
