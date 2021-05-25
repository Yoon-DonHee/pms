package reservation.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.pms.domain.Community;
import reservation.pms.exception.ResourceNotFoundException;
import reservation.pms.model.CommunityDto;
import reservation.pms.repository.CommunityRepo;
import reservation.pms.util.PagingUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommunityService {

	@Autowired
	private CommunityRepo communityRepo;

	/*
	 * 전체글 조회
	 * 최신글이 위로 오도록 sort
	 **/
	public List<Community> getAllcommunity() {
		return communityRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
	}
	// 페이징 글조회
	public ResponseEntity<Map> getPagingCommunity(Integer p_num) {
		Map result = null;

		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		List<Community> list = communityRepo.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		pu.setObjectCountTotal(findAllCount());
		pu.setCalcForPaging();

		System.out.println("p_num : "+p_num);
		System.out.println(pu.toString());

		/*if (list == null || list.size() == 0) {
			return null;
		}*/

		result = new HashMap<>();
		result.put("pagingData", pu);
		result.put("list", list);

		return ResponseEntity.ok(result);
	}

	//전체 글 카운트 조회
	public int findAllCount() {
		return (int) communityRepo.count();
	}
	// 글 작성
//	public Community createcommunity(Community community) {
//		community.setCreatedTime(LocalDateTime.now());
//		return communityRepo.save(community);
//		
//	}
	
	//글 작성 (빌더사용)
	public CommunityDto.info createCommunity(CommunityDto.saveCommunity saveDto){
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

	// 글 수정
	public ResponseEntity<Community> updatecommunity(Integer no, Community updatedcommunity) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		community.setType(updatedcommunity.getType());
		community.setTitle(updatedcommunity.getTitle());
		community.setContents(updatedcommunity.getContents());

		Community endUpdatedcommunity = communityRepo.save(community);
		return ResponseEntity.ok(endUpdatedcommunity);
	}

	// 글 삭제
	public ResponseEntity<Map<String, Boolean>> deletecommunity(Integer no) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		
		communityRepo.delete(community);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted community Data by id : ["+no+"]", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}


}