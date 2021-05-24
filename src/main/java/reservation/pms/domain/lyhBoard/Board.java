package reservation.pms.domain.lyhBoard;

import javax.persistence.*;

import reservation.pms.common.converter.RadioAttributeConverter;
import reservation.pms.common.enums.CheckEnum;
import reservation.pms.common.enums.RadioEnum;
import reservation.pms.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LYH_BOARD")
public class Board extends BaseEntity {

	@Id
	@Column(name = "BOARD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type")
	private Integer type = 0;

	//@Column(length = 100, nullable = false, name = "title", columnDefinition = "VARCHAR(100) COMMENT '타이틀' ", unique = true)
	@Column(length = 100, nullable = false, name = "title")
	private String title;

	@Column(length = 3000, nullable = false, name = "content")
	private String content;

	@Column(name = "member_no")
	private Integer memberNo;

	@Column(name = "likes")
	private Integer likes = 0;

	@Column(name = "counts")
	private Integer counts = 0;

	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	private CheckEnum checkCol1;

	@Convert(converter = RadioAttributeConverter.class)
	@Column(length = 1)
	private RadioEnum radioCol1;

	@OneToMany(mappedBy = "board")
	private List<AttachFile> attachFile = new ArrayList<AttachFile>();

	@Builder
	public Board(
			Long id, Integer type, String title, String content, Integer memberNo, CheckEnum checkCol1, RadioEnum radioCol1
		) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
		this.checkCol1 = checkCol1;
		this.radioCol1 = radioCol1;
	}

	public void update(
			Integer type
			, String title
			, String content
			, Integer memberNo
			, CheckEnum checkCol1
			, RadioEnum radioCol1
			) {
		this.type = type;
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
		this.checkCol1 = checkCol1;
		this.radioCol1 = radioCol1;
	}

	public void increaseCounts(

	) {
		if(this.counts == null) this.counts = 1;
		else this.counts += 1;
	}

}