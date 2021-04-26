package c1020g1.social_network.controller;

import c1020g1.social_network.model.Post;

import c1020g1.social_network.model.PostDTO;
import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.post.PostService;
import c1020g1.social_network.service.post_image.PostImageService;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostImageService postImageService;

//    @GetMapping("/wall/{id}")
//    public ResponseEntity<List<Post>> findAllPostInWall(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostInWall(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/group/{id}")
//    public ResponseEntity<List<Post>> findAllPostInGroupUser(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostInGroupUser(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/friend/{id}")
//    public ResponseEntity<List<Post>> findAllPostOfFriendUser(@PathVariable("id") Integer userId){
//        return  new ResponseEntity<>(postService.getAllPostOfFriendUser(userId), HttpStatus.OK);
//    }

    @GetMapping("/newsfeed/{userId}")
    public ResponseEntity<List<Post>> findAllPostInNewsFeed(@PathVariable("userId") Integer userId) {
        User userFromDb = userService.getUserById(userId);

        if (userFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Post> result = postService.getAllPostInNewsFeed(userId);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/image/{postId}")
    public ResponseEntity<List<PostImage>> findAllImageByPostId(@PathVariable("postId") Integer postId) {
        Post postFromDb = postService.getPostById(postId);

        if (postFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<PostImage> listPostImage = postService.getAllImageByPostId(postId);

        if (listPostImage.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listPostImage, HttpStatus.OK);
    }

    /**
     * @author SonPH
     * create post
     */
    @PostMapping("")
    public ResponseEntity<Void> createPost(@Validated @RequestBody PostDTO postDTO, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        postDTO.getPost().setPostPublished(new Timestamp(System.currentTimeMillis()));
        postDTO.getPost().setPostContent(postService.encodeStringUrl(postDTO.getPost().getPostContent()));
        postService.createPost(postDTO.getPost());
        Post postTemp = postService.getRecentPostByUserId(postDTO.getPost().getUser().getUserId());
        System.out.println(postDTO.getPostImages().length);
        for (String image : postDTO.getPostImages()) {
            postImageService.createPostImage(postTemp.getPostId(), image);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{postId}").buildAndExpand(postTemp).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * @author SonPH
     * edit post
     */
    @PutMapping("/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable("postId") Integer postId, @Validated @RequestBody Post post, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post1 = postService.getPostById(postId);
        if (post1 == null) {
            System.out.println("Post with id " + postId + " not found!");
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        post1.setPostContent(postService.encodeStringUrl(post.getPostContent()));
        post1.setPostStatus(post.getPostStatus());

        postService.editPost(post1);
        return new ResponseEntity<Post>(post1, HttpStatus.OK);
    }

    /**
     * @author SonPH
     * get post by postId
     */
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setPostContent(postService.decodeStringUrl(post.getPostContent()));
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}

