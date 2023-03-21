package 인터페이스;

import java.util.List;

// 인터페이스는 상수, 함수 header 부분만 -> 객체 만들게 아니라서
public interface IBoardDao {
	public List<BoardDto> getList(); // 기본적으로 public 
	
	
}
