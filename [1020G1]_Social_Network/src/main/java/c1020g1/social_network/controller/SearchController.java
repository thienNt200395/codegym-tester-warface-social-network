package c1020g1.social_network.controller;

import c1020g1.social_network.model.User;
import c1020g1.social_network.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/searching/name-search/{name}")
    public List<User> doNameSearch(@PathVariable("name") String name) {
        return searchService.findAllUserByNameContain(name);
    }

    @GetMapping("/searching/advanced-search")
    public List<User> doAdvancedSearch(@RequestParam("name") String name, @RequestParam("birthday") Integer birthday,
                                       @RequestParam("gender") String gender, @RequestParam("province") String province,
                                       @RequestParam("district") String district, @RequestParam("ward") String ward,
                                       @RequestParam("occupation") String occupation,
                                       @RequestParam("favourites") List<String> favourites) {
        return searchService.advancedSearch(name, birthday, gender, province, district, ward, occupation, favourites);
    }

    @GetMapping("/searching/recommend")
    public List<User> doRecommendation(@RequestParam("id") Integer id) {
        User user = searchService.findById(id);
        List<String> list = searchService.getListFavourite(user.getUserId());
        return searchService.recommendation(user.getUserId(), user.getBirthday(),
                user.getWard().getDistrict().getProvince().getProvinceId(), list);
    }
}
