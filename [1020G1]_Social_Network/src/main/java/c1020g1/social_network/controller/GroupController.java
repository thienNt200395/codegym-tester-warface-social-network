package c1020g1.social_network.controller;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.GroupWarning;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                                    Pageable pageable) {
        if (key == null){
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByUser(@PathVariable int id, Pageable pageable) {
        User user = userService.findById(id);
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByUser(user, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        if (groupRequestService.findById(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/request/save")
    public ResponseEntity<Void> saveRequest(@RequestBody GroupRequest groupRequest) {
        if (groupRequest.getGroup() == null || groupRequest.getUser() == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if (groupService.findById(groupRequest.getGroup().getGroupId()) == null
                || userService.findById(groupRequest.getUser().getUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        GroupUser groupUser = groupUserService.findExist(groupRequest.getGroup().getGroupId(),groupRequest.getUser().getUserId());
        if (groupRequestService.findExist(groupRequest) != null || groupUser != null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        groupRequestService.save(groupRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/request/accept/{id}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id) {
        GroupRequest groupRequest = groupRequestService.findById(id);
        if (groupRequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(groupRequest.getGroup());
        groupUser.setUser(groupRequest.getUser());
        groupUserService.save(groupUser);
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/member/list")
    public ResponseEntity<Page<GroupUser>> memberList(@RequestParam(required = false) String key, @PageableDefault(size = 1) Pageable pageable) {
        if (key == null) {
            key = "";
        }
        Page<GroupUser> all = groupUserService.findAllByGroupAndUsernameContainingOrderByUsername(1, key, pageable);
        return new ResponseEntity<Page<GroupUser>>(all, HttpStatus.OK);
    }

    @PostMapping("/member/warning")
    public ResponseEntity<Void> warning(@RequestBody GroupWarning groupWarning){
        if (groupUserService.findById(groupWarning.getGroupUser().getGroupUserId()) == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        warningService.save(groupWarning);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if (groupUserService.findById(id) != null){
            warningService.deleteByGroupUserId(id);
            groupUserService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
