package reservation.pms.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.pms.domain.Reservation;

@Getter @Setter
@NoArgsConstructor  //생성 매서드 쓰도록 제약
public class AccountDto {
    /*
     * entity 에 직접 접근 및 setter를 이용하면 유지보수 어려워지는 이유로
     * controller에서 DB객체 접근할때 사용 
     * */
	
	private Long id; //고객 PK

    @NotEmpty(message = "아이디는 필수 입력해야합니다.")
    private String userId; //고객 아이디
    
    @NotEmpty(message = "패스워드는 필수 입력해야합니다.")
    private String userPw; //암호

    @NotEmpty(message = "이름은 필수 입력해야합니다.")
    private String userName; //고객 이름
    
    @NotEmpty(message = "전화번호는 필수 입력해야합니다.")
    private String hp; //휴대전화 번호

    private LocalDateTime createDate; //고객 계정 생성일
    
    // == 생성 메서드 ==//
    
    /*
     * 고객 생성
     * */
    public static AccountDto createAccount (String userId, String userPw, String UserName, String hp, Reservation reservation) { 

    	AccountDto accountDto = new AccountDto();
 
		accountDto.setUserId(userId); 
		accountDto.setUserPw(userPw);
		accountDto.setUserName(UserName); 
		accountDto.setHp(hp);
		accountDto.setCreateDate(LocalDateTime.now()); 
		
		return accountDto; 
 	}
}
