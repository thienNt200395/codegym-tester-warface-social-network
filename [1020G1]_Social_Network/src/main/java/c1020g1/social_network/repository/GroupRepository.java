package c1020g1.social_network.repository;

import c1020g1.social_network.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Query(value = "select * from `group_social` where group_id = :id",nativeQuery = true)
    Group findByGroupId(@Param("id") int id);

    void deleteGroupByGroupId(Integer id);
    @Query("select gr from Group gr where gr.groupName like %?1%")
    Page<Group> findGroupByGroupNameContaining(String name, Pageable pageable);
}