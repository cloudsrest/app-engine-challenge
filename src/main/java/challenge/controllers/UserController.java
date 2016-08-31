package challenge.controllers;

import challenge.dto.User;
import challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Component
@Path("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GET
    @Produces("application/json")
    public List<User> getUsers() {
        return userService.getUsers(new User());
    }

}
