package c1020g1.social_network.controller;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.friend_request_service.FriendRequestService;
import c1020g1.social_network.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    UserService userService;

    @GetMapping("/friend_request/{id}")
    public ResponseEntity<List<FriendRequest>> getAllFriendRequest(@PathVariable Integer id){
        List<FriendRequest> friendRequestList = friendRequestService.findAllFriendRequest(id);
        if(friendRequestList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendRequestList , HttpStatus.OK);
    }

    @PostMapping("/friend_request")
    public ResponseEntity<Void> createFriendRequest(@RequestBody FriendRequest friendRequest){
        if(userService.findUserById(friendRequest.getSendUser().getUserId()) == null ||
             userService.findUserById(friendRequest.getReceiveUser().getUserId()) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(friendRequestService.saveFriendRequest(friendRequest).equals("NG")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/friend_request/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Integer id){
        if(friendRequestService.deleteFriendRequest(id).equals("NG")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
