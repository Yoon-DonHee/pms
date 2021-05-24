package reservation.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import reservation.pms.common.enums.CheckEnum;
import reservation.pms.common.enums.EnumNamePattern;
import reservation.pms.common.enums.RadioEnum;
import reservation.pms.domain.lyhBoard.AttachFile;
import reservation.pms.domain.lyhBoard.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LyhBoardDto {
	
	@Getter @Setter
	public static class search {
		private String title;
		private Integer type;
		private CheckEnum checkCol1;
		private RadioEnum radioCol1;
		private String createdBy;
	}
	
	@Getter @Setter
	public static class save {
		private Long id;
		//@NotNull
		//@NotEmpty @Size(min = 1, max = 100)
		private Integer type;
		@NotEmpty @Size(min = 1, max = 100) private String title;
		@NotEmpty @Size(min = 1, max = 3000) private String content;
		private Integer memberNo;
		private CheckEnum checkCol1;
		@EnumNamePattern(enumCodes = {"OPTION1","OPTION2"}, nullAble = true)
		private RadioEnum radioCol1;

		
		public Board toEntity() {
			return Board.builder()
					.type(type)
					.title(title)
					.content(content)
					.memberNo(memberNo)
					.checkCol1(checkCol1)
					.radioCol1(radioCol1)
					.build();
		}
	}

	@Getter @Setter @NoArgsConstructor
	public static class info {
		private Long id;
		private Integer type;
		private String title;
		private String content;
		private CheckEnum checkCol1;
		private RadioEnum radioCol1;
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
			checkCol1 = entity.getCheckCol1();
			radioCol1 = entity.getRadioCol1();
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
	}
}
