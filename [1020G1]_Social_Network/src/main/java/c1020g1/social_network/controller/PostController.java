package c1020g1.social_network.controller;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.post.PostService;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<List<Post>> findAllPostInNewsFeed(@PathVariable("userId") Integer userId){
        User userFromDb = userService.getUserById(userId);

        if(userFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Post> result = postService.getAllPostInNewsFeed(userId);

        if(result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/image/{postId}")
    public ResponseEntity<List<PostImage>> findAllImageByPostId(@PathVariable("postId") Integer postId){
        Post postFromDb = postService.getPostById(postId);

        if(postFromDb == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<PostImage> listPostImage = postService.getAllImageByPostId(postId);

        if(listPostImage.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(listPostImage, HttpStatus.OK);
    }
}
