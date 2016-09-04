package challenge.controller;

import challenge.dto.UserDTO;
import challenge.model.User;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private User requester = null;

    //FIXME
    private User requestor() {
        if (requester == null) {
            requester = userService.getUsers(new User()).get(0);
        }
        return requester;
    }

    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserDTO> getUsers() {
        List<UserDTO> ret = new ArrayList<>();
        for (User model : userService.getUsers(requestor())) {
            ret.add(new UserDTO(model));
        }
        return ret;
    }

    @RequestMapping(value="{userId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO getUser(@PathVariable Long userId) {
        User user = userService.getUser(requestor(), userId);
        return new UserDTO(user);
    }

    @RequestMapping(value="/me", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO whoAmI() {
        return new UserDTO(userService.getUser(requestor(), requestor().getId()));
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
