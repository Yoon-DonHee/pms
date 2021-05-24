package reservation.pms.domain.lyhBoard;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import reservation.pms.common.enums.CheckEnum;
import reservation.pms.common.enums.RadioEnum;
import reservation.pms.dto.LyhBoardDto;
import reservation.pms.dto.LyhBoardDto.info;
import reservation.pms.dto.LyhBoardDto.search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import static org.hibernate.internal.util.StringHelper.isEmpty;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

	public BoardRepositoryImpl() {
		super(Board.class);
	}

	QBoard Board = QBoard.board;

	@Override
	public Page<info> findAllBySearch(search searchDto, Pageable pageable) {


		JPQLQuery<LyhBoardDto.info> query = from(Board)
				.select(Projections.constructor(LyhBoardDto.info.class, Board));

		query.where(titleContains(searchDto.getTitle()),
				typeEq(searchDto.getType()),
				checkCol1Eq(searchDto.getCheckCol1()),
				radioCol1Eq(searchDto.getRadioCol1())
		);



		int offset = pageable.getPageNumber()*pageable.getPageSize();
		query
			.offset(offset)
			.limit(pageable.getPageSize());
		query = getQuerydsl().applyPagination(pageable, query);

		QueryResults<LyhBoardDto.info> result = (QueryResults<LyhBoardDto.info>)query.fetchResults();
		Page<LyhBoardDto.info> pageResult = new PageImpl<>(result.getResults(), pageable, result.getTotal());

		return pageResult;
	}

	private BooleanExpression titleContains(String title) {
		return isEmpty(title) ? null : Board.title.contains(title);
	}

	private BooleanExpression typeEq(Object type) {
		return type == null ? null : Board.type.eq(Integer.parseInt(type.toString()));
	}

	private BooleanExpression checkCol1Eq(CheckEnum checkCol1) {
		return checkCol1 == null || checkCol1.equals(CheckEnum.N) ? null : Board.checkCol1.eq(checkCol1);
	}

	private BooleanExpression radioCol1Eq(RadioEnum radioCol1) {
		return radioCol1 == null ? null : Board.radioCol1.eq(radioCol1);
	}

	@Override
	public long deleteAllByIdIn(Long[] ids) {
		return delete(Board).where(Board.id.in(ids)).execute();
	}

}