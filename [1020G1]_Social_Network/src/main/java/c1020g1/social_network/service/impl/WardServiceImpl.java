package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Ward;
import c1020g1.social_network.repository.WardRepository;
import c1020g1.social_network.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    /**
     * author: PhucPT
     * method: return iterables of wards by district id
     * @param districtId
     * @return
     */
    @Override
    public Iterable<Ward> getWardByDistrictId(int districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }
}
