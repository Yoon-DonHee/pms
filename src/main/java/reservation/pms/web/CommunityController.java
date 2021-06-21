package reservation.pms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
/*	@GetMapping("")
	public ResponseEntity<Map> getAllcommunitys2(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;

		return communityService.getPagingCommunity(p_num);
	}*/
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
	public CommunityDto.info createcommunity(@RequestBody CommunityDto.saveCommunity saveDto) {
		CommunityDto.info infoDto = communityService.createCommunity(saveDto);
		
		return infoDto;
	}
	
	// 글 조회
	@GetMapping("/{id}")
	public ResponseEntity<CommunityDto.info> getcommunityById(
			@PathVariable Long id) {
		
		return communityService.getcommunity(id);
	}

	// 글 수정
	@PutMapping("/{id}")
	public ResponseEntity<Community> updatecommunityById(
			@PathVariable Long id, @RequestBody Community community){
		
		return communityService.updatecommunity(id, community);
	}

	/*// delete community
	@DeleteMapping("/community/{id}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityById(@PathVariable Integer id) {
		
		return communityService.deletecommunity(id);
	}*/
	
	//글 삭제
	@PostMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityById(@PathVariable Long id) {
		
		return communityService.deletecommunity(id);
	}
}
