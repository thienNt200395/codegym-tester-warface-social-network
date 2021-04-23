package c1020g1.social_network.controller;

import c1020g1.social_network.model.User;
import c1020g1.social_network.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class EditController {
    @Autowired
    EditService editService;


    @GetMapping("/user/1/edit")
    public User getUserInfo() {
        return editService.getUserInfoById(1);
    }

    @PostMapping("/1")
    public void save(@RequestBody User user){
        editService.save(user);
    }

}

