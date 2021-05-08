package c1020g1.social_network.service.province;

import c1020g1.social_network.model.Province;

import c1020g1.social_network.repository.ProvinceRepository;
import c1020g1.social_network.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {


    @Autowired
    private ProvinceRepository provinceRepository;

    /**
     * author: PhucPT
     * method: return iterables of all province in database
     * @return
     */
    @Override
    public Iterable<Province> getAllProvince() {
        return provinceRepository.findAllProvince();
    }
}
