package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import java.util.List;

public interface PostService {

    void createPost(Post post);

    void editPost(Post post);

    Post getPostById(Integer postId);

    List<Post> getAllPostInNewsFeed(Integer userId);

    List<PostImage> getAllImageByPostId(Integer postId);

}
