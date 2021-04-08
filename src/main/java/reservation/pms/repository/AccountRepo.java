package reservation.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reservation.pms.domain.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{
}
/*
 * 1.JpaRepository > 기본 CRUD 기능 제공
 * 
 * 2.네이밍 규칙
 *  쿼리요청 > findBy로 시작 
 *  쿼리결과 레코드 수 요청 > countBy로 시작
 * */
