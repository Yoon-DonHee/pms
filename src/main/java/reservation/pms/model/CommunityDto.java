package reservation.pms.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.pms.domain.Community;

import java.time.LocalDateTime;

@Data
public class CommunityDto {

	// 검색 (제목)
	@Getter @Setter
	public static class search {
		private String title;
	}
	//글생성 @Builder 이용
	@Data
	public static class saveCommunity{
		private Long id;
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;

		public Community save() {
			return Community.builder()
					.type(type)
					.title(title)
					.contents(contents)
					.memberNo(memberNo)
					.build();
		}
	}
	
	//글 파라메타 받기
	@Data
	@NoArgsConstructor
	public static class info{
		private Long no;
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;
		private Integer likes;
		private Integer counts;
		private LocalDateTime createdTime;
		private LocalDateTime updatedTime;
		
		public info(Community entity) {
			no = entity.getNo();
			type = entity.getType();
			title = entity.getTitle();
			contents = entity.getContents();
			memberNo = entity.getMemberNo();
			likes = entity.getLikes();
			counts = entity.getCounts();
			createdTime = entity.getCreatedTime();
			updatedTime = entity.getUpdatedTime();
		}
	}
	

}
