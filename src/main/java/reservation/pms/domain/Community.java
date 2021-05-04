package reservation.pms.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_community")
@Getter @Setter
public class Community {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "member_no")
	private Integer memberNo;
	
	@CreatedDate
	@Column(name = "created_time")
	private LocalDateTime createdTime;
	
	@Column(name = "updated_time")
	private LocalDateTime updatedTime;
	
	@Column(name = "likes", columnDefinition ="integer default 0")
	private Integer likes;
	
	@Column(name = "counts", columnDefinition ="integer default 0")
	private Integer counts;
	
	@Builder
	public Community(Integer no, Integer type, String title, String contents, Integer memberNo, LocalDateTime createdTime, Integer likes, Integer counts) {
		this.no = no;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.memberNo = memberNo;
		this.createdTime = LocalDateTime.now();
		this.likes = likes;
		this.counts = counts;
	}
	
}
