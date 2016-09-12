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
  public static final String seleniumURL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
  //public static final String seleniumURL = "http://localhost:4444/";
  public static final String baseURL = "https://blissful-cell-141318.appspot.com/api/www/index.html";
  //public static final String baseURL = "http://localhost:8080/api/www/index.html";
  public static final Integer waitForLoad = 15;

  private WebDriver driver;

  @Before
  public void before() throws Exception {
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability("platform", "Windows XP");
    caps.setCapability("version", "43.0");

    driver = new RemoteWebDriver(new URL(seleniumURL), caps);
  }

  @Test
  public void testSuccessLogin() throws Exception {

    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */

    driver.get(baseURL);

    List<WebElement> usernameEls;
    usernameEls = waitAndFind(driver, By.xpath("//input[@id='username']"));
    assertNotEquals("Cannot find username text box", 0, usernameEls.size());
    WebElement username = usernameEls.get(0);

    List<WebElement> passwordEls = driver.findElements(By.xpath("//input[@id='password']"));
    assertNotEquals("Cannot find password text box", 0, passwordEls.size());
    WebElement password = passwordEls.get(0);

    username.sendKeys("nturner");
    password.sendKeys("pass");

    List<WebElement> submitEls = driver.findElements(By.xpath("//input[@type='submit']"));
    assertEquals("Cannot find submit input", 1, submitEls.size());
    submitEls.get(0).click();

    submitEls = waitAndFind(driver, By.xpath("/html/body/ion-app/div[2]/ion-nav/ion-page/div/button"));
    assertEquals("Cannot find Give Recognition input", 1, submitEls.size());
    String giveRecognition = submitEls.get(0).findElement(By.tagName("span")).getText();
    assertEquals("Give Kudos", giveRecognition);

  }

  @Test
  public void testGiveRecognition() throws Exception {

    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */

    driver.get(baseURL);

    waitForElement(driver, By.xpath("//input[@id='username']"));

    List<WebElement> usernameEls;
    usernameEls = waitAndFind(driver, By.xpath("//input[@id='username']"));
    assertNotEquals("Cannot find username text box", 0, usernameEls.size());
    WebElement username = usernameEls.get(0);

    List<WebElement> passwordEls = driver.findElements(By.xpath("//input[@id='password']"));
    assertNotEquals("Cannot find password text box", 0, passwordEls.size());
    WebElement password = passwordEls.get(0);

    username.sendKeys("nturner");
    password.sendKeys("pass");

    List<WebElement> submitEls = driver.findElements(By.xpath("//input[@type='submit']"));
    assertEquals("Cannot find submit input", 1, submitEls.size());
    submitEls.get(0).click();

    List<WebElement> webElementsList;

    // /html/body/ion-app/div[2]/ion-nav/ion-page/div/button
    webElementsList = waitAndFind(driver, By.xpath("/html/body/ion-app/div[2]/ion-nav/ion-page/div/button"));
    assertEquals("Cannot find Give Recognition input", 1, submitEls.size());
    String giveRecognition = webElementsList.get(0).findElement(By.tagName("span")).getText();
    assertEquals("Give Kudos", giveRecognition);
    webElementsList.get(0).click();

    // ----------

    // Click on "Select a colleague to give recognition to..." textbox
    //
    webElementsList = waitAndFind(driver, By.id("colleague-select"));
    assertNotEquals("Cannot find Select a Colleague input", 0, webElementsList.size());
    webElementsList.get(0).click();

    // Click on Colleague's name.
    // alert-tappable alert-radio alert-radio-button alert-radio-button-default
    WebElement webElement = webElementsList.get(0).findElement(By.tagName("option"));
    //webElementsList = driver.findElements(By.xpath("/html/body/ion-app/div[2]/ion-nav/ion-page/div/form/select/option[3]']"));
    webElement.click();

    // Select teamwork
    webElementsList = driver.findElements(By.id("team-work"));
    webElementsList.get(0).click();

    // Enter comment
    // select an award
    webElementsList = driver.findElements(By.id("recognition-comment"));
    webElementsList.get(0).sendKeys("Great Teamwork");

    // Submit
    webElementsList = driver.findElements(By.className("usa-form"));
    webElementsList.get(0).submit();



  }

  private List<WebElement> waitAndFind(WebDriver driver, By by) {
    waitForElement(driver, by, waitForLoad);
    return driver.findElements(by);
  }

  private void waitForElement(WebDriver driver, By by) {
    waitForElement(driver, by, waitForLoad);
  }

  private WebElement waitForElement(WebDriver driver, By by, Integer waitForLoad) {
    WebDriverWait wait = new WebDriverWait(driver, waitForLoad);

    WebElement el = wait
            .until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    by
                            )
            );
    return el;
  }

  @After
  public void after() {
    driver.quit();
  }
}