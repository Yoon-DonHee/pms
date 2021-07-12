package reservation.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.pms.domain.Community;
import reservation.pms.exception.ResourceNotFoundException;
import reservation.pms.model.CommunityDto;
import reservation.pms.repository.CommunityRepo;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CommunityService {

	@Autowired
	private CommunityRepo communityRepo;

	//pageable 이용 전체 글 조회
	public Page<CommunityDto.info> findAllByPageable(CommunityDto.search searchDto, Pageable pageable){
		Page<CommunityDto.info> pageSearchCommunity = communityRepo.findAllBySearch(searchDto, pageable);
		return pageSearchCommunity;
	}

	//글 작성 (빌더사용)
	public CommunityDto.info createCommunity(CommunityDto.save saveDto){
		Community community = communityRepo.save(saveDto.toEntity());
		CommunityDto.info infoDto = new CommunityDto.info(community);
		
		return infoDto;
	}

//	// 글 상세보기
//	public ResponseEntity<CommunityDto.info> getcommunity(Long id) {
//
//		//기본조회
//		Community community = communityRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by id : ["+id+"]"));
//		//조회수증가
//		community.increaseCounts();
//		//CommunityDto 객체 생성
//		CommunityDto.info infoDto = new CommunityDto.info(community);
//
//		return ResponseEntity.ok(infoDto);
//	}

	// 글 상세보기
	public CommunityDto.info getCommunity(Long id) {

		//기본조회
		Community community = communityRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by id : ["+id+"]"));
		//조회수증가
		community.increaseCounts();
		//CommunityDto 객체 생성
		CommunityDto.info infoDto = new CommunityDto.info(community);

		return infoDto;
	}

	// 글 수정
	public ResponseEntity<Community> updateCommunity(Long id, Community updatedcommunity) {
		Community community = communityRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by id : ["+id+"]"));
		community.setType(updatedcommunity.getType());
		community.setTitle(updatedcommunity.getTitle());
		community.setContents(updatedcommunity.getContents());
		community.setMemberNo(updatedcommunity.getMemberNo());

		Community endUpdatedcommunity = communityRepo.save(community);
		return ResponseEntity.ok(endUpdatedcommunity);
	}

	public CommunityDto.info updatecommunity(Long id, CommunityDto.save saveDto) {
		Community community = communityRepo.getOne(id);

		Community updateCommunity = saveDto.toEntity();
		community.update(updateCommunity.getType(), updateCommunity.getTitle(), updateCommunity.getContents(), updateCommunity.getMemberNo());

		CommunityDto.info infoDto = new CommunityDto.info(community);

		return infoDto;
	}

	// 글 삭제
	public ResponseEntity<Map<String, Boolean>> deleteCommunity(Long id) {
		Community community = communityRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by id : ["+id+"]"));
		
		communityRepo.delete(community);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted community Data by id : ["+id+"]", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}