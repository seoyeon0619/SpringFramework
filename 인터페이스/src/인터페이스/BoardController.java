package 인터페이스;

import java.util.List;

public class BoardController {
	IBoardDao dao = new BoardDao();
	
	public void output()
	{
		List<BoardDto> list = dao.getList();
		for(int i=0; i<list.size(); i++)
		{
			BoardDto dto = list.get(i);
			System.out.println(dto.toString());
		}
	}
}
