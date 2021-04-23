package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;

public interface PostService {
    Post findPotsById(Integer postId);

    void createPost(Post post);

    void editPost(Post post);
}
