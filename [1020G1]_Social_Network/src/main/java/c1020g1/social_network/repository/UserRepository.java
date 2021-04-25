package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * \n" +
            "FROM `user` \n" +
            "WHERE user_id = ?1", nativeQuery = true)
    User getUserById(Integer userId);
}
