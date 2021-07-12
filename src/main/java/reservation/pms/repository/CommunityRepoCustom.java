package reservation.pms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reservation.pms.domain.Community;
import reservation.pms.model.CommunityDto;

import java.util.List;

public interface CommunityRepoCustom {
	/*
	 * 쿼리메소드로 표현이 불가능한 기능 작성 => 실제 로직구현은 Impl에서
	 * */

	//전체 리스트 조회
	public List<Community> AllCommunity(Sort id);

	//pageable 이용 전체 리스트 조회
	public Page<CommunityDto.info> findAllBySearch(CommunityDto.search searchDto, Pageable pageable);
}
