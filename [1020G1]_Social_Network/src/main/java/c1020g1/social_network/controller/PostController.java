package c1020g1.social_network.controller;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.post.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @PostMapping("")
    public ResponseEntity<Void> createPost(@RequestBody Post post, UriComponentsBuilder ucBuilder) {
        post.setPostPublished(new Timestamp(System.currentTimeMillis()));
        System.out.println(post);
        postService.createPost(post);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{postId}").buildAndExpand(post.getPostId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable("postId") Integer postId, @RequestBody Post post) {
        Post post1 = postService.findPotsById(postId);
        if (post1 == null) {
            System.out.println("Post with id " + postId + " not found!");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        post1.setPostContent(post.getPostContent());
        post1.setPostStatus(post.getPostStatus());

        postService.editPost(post1);
        return new ResponseEntity<Post>(post1, HttpStatus.OK);
    }
}