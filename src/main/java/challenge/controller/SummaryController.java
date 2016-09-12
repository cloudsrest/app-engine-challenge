package challenge.controller;

import challenge.dao.RecognitionSummary;
import challenge.dto.Summary;
import challenge.service.RecognitionService;
import challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/secure/summary")
public class SummaryController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RecognitionService recognitionService;

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Summary getStats(Principal principal) {
        long totalUsers = userService.geTotalUsers();
        long totalRecognitions = recognitionService.getTotalRecognitions();
        List<RecognitionSummary> topRecognitionReceivers = recognitionService.getTopRecognitionReceivers();
        if (topRecognitionReceivers.size() > 3) {
            topRecognitionReceivers = topRecognitionReceivers.subList(0, 3);
        }

        List<RecognitionSummary> topRecognitionSenders = recognitionService.getTopRecognitionSenders();
        if (topRecognitionSenders.size() > 3) {
            topRecognitionSenders = topRecognitionSenders.subList(0, 3);
        }

        return new Summary(totalUsers, totalRecognitions, topRecognitionReceivers, topRecognitionSenders);
    }

}
