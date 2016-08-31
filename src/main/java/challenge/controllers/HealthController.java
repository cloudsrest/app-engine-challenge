package challenge.controllers;

import challenge.dto.Health;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/health")
public class HealthController {

    public static final String STATUS = "Jersey: Up and Running!";

    @GET
    @Produces("application/json")
    public Health health() {
        return new Health(STATUS);
    }



}
