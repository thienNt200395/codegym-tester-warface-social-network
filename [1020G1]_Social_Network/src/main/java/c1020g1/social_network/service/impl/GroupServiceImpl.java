package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Group;
import c1020g1.social_network.repository.GroupRepository;
import c1020g1.social_network.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group findById(int id) {
        return groupRepository.findByGroupId(id);
    }

    @Override
    public void remove(Integer id) {
        groupRepository.deleteGroupByGroupId(id);
    }

    @Override
    public Page<Group> findAllByGroupName(String key,Pageable pageable) {
        return groupRepository.findGroupByGroupNameContaining(key,pageable);
    }
}
