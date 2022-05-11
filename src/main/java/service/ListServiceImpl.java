package service;

import java.util.Collection;

import domain.BoardVO;
import mapper.ListMapper;

public class ListServiceImpl implements ListService{

	@Override
	public Collection<BoardVO> read() {
		//mapper
		return new ListMapper().read();
	}

	

	

}
