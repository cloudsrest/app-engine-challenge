package challenge.controllers;

import challenge.dto.Recognition;
import challenge.dto.User;
import challenge.services.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/recognitions")
public class RecognitionController {

    public static final String STATUS_CREATE_REC_SUCCESS = "Recognition created successfully";

    @Autowired
    private RecognitionService recognitionService;

    @GET
    @Produces("application/json")
    public List<Recognition> getRecognitions() {
        return recognitionService.getRecognitions(new User());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response createRecognition(Recognition recognition) {
        recognitionService.createRecognition(new User(), recognition);
        return Response.status(Response.Status.CREATED).entity(STATUS_CREATE_REC_SUCCESS).type("text/plain").build();
    }

    @DELETE
    @Produces("application/json")
    public Response deleteAllRecognitions() {
        recognitionService.deleteAllRecognitions();
        return Response.status(Response.Status.CREATED).entity("All recognitions deleted").type("text/plain").build();
    }

}
