package c1020g1.social_network.repository;

import c1020g1.social_network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Modifying
    @Query(value = "insert into post (post_content, post_status, post_published, user_id) values (:postContent, " +
            ":postStatus, :postPublished, :userId)", nativeQuery = true)
    void createPost(@Param("postContent") String postContent, @Param("postStatus") String postStatus, @Param("postPublished") Timestamp postPublished,
                    @Param("userId") Integer userId);

    @Modifying
    @Query(value = "insert into post (post_content, post_status, post_published, user_id, group_id) values (:postContent, " +
            ":postStatus, :postPublished, :userId, :groupId)", nativeQuery = true)
    void createPostInGroup(@Param("postContent") String postContent, @Param("postStatus") String postStatus, @Param("postPublished") Timestamp postPublished,
                           @Param("userId") Integer userId, @Param("groupId") Integer groupId);

    @Modifying
    @Query(value = "update post set post_content = ?1, post_status = ?2 where post_id = ?3", nativeQuery = true)
    void editPost(String postContent, String postStatus, Integer postId);

    @Query(value = "SELECT * FROM post WHERE post_id = ?1", nativeQuery = true)
    Post findPostById(Integer postId);
}
