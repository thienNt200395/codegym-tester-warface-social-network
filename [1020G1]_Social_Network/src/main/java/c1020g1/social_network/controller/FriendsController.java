package c1020g1.social_network.controller;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.Friends;
import c1020g1.social_network.service.friend_request_service.FriendRequestService;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    FriendRequestService friendRequestService;

    @PostMapping("/addfriend")
    public ResponseEntity<Void> addNewFriend(@RequestBody FriendRequest friendRequest){
        Friends friends1 = new Friends();
        friends1.setUser(friendRequest.getReceiveUser());
        friends1.setFriend(friendRequest.getSendUser());
        if (friendsService.addNewFriend(friends1).equals("NG")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Friends friends2 = new Friends();
        friends2.setUser(friendRequest.getSendUser());
        friends2.setFriend(friendRequest.getReceiveUser());
        if (friendsService.addNewFriend(friends2).equals("NG")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        friendRequestService.deleteFriendRequest(friendRequest.getFriendRequestId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
