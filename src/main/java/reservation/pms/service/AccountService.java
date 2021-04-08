package reservation.pms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import reservation.pms.repository.AccountRepo;

@Service
@Transactional(readOnly = true) //조회 쿼리의 경우 readOnly 는 성능향상됨
@RequiredArgsConstructor // final이 있는 필드만 자동 생성자 주입
public class AccountService {

	private final AccountRepo accountRepo;
}
