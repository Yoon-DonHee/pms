package reservation.pms.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name ="tb_account")
public class Account {

    @Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="account_id")
    private Long id; 
	
	@Column(nullable = false, name ="account_userId")
	private String userId;
	  
	@Column(nullable = false, name ="account_userPw")
	private String userPw;
	  
	@Column(nullable = false, name ="account_userName")
	private String userName;
	  
	@Column(nullable = false, name ="account_hp")
	private String hp;
	  
	@Column(nullable = false, name ="account_createTime")
	private LocalDateTime createDate;
	 
	/*
	 * 양방향
	 * 고객 :: 예약현황 
	 */
    @OneToMany(mappedBy = "account")
    private List<Reservation> reservation = new ArrayList<>();
}
