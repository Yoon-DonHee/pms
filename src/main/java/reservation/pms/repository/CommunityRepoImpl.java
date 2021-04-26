package reservation.pms.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

import reservation.pms.domain.Community;
import reservation.pms.domain.QCommunity;

public class CommunityRepoImpl extends QuerydslRepositorySupport implements CommunityRepoCustom{

	public CommunityRepoImpl() {
		super(Community.class);
	}
	
	//Id 로 조회
	@Override
	public Community findByNo(Integer no) {
		QCommunity community = QCommunity.community;
		
		JPQLQuery<Community> query = from(community).where(community.no.eq(no));
		return query.fetchOne();
				
	}
	//전체 리스트 조회
	public List<Community> AllCommunity() {
		QCommunity community = QCommunity.community;
		
		JPQLQuery<Community> query = from(community).orderBy(community.no.desc());
		return query.fetch();
				
				
	}

}
