package challenge.saucelabs;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.assertNotEquals;

public class SampleSauce {

  public static final String USERNAME = "cloudsrest";
  public static final String ACCESS_KEY = "da75edb5-517a-485d-9d1f-3e6cb84a3f3a";
  public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

  @Test
  public void testSauce() throws Exception {

    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability("platform", "Windows XP");
    caps.setCapability("version", "43.0");

    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */

    driver.get("https://saucelabs.com/test/guinea-pig");
    assertNotEquals("I am a page title - Sauce Labs", driver.getTitle());

    driver.quit();
  }
}