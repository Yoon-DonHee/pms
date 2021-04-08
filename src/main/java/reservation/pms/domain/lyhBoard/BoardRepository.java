package reservation.pms.domain.lyhBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {

//    리턴타입 {접두어}{도입부}By{프로퍼티 표현식}(조건식)[(And|Or){프로퍼티표현식}(조건식)]{정렬조건}(매개변수)
//    리턴 타입 : E, Optional<E>, List<E>, Page<E>, Slice<E>, Stream<E>
//    접두어 : find, get, query, count, exists ...
//    도입부 : Distinct, First(N), Top(N)
//    프로퍼티 표현식 : Persion, Address, ZipCode => find(Person)ByAddress_ZipCode(...)
//    조건식 : And, Or, IgnoreCase, Between, LessThan, LessThanEqual, GreaterThan, GreaterThanEqual, After, Before, IsNull, Null, Like, NotLike, StartingWith, EndingWith, Containing, Not, In, NotIn, True, False
//    정렬 조건 : OrderBy{프로퍼티}Asc|Desc
//    매개 변수 : Pageable, Sort

    List<Board> findByContentLike(String content);
    List<Board> findByContentContaining(String content);
    List<Board> findByContentContains(String content);
    List<Board> findByContentIsContaining(String content);

    List<Board> findByContentStartsWith(String content);
    List<Board> findByContentEndsWith(String content);

    List<Board> findByContentContainingIgnoreCase(String content);
    List<Board> findByContentNotContaining(String content);
    List<Board> findByContentNotLike(String content);

    @Query("SELECT m FROM Board m WHERE m.content like %:content%")
    List<Board> searchByContentLike(String content);

    @Query("SELECT m FROM Board m WHERE m.content LIKE ?1%")
    List<Board> searchByContentStartsWith(String content);

    //@Query("SELECT m FROM Board m WHERE m.content LIKE %?#{content([0])} escape ?#{escapeCharacter()}")
    //List<Board> searchByContentEndsWith(String content);
}