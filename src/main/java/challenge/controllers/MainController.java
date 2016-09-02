package challenge.controllers;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Controller
@Path("/")
public class MainController {

    @GET
    @Produces("text/html")
    public String index() {
        return "Welcome to SpringBoot123!";
    }

}
