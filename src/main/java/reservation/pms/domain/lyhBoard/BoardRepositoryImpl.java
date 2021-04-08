package reservation.pms.domain.lyhBoard;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import reservation.pms.dto.LyhBoardDto;
import reservation.pms.dto.LyhBoardDto.info;
import reservation.pms.dto.LyhBoardDto.search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

	public BoardRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Page<info> findAllBySearch(search searchDto, Pageable pageable) {
		QBoard Board = QBoard.board;

		JPQLQuery<LyhBoardDto.info> query = from(Board)
				.select(Projections.constructor(LyhBoardDto.info.class, Board));
		if(! StringUtils.isEmpty(searchDto.getTitle())) {
			query.where(Board.title.contains(searchDto.getTitle()));
		}
		if(! StringUtils.isEmpty(searchDto.getCreatedBy())) {
			query.where(Board.createdBy.contains(searchDto.getCreatedBy()));
		}

		int offset = pageable.getPageNumber()*pageable.getPageSize();
		query
			.offset(offset)
			.limit(pageable.getPageSize());
		query = getQuerydsl().applyPagination(pageable, query);

		QueryResults<LyhBoardDto.info> result = (QueryResults<LyhBoardDto.info>)query.fetchResults();
		Page<LyhBoardDto.info> pageResult = new PageImpl<>(result.getResults(), pageable, result.getTotal());

		return pageResult;
	}

	@Override
	public long deleteAllByIdIn(Integer[] ids) {
		QBoard board = QBoard.board;
		return delete(board).where(board.id.in(ids)).execute();
	}

}