package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;
import c1020g1.social_network.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupRequestRepository groupRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;
    @Autowired
    private WarningRepository warningRepository;

    @GetMapping("/request/list/group/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByGroup(@PathVariable int id, @RequestParam(required = false) String key,
                                                                    Pageable pageable) {
        if (key == null){
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestRepository.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByUser(@PathVariable int id, Pageable pageable) {
        User user = userRepository.findByUserId(id);
        return new ResponseEntity<Page<GroupRequest>>(groupRequestRepository.findAllByUser(user, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        if (groupRequestRepository.findById(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        groupRequestRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/request/save")
    public ResponseEntity<Void> saveRequest(@RequestBody GroupRequest groupRequest) {
        if (groupRequest.getGroup() == null || groupRequest.getUser() == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if (groupRepository.findByGroupId(groupRequest.getGroup().getGroupId()) == null
                || userRepository.findByUserId(groupRequest.getUser().getUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        groupRequestRepository.save(groupRequest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/request/accept/{id}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id) {
        GroupRequest groupRequest = groupRequestRepository.findById(id);
        if (groupRequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(groupRequest.getGroup());
        groupUser.setUser(groupRequest.getUser());
        groupUserRepository.save(groupUser);
        groupRequestRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/member/list")
    public ResponseEntity<Page<GroupUser>> memberList(@RequestParam(required = false) String key, @PageableDefault(size = 1) Pageable pageable) {
        if (key == null) {
            key = "";
        }
        Page<GroupUser> all = groupUserRepository.findAllByGroupAndUsernameContainingOrderByUsername(1, key, pageable);
        return new ResponseEntity<Page<GroupUser>>(all, HttpStatus.OK);
    }

    @PostMapping("/member/warning")
    public ResponseEntity<Void> warning(@RequestBody GroupWarning groupWarning){
        if (groupUserRepository.findById(groupWarning.getGroupUser().getGroupUserId()).orElse(null) == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        warningRepository.save(groupWarning);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if (groupUserRepository.findById(id).orElse(null) != null){
            warningRepository.deleteByGroupUserId(id);
            groupUserRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
