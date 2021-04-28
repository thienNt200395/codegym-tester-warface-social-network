package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Group;
import c1020g1.social_network.repository.GroupRepository;
import c1020g1.social_network.service.GroupService;
import c1020g1.social_network.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;


    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAllGroup(pageable);
    }

    @Override
    public void save(Group group) {
        groupRepository.updateGroup(group.getImageBackground(), group.getImageAvatarUrl(), group.getScope(), group.getGroupId());
    }


    @Override
    public Group findById(Integer id) {
        return groupRepository.findGroupById(id);
    }


    @Override
    public void remove(Integer id) {
        groupRepository.deleteGroupByGroupId(id);
    }

    @Override
    public Page<Group> findAllByGroupName(String key, Pageable pageable) {
        return groupRepository.findGroupByGroupNameContaining(key, pageable);
    }
}
