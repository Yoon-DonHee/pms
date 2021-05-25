package reservation.pms.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_community")
@Getter @Setter
public class Community extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "member_no")
	private Integer memberNo;
	
	@Column(name = "likes", columnDefinition ="integer default 0")
	private Integer likes;
	
	@Column(name = "counts", columnDefinition ="integer default 0")
	private Integer counts;
	
	@Builder
	public Community(Long no, Integer type, String title, String contents, Integer memberNo, LocalDateTime createdTime, Integer likes, Integer counts) {
		this.no = no;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.memberNo = memberNo;
		this.likes = likes;
		this.counts = counts;
	}
	
}
