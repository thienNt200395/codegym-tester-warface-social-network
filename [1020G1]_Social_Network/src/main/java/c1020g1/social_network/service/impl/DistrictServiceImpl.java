package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.District;
import c1020g1.social_network.repository.DistrictRepository;
import c1020g1.social_network.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<District> getDistrictList() {
        return districtRepository.getDistrictList();
    }
}
