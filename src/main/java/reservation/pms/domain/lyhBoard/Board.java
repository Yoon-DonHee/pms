package reservation.pms.domain.lyhBoard;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import reservation.pms.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LYH_BOARD")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type")
	private Integer type;

	//@Column(length = 100, nullable = false, name = "title", columnDefinition = "VARCHAR(100) COMMENT '타이틀' ", unique = true)
	@Column(length = 100, nullable = false, name = "title")
	private String title;

	@Column(length = 3000, nullable = false, name = "content")
	private String content;

	@Column(name = "member_no")
	private Integer memberNo;

	@Column(name = "likes")
	private Integer likes;

	@Column(name = "counts")
	private Integer counts;

	@Builder
	public Board(
			Integer id, Integer type, String title, String content, Integer memberNo
		) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
	}

	public void update(
			Integer type
			, String title
			, String content
			, Integer memberNo
			) {
		this.type = type;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
	}
}