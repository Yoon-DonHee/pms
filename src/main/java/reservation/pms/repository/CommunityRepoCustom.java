package reservation.pms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reservation.pms.domain.Community;
import reservation.pms.model.CommunityDto;

import java.util.List;

public interface CommunityRepoCustom {
	/*
	 * QueryDSL 사용관련 기본 CRUD 기능 외 필요한 내용 작성
	 * */

	//전체 리스트 조회
	public List<Community> AllCommunity(Sort id);

	//pageable 이용 전체 리스트 조회
	public Page<CommunityDto.info> findAllBySearch(CommunityDto.search searchDto, Pageable pageable);
}
