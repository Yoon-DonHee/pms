package reservation.pms.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //테이블로 맵핑하지 않고, 자식 엔티티에게 매핑정보를 상속하기 위한 애노테이션
@EntityListeners(AuditingEntityListener.class) //JPA에게 해당 Entity는 Auditing 기능을 사용한다는 것을 알리는 애노테이션
public class TimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime updatedTime;
}