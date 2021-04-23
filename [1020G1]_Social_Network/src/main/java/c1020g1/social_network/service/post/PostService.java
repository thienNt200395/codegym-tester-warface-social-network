package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import java.util.List;

public interface PostService {
    Post findPotsById(Integer postId);

    void createPost(Post post);

    void editPost(Post post);

//    List<Post> getAllPostInWall(Integer userId);
//
//    List<Post> getAllPostInGroupUser(Integer userId);
//
//    List<Post> getAllPostOfFriendUser(Integer userId);

    Post getPostById(Integer postId);

    List<Post> getAllPostInNewsFeed(Integer userId);

    List<PostImage> getAllImageByPostId(Integer postId);

}
