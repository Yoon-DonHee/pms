package reservation.pms.repository;

import reservation.pms.domain.Community;

import java.util.List;

public interface CommunityRepoCustom {
	/*
	 * QueryDSL 사용관련 기본 CRUD 기능 외 필요한 내용 작성
	 * */
	
	
	//Id 로 조회
	public Community findByNo(Integer no);
	
	//전체 리스트 조회
	public List<Community> AllCommunity();
}
