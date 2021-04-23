package c1020g1.social_network.controller;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.Post;
import c1020g1.social_network.service.GroupRequestService;
import c1020g1.social_network.service.GroupService;
import c1020g1.social_network.service.GroupUserService;
import c1020g1.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupUserService groupUserService;
    @Autowired
    private PostService postService;  // getAllGroupMember():Observable<any>{
    //   return this.http.get((this.API + '/member'))
    // }
    @Autowired
    private GroupRequestService groupRequestService;

    //show list group
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSocial>> listAllGroup() {
        List<GroupSocial> groupSocials = groupService.findAll();
        if (groupSocials.isEmpty()) {
            return new ResponseEntity<List<GroupSocial>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupSocial>>(groupSocials, HttpStatus.OK);
        }
    }

    //tim kiem group theo ten
    @RequestMapping(value = "/group/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSocial>> listGroupByName(@PathVariable String name){
        List<GroupSocial> groupSocials = groupService.findGroupByNameContaining(name);
        if(groupSocials.isEmpty()){
            return new ResponseEntity<List<GroupSocial>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupSocial>>(groupSocials, HttpStatus.OK);
        }
    }

    //hien group detail
    @RequestMapping(value = "/group-detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupSocial> getGroup(@PathVariable("id") Integer id) {
        System.out.println("Fetching Group with id " + id);
        GroupSocial groupSocial = groupService.findById(id);
        if (groupSocial == null) {
            System.out.println("Group with id " + id + " not found");
            return new ResponseEntity<GroupSocial>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<GroupSocial>(groupSocial, HttpStatus.OK);
    }

    //Xoa group
    @RequestMapping(value = "/group/delete-{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GroupSocial> deleteGroup(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Group with id " + id);

        GroupSocial groupSocial = groupService.findById(id);
        if (groupSocial == null) {
            System.out.println("Unable to delete. Group with id " + id + " not found");
            return new ResponseEntity<GroupSocial>(HttpStatus.NOT_FOUND);
        }
        groupService.remove(id);
        return new ResponseEntity<GroupSocial>(HttpStatus.NO_CONTENT);
    }

    //list Member
    @RequestMapping(value = "/group-member/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<GroupUser>> listAllGroupMember(@PathVariable Integer id) {
        List<GroupUser> groupUsers = groupUserService.findAllGroupMember(id);
        if (groupUsers.isEmpty()) {
            return new ResponseEntity<List<GroupUser>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupUser>>(groupUsers, HttpStatus.OK);
        }
    }

    //Display page feed
    @RequestMapping(value = "/group-list-post/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPostGroup(@PathVariable Integer id) {
        List<Post> posts = postService.findAllPostGroup(id);
        if (posts.isEmpty()) {
            return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }
    }

    //edit Group
    @RequestMapping(value = "/group-edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GroupSocial> updateGroup(@PathVariable("id") Integer id, @RequestBody GroupSocial groupSocial) {
        System.out.println("Updating Group " + id);

        GroupSocial currentGroup = groupService.findById(id);

        if (currentGroup == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<GroupSocial>(HttpStatus.NOT_FOUND);
        }

        currentGroup.setImageAvatarUrl(groupSocial.getImageAvatarUrl());
        currentGroup.setImageBackground(groupSocial.getImageBackground());
        currentGroup.setScope(groupSocial.getScope());

        groupService.save(currentGroup);
        return new ResponseEntity<GroupSocial>(currentGroup, HttpStatus.OK);
    }

    //Gui request join group
    @RequestMapping(value = "/group-request", method = RequestMethod.POST)
    public ResponseEntity<GroupRequest> requestGroup(@RequestBody GroupRequest groupRequest){
        if(groupRequestService.addGroupRequest(groupRequest).equals("NG"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
