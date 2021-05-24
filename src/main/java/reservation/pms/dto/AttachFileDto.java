package reservation.pms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.pms.domain.lyhBoard.AttachFile;

public class AttachFileDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class info {
        private Long id;
        private String originalFilename;
        private String fileName;
        private Long fileSize;
        private String filePath;
        private String fileUrl;

        public info(AttachFile entity) {
            id = entity.getId();
            originalFilename = entity.getOriginalFilename();
            fileName = entity.getFileName();
            fileSize = entity.getFileSize();
            filePath = entity.getFilePath();
            fileUrl = entity.getFileUrl();
        }
    }
}
