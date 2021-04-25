package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.repository.PostRepository;
import c1020g1.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<Post> findAllPostGroup(Integer id) {
        return postRepository.findAllPostGroup(id);
    }
}
