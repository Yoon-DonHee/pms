package reservation.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reservation.pms.domain.lyhBoard.Board;
import reservation.pms.domain.lyhBoard.BoardRepository;
import reservation.pms.dto.LyhBoardDto;

import java.util.List;

@Service
@Transactional
public class LyhBoardService {

	@Autowired
	private BoardRepository boardRepository;

	public Page<LyhBoardDto.info> findAllbyPageable(LyhBoardDto.search searchDto, Pageable pageable){
		Page<LyhBoardDto.info> pageSearchBoard = boardRepository.findAllBySearch(searchDto, pageable);
		return pageSearchBoard;
	}

	public LyhBoardDto.info findbyId(Integer id) {
		Board board = boardRepository.getOne(id);
		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

	public LyhBoardDto.info addBoard(LyhBoardDto.save saveDto) {
		Board board = boardRepository.save(saveDto.toEntity());
		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public LyhBoardDto.info modifyBoard(LyhBoardDto.save saveDto) {
		Board board = boardRepository.getOne(saveDto.getId());
		Board saveBoard = saveDto.toEntity();
		board.update(saveBoard.getType(), saveBoard.getTitle(), saveBoard.getContent(), saveBoard.getMemberNo());
		
		LyhBoardDto.info infoDto = new LyhBoardDto.info(board);
		return infoDto;
	}

	public long removeAllByIdIn(Integer[] ids) {
		return boardRepository.deleteAllByIdIn(ids);
	}
}