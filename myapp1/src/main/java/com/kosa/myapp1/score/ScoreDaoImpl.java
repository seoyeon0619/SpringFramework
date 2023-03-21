package com.kosa.myapp1.score;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("scoreDao")
public class ScoreDaoImpl implements ScoreDao{
	List<ScoreDto> list = new ArrayList<ScoreDto>();
	
	public ScoreDaoImpl() {
		super();
		list.add(new ScoreDto("홍길동", "100", "90", "80"));
		list.add(new ScoreDto("김길동", "90", "20", "100"));
		list.add(new ScoreDto("이길동", "80", "40", "70"));
		list.add(new ScoreDto("박길동", "70", "80", "88"));
		list.add(new ScoreDto("최길동", "50", "60", "98"));
	}

	@Override
	public List<ScoreDto> getList(ScoreDto dto) {
		return list;
	}

	@Override
	public ScoreDto getView(String seq) {
		return null;
	}

	@Override
	public void insert(ScoreDto dto) {
		
	}

	@Override
	public void update(ScoreDto dto) {
		
	}

	@Override
	public void delete(ScoreDto dto) {
		
	}
	
	
}
