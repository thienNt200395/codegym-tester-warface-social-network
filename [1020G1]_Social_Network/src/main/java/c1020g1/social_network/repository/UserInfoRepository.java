package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<User , Integer> {

    @Query(value = "select * from user u where u.user_id = ?1", nativeQuery = true)
    User findUserByUserId(Integer id);
}
