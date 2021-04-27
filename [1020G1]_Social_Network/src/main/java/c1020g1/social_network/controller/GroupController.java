package c1020g1.social_network.controller;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.Group;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.GroupRequestService;
import c1020g1.social_network.service.GroupService;
import c1020g1.social_network.service.GroupUserService;
import c1020g1.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import c1020g1.social_network.model.GroupWarning;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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
    private PostService postService;

    //show list group

    /**
     * Author: CuongNVM
     * Get all list group
     *
     * @return
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> listAllGroup() {
        try {
            List<Group> Groups = groupService.findAll();
            if (Groups.isEmpty()) {
                return new ResponseEntity<List<Group>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<Group>>(Groups, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //tim kiem group theo ten

    /**
     * Author: CuongNVM
     * Search group by name
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/group-search/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> listGroupByName(@PathVariable String name) {
        try {
            List<Group> Groups = groupService.findGroupByNameContaining(name);
            if (Groups.isEmpty()) {
                return new ResponseEntity<List<Group>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<Group>>(Groups, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //hien group detail

    /**
     * Author: CuongNVM
     * Get group detail
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> getGroup(@PathVariable("id") Integer id) {
        try {
            Group Group = groupService.findById(id);
            if (Group == null) {
                return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Group>(Group, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Xoa group

    /**
     * Author: CuongNVM
     * Delete group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Integer id) {
        try {
            Group Group = groupService.findById(id);
            if (Group == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            groupService.remove(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //list Member

    /**
     * Author: CuongNVM
     * Get list member group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-member/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<GroupUser>> listAllGroupMember(@PathVariable Integer id) {
        try {
            List<GroupUser> groupUsers = groupUserService.findAllGroupMember(id);
            if (groupUsers.isEmpty()) {
                return new ResponseEntity<List<GroupUser>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<GroupUser>>(groupUsers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Display page feed

    /**
     * Author: CuongNVM
     * get all post group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-list-post/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPostGroup(@PathVariable Integer id) {
        try {
            List<Post> posts = postService.findAllPostGroup(id);
            if (posts.isEmpty()) {
                return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //edit Group

    /**
     * Author: CuongNVM
     * edit profile group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Integer id, @RequestBody Group Group) {
        try {
            Group currentGroup = groupService.findById(id);

            if (currentGroup == null) {
                return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
            }

            currentGroup.setImageAvatarUrl(Group.getImageAvatarUrl());
            currentGroup.setImageBackground(Group.getImageBackground());
            currentGroup.setScope(Group.getScope());

            groupService.save(currentGroup);
            return new ResponseEntity<Group>(currentGroup, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/group-detail/{groupId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<GroupUser> findGroupUserByGroupAndUser(@PathVariable Integer groupId, @PathVariable Integer userId) {
        try {
            if (groupService.findById(groupId) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (userService.findById(userId) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (groupUserService.findExist(groupId, userId) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<GroupUser>(groupUserService.findExist(groupId, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private WarningService warningService;

    @GetMapping("/request/list/group/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByGroup(@PathVariable int id,
                                                                    @RequestParam(required = false) String key,
                                                                    Pageable pageable) {
        if (key == null) {
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByUser(@PathVariable Integer id, Pageable pageable) {
        User user = userService.findById(id);
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByUser(user, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id) {
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
            return new ResponseEntity<GroupUser>(groupUser, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
    }

    @GetMapping("/member/warning/{id}")
    public ResponseEntity<Page<GroupWarning>> warningList(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable) {
        if (groupUserService.findById(id) == null) {
            return new ResponseEntity<Page<GroupWarning>>(Page.empty(), HttpStatus.NO_CONTENT);
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
        return new ResponseEntity<List<User>>(userService.inviteFriendsOfFriendsList(id), HttpStatus.OK);
    }

    @GetMapping("/request/invite/friends/{id}")
    public ResponseEntity<List<User>> friendsInviteList(@PathVariable int id, @RequestParam int userId) {
        List<User> list = userService.inviteFriendList(id, userId);
        for (User user : list) {
            System.out.println(user.getUserName());
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    /**
     * Author: CuongNVM
     * get all data form table Group Request
     * @param userId
     * @return
     */
    @GetMapping("/group-request/{userId}")
    public ResponseEntity<List<GroupRequest>> findGroupRequestByUserId(@PathVariable Integer userId){
        try{
            List<GroupRequest> list = groupRequestService.findGroupRequestByUserId(userId);
            return new ResponseEntity<List<GroupRequest>>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
