package challenge.controller;

import challenge.dto.Health;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/public/health")
public class HealthController {

    public static final String STATUS = "Up and Running! CI/CD pipeline enabled.";

    @RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Health health() {
        return new Health(STATUS);
    }



}
