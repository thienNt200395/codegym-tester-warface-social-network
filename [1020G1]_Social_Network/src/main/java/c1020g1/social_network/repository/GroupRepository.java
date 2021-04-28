package c1020g1.social_network.repository;

import c1020g1.social_network.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("select gr from Group gr")
    Page<Group> findAllGroup(Pageable pageable);

    @Query("select gr from Group gr where gr.groupId=?1")
    Group findGroupById(Integer id);

    void deleteGroupByGroupId(Integer id);

    @Modifying
    @Query(" UPDATE Group gr set gr.scope = ?3, gr.imageAvatarUrl= ?2," +
            " gr.imageBackground= ?1 where gr.groupId = ?4")
    void updateGroup(String imageBackground, String imageAvatarUrl, String scope ,Integer groupId);

    @Query("select gr from Group gr where gr.groupName like %?1%")
    Page<Group> findGroupByGroupNameContaining(String name, Pageable pageable);
}
