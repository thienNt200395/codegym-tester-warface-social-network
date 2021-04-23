package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.repository.GroupUserRepository;
import c1020g1.social_network.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    private GroupUserRepository groupUserRepository;
    @Override
    public List<GroupUser> findAllGroupMember(Integer id) {
        return groupUserRepository.findAllGroupMember(id);
    }
}
