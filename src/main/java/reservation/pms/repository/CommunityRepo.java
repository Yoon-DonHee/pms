package reservation.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import reservation.pms.domain.Community;

public interface CommunityRepo extends JpaRepository<Community, Integer> {
	
}
