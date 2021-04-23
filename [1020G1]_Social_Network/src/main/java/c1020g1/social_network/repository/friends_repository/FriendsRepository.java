package c1020g1.social_network.repository.friends_repository;

import c1020g1.social_network.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends,Integer> {
    @Query("select c from Friends c where c.user.userId = ?1")
    List<Friends> findAllFriendById(Integer idUser);
}
