package reservation.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import reservation.pms.domain.lyhBoard.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LyhBoardDto {
	
	@Getter @Setter
	public static class search {
		private String title;
		private String createdBy;
	}
	
	@Getter @Setter
	public static class save {
		private Integer id;
		//@NotNull
		//@NotEmpty @Size(min = 1, max = 100)
		private Integer type;
		@NotEmpty @Size(min = 1, max = 100) private String title;
		@NotEmpty @Size(min = 1, max = 3000) private String content;
		private Integer memberNo;

		
		public Board toEntity() {
			return Board.builder()
					.type(type)
					.title(title)
					.content(content)
					.memberNo(memberNo)
					.build();
		}
	}

	@Getter @Setter @NoArgsConstructor
	public static class info {
		private Integer id;
		private Integer type;
		private String title;
		private String content;
		private Integer memberNo;
		private Integer likes;
		private Integer counts;
		private String createdAt;
		private String createdBy;
		private String modifiedAt;
		private String modifiedBy;
		
		public info(Board entity) {
			id = entity.getId();
			type = entity.getType();
			title = entity.getTitle();
			content = entity.getContent();
			memberNo = entity.getMemberNo();
			likes = entity.getLikes();
			counts = entity.getCounts();
			createdAt = toStringDateTime(entity.getCreatedAt());
			createdBy = entity.getCreatedBy();
			modifiedAt = toStringDateTime(entity.getModifiedAt());
			modifiedBy = entity.getModifiedBy();
		}
		
		private String toStringDateTime(LocalDateTime localDateTime){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return Optional.ofNullable(localDateTime)
					.map(formatter::format)
					.orElse("");
		}

		@Override
		public String toString() {
			return "info{" +
					"id=" + id +
					", type='" + type + '\'' +
					", title='" + title + '\'' +
					", content='" + content + '\'' +
					", memberNo='" + memberNo + '\'' +
					", likes='" + likes + '\'' +
					", counts='" + counts + '\'' +
					", createdAt='" + createdAt + '\'' +
					", createdBy='" + createdBy + '\'' +
					", modifiedAt='" + modifiedAt + '\'' +
					", modifiedBy='" + modifiedBy + '\'' +
					'}';
		}
	}
}
