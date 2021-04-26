package reservation.pms.repository;

import java.util.List;

import reservation.pms.domain.Community;

public interface CommunityRepoCustom {
	/*
	 * 기본 CRUD 기능 외 필요한 내용 작성
	 * */
	
	
	//Id 로 조회
	public Community findByNo(Integer no);
	
	//전체 리스트 조회
	public List<Community> AllCommunity();
	
}
