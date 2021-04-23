package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.repository.GroupRequestRepository;
import c1020g1.social_network.service.GroupRequestService;
import c1020g1.social_network.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRequestServiceImpl implements GroupRequestService {
    @Autowired
    private GroupRequestRepository groupRequestRepository;
    @Autowired
    private GroupService groupService;

    @Override
    public String addGroupRequest(GroupRequest groupRequest) {
        if(groupService.findById(groupRequest.getGroupSocial().getGroupId()) == null){
            return "NG";
        }

         groupRequestRepository.save(groupRequest);
        return "OK";
    }
}
