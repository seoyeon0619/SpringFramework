package com.kosa.mycompany.guestbook;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/* root-context.xml
 <bean id="sm"   class="org.mybatis.spring.SqlSessionTemplate"> <!-- dao와 연동 -->
   <constructor-arg index="0"	ref="sqlSessionFactory" >
   </constructor-arg>
 </bean>
*/

@Repository("guestbookDao")
public class GuestbookDaoImpl implements GuestbookDao{
	// org.mybatis.spring.SqlSessionTemplate
	@Autowired
	SqlSessionTemplate sm;

	@Override
	public List<GuestbookDto> getList(GuestbookDto dto) {
		// 첫번째 파라미터 "Guestbook_getList" : mapper파일(Guestbook.xml)파일 쿼리의 id값 전달
		// 두번째 파라미터 dto : parameterType에 전달됨
		// selectList함수는 xml파일에서 쿼리를 불러와 실행시킨후 
		// 결과를 resultType에서 지정한 객체를 만들고 값을 넣은 후 ArrayList객체를 만들어
		// dto객체 추가후 반환함
		return sm.selectList("Guestbook_getList", dto);
	}

	@Override
	public int getTotalCnt(GuestbookDto dto) {
		return sm.selectOne("Guestbook_getTotalCnt", dto);
	}

	@Override
	public GuestbookDto getView(GuestbookDto dto) {
		// 데이터가 한 건일 경우에는 selectOne함수 호출
		return sm.selectOne("Guestbook_getView", dto);
	}

	@Override
	public void insert(GuestbookDto dto) {
		sm.insert("Guestbook_insert", dto);
	}

	@Override
	public void update(GuestbookDto dto) {
		sm.update("Guestbook_update", dto);
	}

	@Override
	public void delete(GuestbookDto dto) {
		sm.delete("Guestbook_delete", dto);
	}
	
}
