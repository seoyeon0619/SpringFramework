package com.kosa.mycompany.score;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{

	@Resource(name="scoreDao")
	ScoreDao dao;
	
	@Override
	public List<ScoreDto> getList(ScoreDto dto) {
		return dao.getList(dto);
	}

}
