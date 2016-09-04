package challenge.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "Welcome to SpringBoot123!";
    }

}
