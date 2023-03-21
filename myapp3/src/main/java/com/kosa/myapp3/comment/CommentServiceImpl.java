package com.kosa.myapp3.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource(name="commentDao")
	CommentDao dao;
	
	@Override
	public List<CommentDto> getList(CommentDto dto) {
		// TODO Auto-generated method stub
		return dao.getList(dto);
	}

	@Override
	public void insert(CommentDto dto) {
		dao.insert(dto);
	}

}
