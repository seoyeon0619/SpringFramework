package com.kosa.mycompany.home;

import java.util.List;

public interface HomeService {
	List<HomeDto> getList(HomeDto dto);
	void insert(HomeDto dto);
}
