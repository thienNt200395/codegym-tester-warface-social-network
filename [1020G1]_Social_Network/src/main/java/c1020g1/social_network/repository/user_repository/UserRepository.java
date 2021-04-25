package c1020g1.social_network.repository.user_repository;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select c from User c where ?1 = c.userId")
    User findUserByUserId(Integer idUser);
}
