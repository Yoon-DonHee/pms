package reservation.pms.domain.lyhBoard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import reservation.pms.dto.LyhBoardDto;

public interface BoardRepositoryCustom {

	public Page<LyhBoardDto.info> findAllBySearch(LyhBoardDto.search searchDto, Pageable pageable);

	public long deleteAllByIdIn(Long[] ids);
}