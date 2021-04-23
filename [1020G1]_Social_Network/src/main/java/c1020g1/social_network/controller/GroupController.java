package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;
import c1020g1.social_network.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupRequestService groupRequestService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupUserService groupUserService;
    @Autowired
    private WarningService warningService;

    @GetMapping("/request/list/group/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByGroup(@PathVariable int id, @RequestParam(required = false) String key,
                                                                    @PageableDefault(size = 11) Pageable pageable) {
        if (groupService.findById(id) == null) {
            return new ResponseEntity<Page<GroupRequest>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        if (key == null) {
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByUser(@PathVariable int id, Pageable pageable) {
        User user = userService.findById(id);
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByUser(user, pageable), HttpStatus.OK);
    }

    @GetMapping("/group/get/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable int id){
        return new ResponseEntity<Group>(groupService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return new ResponseEntity<User>(userService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        if (groupRequestService.findById(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/request/save")
    public ResponseEntity<Void> saveRequest(@RequestBody GroupRequest groupRequest) {
        if (groupRequest.getGroup() == null || groupRequest.getUser() == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (groupService.findById(groupRequest.getGroup().getGroupId()) == null
                || userService.findById(groupRequest.getUser().getUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = groupUserService.findExist(groupRequest.getGroup().getGroupId(), groupRequest.getUser().getUserId());
        if (groupRequestService.findExist(groupRequest) != null || groupUser != null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.save(groupRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/request/accept/{id}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id) {
        GroupRequest groupRequest = groupRequestService.findById(id);
        if (groupRequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(groupRequest.getGroup());
        groupUser.setUser(groupRequest.getUser());
        groupUserService.save(groupUser);
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/member/list/{id}")
    public ResponseEntity<Page<GroupUser>> memberList(@RequestParam(required = false) String key, @PathVariable int id,
                                                      @PageableDefault(size = 11) Pageable pageable) {
        if (groupService.findById(id) == null) {
            return new ResponseEntity<Page<GroupUser>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        if (key == null) {
            key = "";
        }
        Page<GroupUser> all = groupUserService.findAllByGroupAndUsernameContainingOrderByUsername(id, key, pageable);
        return new ResponseEntity<Page<GroupUser>>(all, HttpStatus.OK);
    }

    @PostMapping("/member/warning")
    public ResponseEntity<Void> warning(@RequestBody GroupWarning groupWarning) {
        if (groupUserService.findById(groupWarning.getGroupUser().getGroupUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        warningService.save(groupWarning);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<GroupUser> member(@PathVariable int id) {
        GroupUser groupUser = groupUserService.findById(id);
        if (groupUser == null) {
            return new ResponseEntity<GroupUser>(groupUser, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
    }

    @GetMapping("/member/warning/{id}")
    public ResponseEntity<Page<GroupWarning>> warningList(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable) {
        if (groupUserService.findById(id) == null) {
            return new ResponseEntity<Page<GroupWarning>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        GroupUser groupUser = groupUserService.findById(id);
        return new ResponseEntity<Page<GroupWarning>>(warningService.findAllByGroupUserOrderByWarningDateDesc(groupUser, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (groupUserService.findById(id) != null) {
            warningService.deleteByGroupUserId(id);
            groupUserService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/request/invite/list/{id}")
    public ResponseEntity<List<User>> friendsOfFriendsInviteList(@PathVariable int id) {
        if (groupService.findById(id) == null){
            return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userService.inviteFriendsOfFriendsList(id), HttpStatus.OK);
    }

    @GetMapping("/request/invite/friends/{id}")
    public ResponseEntity<List<User>> friendsInviteList(@PathVariable int id, @RequestParam int userId) {
        if (groupService.findById(id) == null || userService.findById(userId) == null){
            return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        List<User> list = userService.inviteFriendList(id, userId);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
}
