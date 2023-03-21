package com.kosa.mycompany.score;

import java.util.List;

public interface ScoreDao {
	List<ScoreDto> getList(ScoreDto dto);
}
