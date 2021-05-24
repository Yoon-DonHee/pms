package reservation.pms.domain.lyhBoard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {
    List<AttachFile> findAllByBoardId(Long id);
}
