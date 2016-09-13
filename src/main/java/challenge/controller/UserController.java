package challenge.controller;

import challenge.dao.RecognitionDao;
import challenge.dto.RecognitionDTO;
import challenge.dto.UserDTO;
import challenge.exception.InternalServerException;
import challenge.model.Recognition;
import challenge.model.Team;
import challenge.model.User;
import challenge.service.RecognitionService;
import challenge.service.TeamService;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/secure/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecognitionDao recognitionDao;

    @Autowired
    private RecognitionService recognitionService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserDTO> getUsers(Principal principal) {
        List<UserDTO> ret;
        try {
            ret = new ArrayList<>();
            User requestor = requestor(principal);
            Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
            for (User user : userService.getUsers(requestor)) {
                UserDTO userDTO = new UserDTO(user);
                List<Recognition> byFromToAndDate = recognitionDao.findByFromToAndDate(user.getId(), requestor.getId(), date);
                if (byFromToAndDate == null || byFromToAndDate.isEmpty()) {
                    userDTO.setCanGiveTo(true);
                } else {
                    userDTO.setCanGiveTo(false);
                }

                ret.add(userDTO);
            }
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
        return ret;
    }

    @RequestMapping(value="{userId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO getUser(@PathVariable Long userId, Principal principal) {
        User user;
        try {
            user = userService.getUser(requestor(principal), userId);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
        return new UserDTO(user);
    }

    @RequestMapping(value="/me", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO getMe(Principal principal) {
        User user;
        try {
            User requestor = requestor(principal);
            user = userService.getUser(requestor(principal), requestor.getId());
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
        return new UserDTO(user);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
