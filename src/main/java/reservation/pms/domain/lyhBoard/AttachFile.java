package reservation.pms.domain.lyhBoard;

import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import reservation.pms.domain.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Builder
@ToString
@Audited
public class AttachFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACH_FILE_ID")
    private Long id; //FileID

    @Column(length = 150, nullable = false)
    private String originalFilename; //원본파일명

    @Column(length = 150, nullable = false)
    private String fileName; //파일명

    @Column(nullable = false)
    private Long fileSize; //파일사이즈

    @Column(length = 150, nullable = false)
    private String filePath; //파일경로

    @Column(length = 150, nullable = false)
    private String fileUrl; //파일Url

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public void setBoard(Board board) {
        if(this.board != null)
            this.board.getAttachFile().remove(this);
        this.board = board;
        board.getAttachFile().add(this);
    }

}
