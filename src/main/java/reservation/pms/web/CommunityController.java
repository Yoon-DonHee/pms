package reservation.pms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservation.pms.model.CommunityDto;
import reservation.pms.service.CommunityService;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;

	// 전체글 조회 (pageable 이용)
	@GetMapping("")
	public Page<CommunityDto.info> getAllCommunity(Pageable pageable, CommunityDto.search searchDto){

		Page<CommunityDto.info> pageCommunityDto = communityService.findAllByPageable(searchDto , pageable);
			return  pageCommunityDto;
	}

	/*
	 * DTO 사용 버전
	 * 파라미터로 엔티티를 직접 접속하지 않고 DTO를 통해 처리
	 * builder 사용
	 * */
	//글 등록
	@PostMapping("")
	public ResponseEntity<CommunityDto.info> createCommunity(@RequestBody CommunityDto.save saveDto) {
		CommunityDto.info infoDto = communityService.createCommunity(saveDto);

		return ResponseEntity.ok(infoDto);
	}


//	// 글 조회
//	@GetMapping("/{id}")
//	public ResponseEntity<CommunityDto.info> getcommunityById(
//			@PathVariable Long id) {
//
//		return communityService.getcommunity(id);
//	}

	// 글 조회
	@GetMapping("/{id}")
	public CommunityDto.info getCommunityById(@PathVariable Long id) {
		CommunityDto.info infoDto = communityService.getCommunity(id);
		return infoDto;
	}

	// 글 수정
//	@PutMapping("/{id}")
//	public ResponseEntity<Community> updateCommunityById(
//			@PathVariable Long id, @RequestBody Community community){
//
//		return communityService.updateCommunity(id, community);
//	}

	// 글 수정
	@PutMapping("{id}")
	public CommunityDto.info updateCommunity(@PathVariable Long id, @RequestBody CommunityDto.save saveDto){
		CommunityDto.info infoDto = communityService.updatecommunity(id, saveDto);

		return infoDto;
	}

	/*// delete community
	@DeleteMapping("/community/{id}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityById(@PathVariable Integer id) {
		
		return communityService.deletecommunity(id);
	}*/
	
	//글 삭제
	@PostMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCommunityById(@PathVariable Long id) {
		
		return communityService.deleteCommunity(id);
	}
}
