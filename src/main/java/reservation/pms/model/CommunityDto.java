package reservation.pms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.pms.domain.Community;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommunityDto {

	// 검색 (제목,타입)
	@Getter @Setter
	public static class search {
		private String title;
		private Integer type;
	}
	//글생성 @Builder 이용
	@Getter @Setter
	public static class saveCommunity{
		private Long id;
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;

		public Community save() {
			//Entity 의 생성자를 통해 데이터 저장시 이용
			return Community.builder()
					.type(type)
					.title(title)
					.contents(contents)
					.memberNo(memberNo)
					.build();
		}
	}
	
	//글 파라메타 받기
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

		public info(Community entity) {
			id = entity.getId();
			type = entity.getType();
			title = entity.getTitle();
			contents = entity.getContents();
			memberNo = entity.getMemberNo();
			likes = entity.getLikes();
			counts = entity.getCounts();
			createdTime = toStringDateTime(entity.getCreatedTime());
			updatedTime = toStringDateTime(entity.getUpdatedTime());
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
