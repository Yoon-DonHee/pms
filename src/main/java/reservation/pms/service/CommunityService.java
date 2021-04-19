package reservation.pms.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reservation.pms.domain.Community;
import reservation.pms.exception.ResourceNotFoundException;
import reservation.pms.repository.CommunityRepo;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepo communityRepo;
	
	// get communitys data
	public List<Community> getAllcommunity() {
		return communityRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
	}

	// create community
	public Community createcommunity(Community community) {
		community.setCreatedTime(LocalDateTime.now());
		return communityRepo.save(community);
	}

	// get community one by id
	public ResponseEntity<Community> getcommunity(Integer no) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		return ResponseEntity.ok(community);
	}

	// update community 
	public ResponseEntity<Community> updatecommunity(
			Integer no, Community updatedcommunity) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		community.setType(updatedcommunity.getType());
		community.setTitle(updatedcommunity.getTitle());
		community.setContents(updatedcommunity.getContents());
		community.setUpdatedTime(LocalDateTime.now());
		
		Community endUpdatedcommunity = communityRepo.save(community);
		return ResponseEntity.ok(endUpdatedcommunity);
	}

	// delete community
	public ResponseEntity<Map<String, Boolean>> deletecommunity(
			Integer no) {
		Community community = communityRepo.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist community Data by no : ["+no+"]"));
		
		communityRepo.delete(community);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted community Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}