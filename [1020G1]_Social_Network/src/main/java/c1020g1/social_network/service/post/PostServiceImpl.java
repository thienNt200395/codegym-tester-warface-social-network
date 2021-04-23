package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post findPotsById(Integer postId) {
        return postRepository.findPostById(postId);
    }

    @Override
    @Transactional
    public void createPost(Post post) {
        if (post.getGroupSocial() == null) {
            postRepository.createPost(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId());
        } else {
            postRepository.createPostInGroup(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroupSocial().getGroupId());
        }
    }

    @Override
    @Transactional
    public void editPost(Post post) {
        postRepository.editPost(post.getPostContent(), post.getPostStatus(), post.getPostId());
    }
}
