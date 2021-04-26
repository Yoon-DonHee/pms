package reservation.pms.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.pms.domain.Community;

@Data
public class CommunityDto {

	//글생성 @Builder 이용
	@Data
	public static class createOrder{
		private Integer type;
		private String title;
		private String contents;
		private Integer memberNo;
		private LocalDateTime createdTime;
		
		public Community save() {
			return Community.builder()
					.type(type)
					.title(title)
					.contents(contents)
					.memberNo(memberNo)
					.createdTime(createdTime)
					.build();
		}
	}
	
	//글 파라메타 받기
	@Data
	@NoArgsConstructor
	public static class info{
		private Integer no;
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
