package reservation.pms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.pms.domain.Community;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommunityDto {

	//검색 (제목,타입)
	@Setter @Getter
	public static class search{
		private Integer type;
		private String title;
	}

	//글 저장 @Builder 패턴 이용
	@Getter @Setter
	public static class save{
		private Long id;
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;

		public Community toEntity(){
			//Entity 의  builder 생성자를 통해 데이터 저장시 이용
			return Community.builder()
					.type(type)
					.contents(contents)
					.title(title)
					.memberNo(memberNo)
					.build();
		}
	}

	//entity <-> DTO 변환 객체
	@Getter @Setter
	@NoArgsConstructor
	public static class info{
		private Long id;
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;
		private Integer likes;
		private Integer counts;
		private String createdTime;
		private String updatedTime;

		public info(Community toEntity){
			id = toEntity.getId();
			type = toEntity.getType();
			title = toEntity.getTitle();
			contents = toEntity.getContents();
			memberNo = toEntity.getMemberNo();
			likes = toEntity.getLikes();
			counts = toEntity.getCounts();
			createdTime = toStringDateTime(toEntity.getCreatedTime());
			updatedTime = toStringDateTime(toEntity.getUpdatedTime());

		}
		//시간표기
		private String toStringDateTime(LocalDateTime localDateTime){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return Optional.ofNullable(localDateTime)
					.map(formatter::format)
					.orElse("");
		}
	}

}
