package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update user\n" + "set status_id = ?2\n" + "where id = ?1", nativeQuery = true)
    void updateStatus(Integer userId, Integer status_id);

    @Transactional
    @Modifying
    @Query(value = "update user\n" + "set avatar = ?2\n" + "where id = ?1", nativeQuery = true)
    void updateAvatar(Integer userId, String image);

    @Transactional
    @Modifying
    @Query(value = "update user\n" + "set background = ?2\n" + "where id = ?1", nativeQuery = true)
    void updateBackground(Integer userId, String background);

    @Query(value = "select * from user\n" + " where id = ?1", nativeQuery = true)
    User findUserById(Integer id);
}
