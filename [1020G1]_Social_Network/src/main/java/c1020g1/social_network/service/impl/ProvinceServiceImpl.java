package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.repository.DistrictRepository;
import c1020g1.social_network.repository.ProvinceRepository;
import c1020g1.social_network.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<Province> getProvinceList() {
        return provinceRepository.getProvinceList();
    }
}
