package reservation.pms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservation.pms.domain.Community;
import reservation.pms.model.CommunityDto;
import reservation.pms.service.CommunityService;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;

	// 전체글 조회
/*	@GetMapping("/community")
	public List<Community> getAllcommunitys() {
		return communityService.getAllcommunity();
	}*/

	// 전체글 조회 (페이징)
	@GetMapping("")
	public ResponseEntity<Map> getAllcommunitys2(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;

		return communityService.getPagingCommunity(p_num);
	}

	/*
	 * DTO 사용 버전
	 * 파라미터로 엔티티를 직접 접속하지 않고 DTO를 통해 처리
	 * builder 사용
	 * */
	//글 등록
	@PostMapping("")
	public CommunityDto.info createcommunity(@RequestBody CommunityDto.saveCommunity saveDto) {
		CommunityDto.info infoDto = communityService.createCommunity(saveDto);
		
		return infoDto;
	}
	
	// 글 조회
	@GetMapping("/{no}")
	public ResponseEntity<Community> getcommunityByNo(
			@PathVariable Integer no) {
		
		return communityService.getcommunity(no);
	}

	// 글 수정
	@PutMapping("/{no}")
	public ResponseEntity<Community> updatecommunityByNo(
			@PathVariable Integer no, @RequestBody Community community){
		
		return communityService.updatecommunity(no, community);
	}

	/*// delete community
	@DeleteMapping("/community/{no}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityByNo(@PathVariable Integer no) {
		
		return communityService.deletecommunity(no);
	}*/
	
	//글 삭제
	@PostMapping("/{no}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityByNo(@PathVariable Integer no) {
		
		return communityService.deletecommunity(no);
	}
}
