import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetupWebDriver {

    WebDriver driver;
    public void SetupWebDriver(){
        if(driver==null) {
            System.setProperty("webdriver.chrome.driver", "/Users/nayaysharma/Selenium/chromedriver");
            driver = new ChromeDriver();
        }
    }

}
