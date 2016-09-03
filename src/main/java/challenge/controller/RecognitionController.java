package challenge.controller;

import challenge.dto.RecognitionDTO;
import challenge.model.Recognition;
import challenge.model.User;
import challenge.service.RecognitionService;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/recognitions")
public class RecognitionController {

    @Autowired
    private RecognitionService recognitionService;

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
    @Path("/all")
    @Produces("application/json")
    public List<RecognitionDTO> getRecognitions() {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getAllRecognitions(requestor())) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @GET
    @Path("/mine")
    @Produces("application/json")
    public List<RecognitionDTO> getMyRecognitions() {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getMyRecognitions(requestor())) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public RecognitionDTO createRecognition(RecognitionDTO recognition) {
        Recognition saved = recognitionService.createRecognition(requestor(), recognition);
        return new RecognitionDTO(saved);
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
