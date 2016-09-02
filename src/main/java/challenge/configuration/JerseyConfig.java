package challenge.configuration;

import challenge.controller.HealthController;
import challenge.controller.MainController;
import challenge.controller.RecognitionController;
import challenge.controller.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HealthController.class);
        register(UserController.class);
        register(RecognitionController.class);
        register(MainController.class);

    }

}
