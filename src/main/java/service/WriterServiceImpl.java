package service;

import domain.BoardVO;
import mapper.WriterMapper;

public class WriterServiceImpl implements WriterService{

	@Override
	public void insert(BoardVO vo) {
		//mapper 만들어준다.
		WriterMapper mapper = new WriterMapper();
		//mapper의 메소드는 insert이며, 값은 vo를 받는다.
		mapper.insert(vo);
	}

	

}
