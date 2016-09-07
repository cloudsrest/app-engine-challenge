package challenge.controller;

import challenge.dto.RecognitionDTO;
import challenge.model.Recognition;
import challenge.model.User;
import challenge.service.RecognitionService;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/secure/recognitions")
public class RecognitionController extends BaseController {

    @Autowired
    private RecognitionService recognitionService;

    @Autowired
    private UserService userService;

    private User requester = null;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RecognitionDTO> getRecognitions(Principal principal) {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getAllRecognitions(requestor(principal))) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RecognitionDTO> getMyRecognitions(Principal principal) {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getMyRecognitions(requestor(principal))) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody RecognitionDTO createRecognition(@RequestBody RecognitionDTO recognition, Principal principal) {
        Recognition saved = recognitionService.createRecognition(requestor(principal), recognition);
        return new RecognitionDTO(saved);
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
