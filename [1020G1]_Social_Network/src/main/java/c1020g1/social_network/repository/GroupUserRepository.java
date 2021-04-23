package c1020g1.social_network.repository;

import c1020g1.social_network.model.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser,Integer> {
    @Query("select mem from GroupUser mem where mem.groupSocial.groupId=?1")
    List<GroupUser> findAllGroupMember(Integer id);
}
