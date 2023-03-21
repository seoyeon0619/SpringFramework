package com.kosa.mycompany.home;

import java.util.List;

public interface HomeDao {
	List<HomeDto> getList(HomeDto dto);
	void insert(HomeDto dto);
}
