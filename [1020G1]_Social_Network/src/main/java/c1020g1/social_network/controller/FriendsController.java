package c1020g1.social_network.controller;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    // show list friend
   @RequestMapping(value = "/friend-list/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Friends>> getAllList(@PathVariable Integer id) {
       List<Friends> friendsList = friendsService.getAllFriendByUserId(id);
       if (friendsList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(friendsList,HttpStatus.OK);
   }

   //Delete friend
    @RequestMapping(value = "friend-delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Friends> deleteFriends(@PathVariable Integer id){
       Friends friends = friendsService.findFriendsById(id);
       if (friends == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       friendsService.deleteFriends(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    //show List Suggest Friend
    @RequestMapping(value = "friend-suggest/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> showListSuggest(@PathVariable Integer id){
        List<Object> friendSuggestList = friendsService.getAllSuggestFriend(id);
        if (friendSuggestList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friendSuggestList, HttpStatus.OK);
    }
}
