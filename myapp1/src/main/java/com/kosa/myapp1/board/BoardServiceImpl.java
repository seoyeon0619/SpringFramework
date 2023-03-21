package com.kosa.myapp1.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	// 객체는 스프링이 만들어서 우리에게 전달
	// 정밀한 제어 불가
	//@Autowired
	//BoardDao boardDao; 
	
	// 명확한 이름을 기재하여 정밀한 제어 
	// 에러 잡기도 더 편함 
	// 현재 더 많이 쓰는 방식
	// BoardDaoImpl -> @Repository("boardDao")
	@Resource(name="boardDao")
	BoardDao boardDao;
	
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return boardDao.getList(dto);
	}

	@Override
	public BoardDto getView(String seq) {
		return boardDao.getView(seq);
	}

	@Override
	public void insert(BoardDto dto) {
		boardDao.insert(dto);
	}

	@Override
	public void update(BoardDto dto) {
		
	}

	@Override
	public void delete(BoardDto dto) {
		
	}
	
}
