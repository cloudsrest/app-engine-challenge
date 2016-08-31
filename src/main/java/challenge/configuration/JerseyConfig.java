package challenge.configuration;

import challenge.controllers.HealthController;
import challenge.controllers.RecognitionController;
import challenge.controllers.UserController;
import challenge.dto.Recognition;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HealthController.class);
        register(UserController.class);
        register(RecognitionController.class);
    }

}
