package reservation.pms.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reservation.pms.domain.Community;
import reservation.pms.service.CommunityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommunityController {
	@Autowired
	private CommunityService communityService;

	// get all community 
	@GetMapping("/community")
	public List<Community> getAllcommunitys() {
		return communityService.getAllcommunity();
	}

	// create community
	@PostMapping("/community")
	public Community createcommunity(@RequestBody Community community) {
		return communityService.createcommunity(community);
	}

	// get community
	@GetMapping("/community/{no}")
	public ResponseEntity<Community> getcommunityByNo(
			@PathVariable Integer no) {
		
		return communityService.getcommunity(no);
	}

	// update community
	@PutMapping("/community/{no}")
	public ResponseEntity<Community> updatecommunityByNo(
			@PathVariable Integer no, @RequestBody Community community){
		
		return communityService.updatecommunity(no, community);
	}

	// delete community
	@DeleteMapping("/community/{no}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityByNo(@PathVariable Integer no) {
		
		return communityService.deletecommunity(no);
	}
	
	@PostMapping("/community/{no}")
	public ResponseEntity<Map<String, Boolean>> deletecommunityByNo2(@PathVariable Integer no) {
		
		return communityService.deletecommunity(no);
	}
}
