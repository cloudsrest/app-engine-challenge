package challenge.saucelabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SauceLabsTest {

  public static final String USERNAME = "cloudsrest";
  public static final String ACCESS_KEY = "da75edb5-517a-485d-9d1f-3e6cb84a3f3a";
  public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

  public static final Integer waitForLoad = 15;

  private WebDriver driver;

  @Before
  public void before() throws Exception {
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability("platform", "Windows XP");
    caps.setCapability("version", "43.0");

    driver = new RemoteWebDriver(new URL(URL), caps);
  }

  @Test
  public void testSuccessLogin() throws Exception {

    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */

    driver.get("https://blissful-cell-141318.appspot.com/api/www/index.html");

    waitForElement(driver, By.xpath("//ion-input[@name='username']"));

    List<WebElement> usernameEls;
    usernameEls = driver.findElements(By.xpath("//ion-input[@name='username']"));
    List<WebElement> passwordEls = driver.findElements(By.xpath("//ion-input[@name='password']"));
    assertNotEquals("Cannot find username text box", 0, usernameEls.size());
    assertNotEquals("Cannot find password text box", 0, passwordEls.size());
    WebElement username = usernameEls.get(0).findElement(By.name("username"));
    WebElement password = passwordEls.get(0).findElement(By.name("password"));
    username.sendKeys("nturner");
    password.sendKeys("pass");
    List<WebElement> submitEls = driver.findElements(By.xpath("//button[@class='button button-default']"));
    assertEquals("Cannot find submit input", 1, submitEls.size());
    submitEls.get(0).click();

    waitForElement(driver, By.xpath("//button[@class='bar-button bar-button-default bar-button-icon-right']"));

    submitEls = driver.findElements(By.xpath("//button[@class='bar-button bar-button-default bar-button-icon-right']"));
    assertEquals("Cannot find Give Recognition input", 1, submitEls.size());
    String giveRecognition = submitEls.get(0).findElement(By.tagName("span")).getText();
    assertEquals("Give Recognition", giveRecognition);

  }

  @Test
  public void testGiveRecognition() throws Exception {

    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */

    driver.get("https://blissful-cell-141318.appspot.com/api/www/index.html");

    waitForElement(driver, By.xpath("//ion-input[@name='username']"));

    List<WebElement> usernameEls;
    usernameEls = driver.findElements(By.xpath("//ion-input[@name='username']"));
    List<WebElement> passwordEls = driver.findElements(By.xpath("//ion-input[@name='password']"));
    assertNotEquals("Cannot find username text box", 0, usernameEls.size());
    assertNotEquals("Cannot find password text box", 0, passwordEls.size());
    WebElement username = usernameEls.get(0).findElement(By.name("username"));
    WebElement password = passwordEls.get(0).findElement(By.name("password"));
    username.sendKeys("nturner");
    password.sendKeys("pass");
    List<WebElement> webElementsList = driver.findElements(By.xpath("//button[@class='button button-default']"));
    assertEquals("Cannot find submit input", 1, webElementsList.size());
    webElementsList.get(0).click();

    waitForElement(driver, By.xpath("//button[@class='bar-button bar-button-default bar-button-icon-right']"));

    webElementsList = driver.findElements(By.xpath("//button[@class='bar-button bar-button-default bar-button-icon-right']"));
    assertEquals("Cannot find Give Recognition input", 1, webElementsList.size());
    String giveRecognition = webElementsList.get(0).findElement(By.tagName("span")).getText();
    assertEquals("Give Recognition", giveRecognition);

    webElementsList.get(0).click();

    waitForElement(driver, By.xpath("//button[@class='item-cover item-cover-default']"));
    webElementsList = driver.findElements(By.xpath("//button[@class='bar-button bar-button-default bar-button-icon-right']"));
    assertEquals("Cannot find Select a Colleague input", 1, webElementsList.size());
  }

  private void waitForElement(WebDriver driver, By by) {
    waitForElement(driver, by, waitForLoad);
  }

  private void waitForElement(WebDriver driver, By by, Integer waitForLoad) {
    WebDriverWait wait = new WebDriverWait(driver, waitForLoad);

    WebElement el = wait
            .until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    by
                            )
            );
  }

  @After
  public void after() {
    driver.quit();
  }
}