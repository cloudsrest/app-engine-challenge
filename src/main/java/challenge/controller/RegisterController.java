package challenge.controller;

import challenge.dto.UserDTO;
import challenge.exception.InternalServerException;
import challenge.model.Team;
import challenge.model.User;
import challenge.service.TeamService;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/public/register")
public class RegisterController extends BaseController  {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO register(@RequestBody UserDTO userDto, Principal principal) {
        User saved;
        try {
            Team team = teamService.findAll().get(0);
            User user = new User(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), false, team);
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            String pass = encoder.encode(userDto.getPassword());
            user.setPassword(pass);
            saved = userService.createUser(requestor(principal), user);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
        return new UserDTO(saved);
    }

}
