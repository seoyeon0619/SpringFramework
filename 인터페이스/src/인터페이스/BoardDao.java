package �������̽�;

import java.util.ArrayList;
import java.util.List;

public class BoardDao implements IBoardDao{

	List<BoardDto> list = new ArrayList<BoardDto>();
	
	public BoardDao() {
		super();
		list.add(new BoardDto("����1", "�ۼ���1", "����1"));
		list.add(new BoardDto("����2", "�ۼ���2", "����2"));
		list.add(new BoardDto("����3", "�ۼ���3", "����3"));
		list.add(new BoardDto("����4", "�ۼ���4", "����4"));
		list.add(new BoardDto("����5", "�ۼ���5", "����5"));
	}
	
	@Override
	public List<BoardDto> getList() {
		return list;
	}
	
}
