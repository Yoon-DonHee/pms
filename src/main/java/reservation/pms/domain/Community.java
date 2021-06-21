package reservation.pms.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Table(name = "tb_community")
public class Community extends TimeEntity {
	@Id
	@Column(name = "COMMUNITY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "member_no")
	private Integer memberNo;
	
	@Column(name = "likes")
	private Integer likes = 0;
	
	@Column(name = "counts")
	private Integer counts = 0;

	/*
	* @Builder 패턴 생성자
	* 인자가 많을 경우 쉽고 안전하게 객체를 생성할 수 있습니다.
	* 인자의 순서와 상관없이 객체를 생성할 수 있습니다.
	* 적절한 책임을 이름에 부여하여 가독성을 높일 수 있습니다.
	* */
	@Builder
	public Community(Long id, Integer type, String title, String contents, Integer memberNo, LocalDateTime createdTime, Integer likes, Integer counts) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.memberNo = memberNo;
	}

	//조회수 증가
	public void increaseCounts(){
		if(this.counts == null) this.counts =1;
		else this.counts += 1;
	}
	
}
