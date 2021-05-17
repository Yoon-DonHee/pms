package reservation.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reservation.pms.domain.Community;
import reservation.pms.exception.ResourceNotFoundException;
import reservation.pms.model.CommunityDto;
import reservation.pms.repository.CommunityRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepo communityRepo;
	
	/*
	 * 전체글 조회
	 * 최신글이 위로 오도록 sort
	 **/
	public List<Community> getAllcommunity() {
		return communityRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
		//return communityRepo.AllCommunity();
	}
	
	//페이징 api
	public Page<Community> getAllcommunity2(Pageable pageable){
		return communityRepo.findAll(pageable);
	}

	// create community
//	public Community createcommunity(Community community) {
//		community.setCreatedTime(LocalDateTime.now());
//		return communityRepo.save(community);
//		
//	}
	
	//글생성 (빌더사용)
	public CommunityDto.info createCommunity(CommunityDto.createOrder saveDto){
		Community community = communityRepo.save(saveDto.save());
		CommunityDto.info infoDto = new CommunityDto.info(community);
		
		return infoDto;
	}
	
	// get community one by id
	public ResponseEntity<Community> getcommunity(Integer no) {

		//기존 기본 CRUD 조회
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		//조회수증가
		community.setCounts(community.getCounts() + 1);
		communityRepo.save(community);
		
		return ResponseEntity.ok(community);
		//QueryDsl 조회
		//Community community = communityRepo.findByNo(no);
	}

	// update community 
	public ResponseEntity<Community> updatecommunity(Integer no, Community updatedcommunity) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		community.setType(updatedcommunity.getType());
		community.setTitle(updatedcommunity.getTitle());
		community.setContents(updatedcommunity.getContents());

		Community endUpdatedcommunity = communityRepo.save(community);
		return ResponseEntity.ok(endUpdatedcommunity);
	}

	// delete community
	public ResponseEntity<Map<String, Boolean>> deletecommunity(Integer no) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		
		communityRepo.delete(community);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted community Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}