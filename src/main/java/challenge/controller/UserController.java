package challenge.controller;

import challenge.dto.UserDTO;
import challenge.exception.InternalServerException;
import challenge.model.User;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/secure/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserDTO> getUsers(Principal principal) {
        List<UserDTO> ret;
        try {
            ret = new ArrayList<>();
            for (User user : userService.getUsers(requestor(principal))) {
                ret.add(new UserDTO(user));
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
