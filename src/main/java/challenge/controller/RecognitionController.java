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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recognitions")
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

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RecognitionDTO> getRecognitions() {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getAllRecognitions(requestor())) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RecognitionDTO> getMyRecognitions() {
        List<RecognitionDTO> ret = new ArrayList<>();
        for (Recognition recognition: recognitionService.getMyRecognitions(requestor())) {
            ret.add(new RecognitionDTO(recognition));
        }
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody RecognitionDTO createRecognition(@RequestBody RecognitionDTO recognition) {
        Recognition saved = recognitionService.createRecognition(requestor(), recognition);
        return new RecognitionDTO(saved);
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
