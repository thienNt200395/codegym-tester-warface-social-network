package c1020g1.social_network.repository;

import c1020g1.social_network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.group.groupId=?1")
    List<Post> findAllPostGroup(Integer id);
}
