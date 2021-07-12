package reservation.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reservation.pms.domain.Community;

public interface CommunityRepo extends JpaRepository<Community, Long>, CommunityRepoCustom {
    /*
    * 기본 CRUD (별도 메서드 선언없이 사용), 쿼리메소드로 표현 가능한 기능 추가(쿼리메소드 규칙이용)
    * */

   /* public final static String SELECT_COMMUNITY_LIST_PAGED = ""
            + "SELECT "
            + "community_id,"
            + "type,"
            + "title,"
            + "contents,"
            + "member_no,"
            + "created_time,"
            + "updated_time,"
            + "likes,"
            + "counts"
            + " FROM tb_community WHERE 0 < community_id "
            + "ORDER BY community_id DESC LIMIT ?1, ?2";


    @Query(value = SELECT_COMMUNITY_LIST_PAGED, nativeQuery = true)
    List<Community> findFromTo(
            final Integer objectStartNum,
            final Integer objectEndNum);*/
}
