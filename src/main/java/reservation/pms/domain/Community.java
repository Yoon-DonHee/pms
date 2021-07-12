package reservation.pms.domain;

import com.mysema.commons.lang.Assert;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@NotBlank(message = "제목은 필수 입력 해야합니다.")
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;

	@NotNull
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
	* ex) builderClassName = "CommunitySave", builderMethodName = "CommunitySave
	* */
	//@Builder(builderClassName = "saveCommunity", builderMethodName = "saveCommunity")
	@Builder
	public Community(Long id, int type, String title, String contents, int memberNo){
		//builder 패턴으로 객체 생성시 해당 값이 없으면 아래메세지와 함께 오류를 발생시킨다.
		Assert.hasText("title", "제목은 필수 입력입니다.");
		Assert.hasText("memberNo", "게시자는 필수 입력입니다.");

		this.id = id;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.memberNo = memberNo;
	}

	public void update(int type, String title, String contents, int memberNo){
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
