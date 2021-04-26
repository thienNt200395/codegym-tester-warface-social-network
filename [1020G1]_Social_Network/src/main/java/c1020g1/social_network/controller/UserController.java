package c1020g1.social_network.controller;

import c1020g1.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {
    /**
     * method: update status of user.
     * author: HanTH.
     */
    @Autowired
    private UserService userService;
    @PutMapping("/user/{idUser}/update/status/{idStatus}")
    public void updateStatus(@PathVariable("idUser") Integer idUser, @PathVariable("idStatus") Integer idStatus) {
        userService.updateStatus( idUser, idStatus );
    }

    /**
     * method: update avatar of user.
     * author: HanTH
     * @param idUser
     * @param image
     * @param imageName
     */
    @PutMapping("/user/{idUser}/update/avatar")
    public void updateAvatar(@PathVariable("idUser") Integer idUser, @RequestParam("image") String image,
                             @RequestParam("imageFile") String imageName) {
        userService.updateAvatar( idUser, image, imageName );
    }

    /**
     * method: update background of user.
     * author: HanTH.
     * @param idUser
     * @param image
     * @param imageName
     */
    @PutMapping("/user/{idUser}/update/background")
    public void updateBackground(@PathVariable("idUser") Integer idUser, @RequestParam("background") String image,
                                 @RequestParam("imageFile") String imageName) {
        userService.updateBackground( idUser, image, imageName );
    }
}
