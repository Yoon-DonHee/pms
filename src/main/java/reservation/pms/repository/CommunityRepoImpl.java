package reservation.pms.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import reservation.pms.domain.Community;
import reservation.pms.domain.QCommunity;
import reservation.pms.model.CommunityDto;

import java.util.List;

import static org.hibernate.internal.util.StringHelper.isEmpty;

public class CommunityRepoImpl extends QuerydslRepositorySupport implements CommunityRepoCustom{
	public CommunityRepoImpl() {
		super(Community.class);
	}	/*
	 * QueryDSL 이용 => Q class 사용
	 * */
	QCommunity community = QCommunity.community;



	//전체 리스트 조회
	@Override
	public List<Community> AllCommunity(Sort id) {
		JPQLQuery<Community> query = from(community).orderBy(community.id.desc());
		return query.fetch();
	}
	@Override
	public Page<CommunityDto.info> findAllBySearch(CommunityDto.search searchDto, Pageable pageable) {

		JPQLQuery<CommunityDto.info> query = from(community)
   	            .select(Projections.constructor(CommunityDto.info.class,community));

		query.where(titleContains(searchDto.getTitle()),
		        typeEq(searchDto.getType())
        );

		/*
		* 페이징
		* 시작 인덱스를 지정하는 offset,
		* 조회할 개수를 지정하는 limit
		* */
		int offset = pageable.getPageNumber()*pageable.getPageSize();
		query
            .offset(offset)
            .limit(pageable.getPageSize());
		query = getQuerydsl().applyPagination(pageable,query)
				.orderBy(community.id.desc());

        QueryResults<CommunityDto.info> result = (QueryResults<CommunityDto.info>)query.fetchResults();
        Page<CommunityDto.info> pageResult = new PageImpl<>(result.getResults(), pageable, result.getTotal());

        return pageResult;
	}

	private BooleanExpression titleContains(String title) {
		return isEmpty(title) ? null : community.title.contains(title);
	}

	private BooleanExpression typeEq(Object type) {
		return type == null ? null : community.type.eq(Integer.parseInt(type.toString()));
	}
}
