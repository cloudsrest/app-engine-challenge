package challenge.controller;

import challenge.dto.UserDTO;
import challenge.model.User;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/users")
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

    @GET
    @Produces("application/json")
    public List<UserDTO> getUsers() {
        List<UserDTO> ret = new ArrayList<>();
        for (User model : userService.getUsers(requestor())) {
            ret.add(new UserDTO(model));
        }
        return ret;
    }

    @GET
    @Path("{userId}")
    @Produces("application/json")
    public UserDTO getUser(@PathParam("userId") Long userId) {
        return new UserDTO(userService.getUser(requestor(), userId));
    }

    @GET
    @Path("/me")
    @Produces("application/json")
    public UserDTO whoAmI() {
        return new UserDTO(userService.getUser(requestor(), requestor().getId()));
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
