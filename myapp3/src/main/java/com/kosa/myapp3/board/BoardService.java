package com.kosa.myapp3.board;

import java.util.List;

public interface BoardService {
	int getTotalCnt(BoardDto dto);
	List<BoardDto> getList(BoardDto dto);
	BoardDto getView(BoardDto dto);
	void insert(BoardDto dto);
	void reply(BoardDto dto);
	void modify(BoardDto dto);
	void delete(BoardDto dto);
}
