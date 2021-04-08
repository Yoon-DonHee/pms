package reservation.pms.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "tb_reservation")
public class Reservation {

	@Id
	@GeneratedValue
	@Column(nullable = false, name = "reservation_id")
	private Long id;
	
	@Column(nullable = false, name ="reservation_startDate") 
	private LocalDateTime startDate; //숙박시작일
	  
	@Column(nullable = false, name ="reservation_enbDate") 
	private LocalDateTime endDate; //숙박종료일
	  
	@Enumerated(EnumType.STRING) //순서가 변경되도 문제 없도록 STRING 으로 사용한다 
	private RoomStatus status; //방 예약 상태 READY, COMP
	  
	/*
	 * 양방향 
	 * 예약 :: 고객
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="account_id") 
	private Account account;
	  
	  
	/*
	 * 양방향 
	 * 예약 :: 방
	 */  
	@OneToMany(mappedBy = "reservation") 
	private List<Room> room = new ArrayList<>();
	 
}
