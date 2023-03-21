package com.kosa.mycompany.score;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("scoreDao")
public class ScoreDaoImpl implements ScoreDao{
	@Autowired
	SqlSessionTemplate sm;

	@Override
	public List<ScoreDto> getList(ScoreDto dto) {
		return sm.selectList("Score_getList", dto);
	}

	
}
