package reservation.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reservation.pms.domain.Community;

import java.util.List;

public interface CommunityRepo extends JpaRepository<Community, Long>, CommunityRepoCustom {
    /*
    * 기본 CRUD (별도 메서드 선언없이 사용),  JPQL 문 사용(메서드 선언하여 기본 CRUD 외 검색조건, 페이징등 사용) , CustomRepo(QueryDSL)상속
    * */

    public final static String SELECT_COMMUNITY_LIST_PAGED = ""
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
            final Integer objectEndNum);
}
