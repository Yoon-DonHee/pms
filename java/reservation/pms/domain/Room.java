package reservation.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name ="tb_room")
public class Room {

    @Id @GeneratedValue
    @Column(nullable = false, name ="room_id")
    private Long id; //방 PK
	
	@Column(nullable = false, name ="room_num") 
	private int num; //방 호실
	 
	@Column(nullable = false, name ="room_type") 
	private String type; //방 타입
	  
	@Column(nullable = false, name ="room_price") 
	private double price; //방 가격 (타입별 가격상이)
	
	/*
	 * 양방향 
	 * 방 :: 예약
	 * */
	@ManyToOne 
	@JoinColumn(name="reservation_id") 
	private Reservation reservation;
	
}
