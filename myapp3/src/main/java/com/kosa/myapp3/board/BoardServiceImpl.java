package com.kosa.myapp3.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="boardDao")
	BoardDao boardDao;
	
	@Override
	public int getTotalCnt(BoardDto dto) {
		return boardDao.getTotalCnt(dto);
	}

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return boardDao.getList(dto);
	}

	@Override
	public void insert(BoardDto dto) {
		boardDao.insert(dto);
	}
	
	@Override
	public void modify(BoardDto dto) {
		boardDao.modify(dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		return boardDao.getView(dto);
	}

	@Override
	public void reply(BoardDto dto) {
		// dto - seq : �θ���� seq
		// �θ���� ���� �ʿ�
		BoardDto parentDto = boardDao.getView(dto);
		dto.setGroup_id(parentDto.getGroup_id());
		dto.setDepth(parentDto.getDepth()+1);
		dto.setG_level(parentDto.getG_level()+1);
		
		// �θ�� ������� update level
		boardDao.update_level(parentDto); // �θ��
		boardDao.reply(dto); // ����� �߰���
	}

	@Override
	public void delete(BoardDto dto) {
		boardDao.delete(dto);
	}





}
