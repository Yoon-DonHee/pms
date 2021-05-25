package reservation.pms.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import reservation.pms.domain.Community;
import reservation.pms.domain.QCommunity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommunityRepoImpl extends QuerydslRepositorySupport implements CommunityRepoCustom{

	public CommunityRepoImpl() {
		super(Community.class);
	}

	/*
	* QueryDSL 이용
	* */

	//Id 로 조회
	@Override
	public Community findByNo(Integer no) {
		QCommunity community = QCommunity.community;
		
		JPQLQuery<Community> query = from(community).where(community.no.eq(no));
		return query.fetchOne();
				
	}
	//전체 리스트 조회
	@Override
	public List<Community> AllCommunity() {
		QCommunity community = QCommunity.community;
		
		JPQLQuery<Community> query = from(community).orderBy(community.no.desc());
		return query.fetch();
				
				
	}
}
