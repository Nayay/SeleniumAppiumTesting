import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class SearchVacation extends SetupWebDriver {
    static ExtentTest test;
    static ExtentReports report;
    @BeforeClass
    @Parameters({"URL"})
    public void setup(@Optional ("https://www.travelocity.ca/") String URL) {
        SetupWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
        test = report.startTest("Vacation Demo");
        if(driver.getTitle().equals("Travelocity | Cheap Hotels, Flights, Vacations & Travel Deals"))
        {
            test.log(LogStatus.PASS, "Navigated to the specified URL");
        } else
        {
            test.log(LogStatus.FAIL, "Test Failed");

        }
    }

    @Test
    public void searchVacations() throws InterruptedException, IOException {
        WebElement Vacations =  driver.findElement(By.xpath("//*[@id=\"tab-threePP-tab-hp\"]"));
        Vacations.click();
        if(driver.findElement(By.xpath("//*[@id=\"threePP-origin-hp-threepp\"]")).isDisplayed())
        {
            test.log(LogStatus.PASS, "Vacations Page is displayed");
        } else
        {
            test.log(LogStatus.FAIL, "Test Failed");
        }
        Thread.sleep(3000);
        Select dropdownFrom = new Select(driver.findElement(By.xpath("//*[@id=\"threePP-origin-hp-threepp\"]")));
        dropdownFrom.selectByValue("YYZ");
        test.log(LogStatus.PASS, "Toronto is entered in Leaving Destination");
        Thread.sleep(3000);
        WebElement Destination =  driver.findElement(By.xpath("//*[@id=\"tab-threePP-tab-hp\"]"));
        Destination.click();
        driver.findElement(By.xpath("//option[contains(text(),'Cancun')]")).click();
        test.log(LogStatus.PASS, "Cancun is entered in Destination");
        WebElement Date = driver.findElement(By.xpath("//*[@id=\"threePP-departing-date-hp-threepp\"]"));
        Date.clear();
        Date.sendKeys("31/01/2020");
        WebElement Duration =  driver.findElement(By.xpath("//select[@name='duration']//following::option[text()='3 days']"));
        Duration.click();
        WebElement AdultCount = driver.findElement(By.xpath("//select[@name='adultCount']//following-sibling::option[@value='3']"));
        AdultCount.click();
        WebElement Search = driver.findElement(By.xpath("//*[@id=\"gcw-threePP-form-hp-threepp\"]/div[6]/label/button"));
        Search.click();
        test.log(LogStatus.PASS,test.addScreenCapture(Utility.capture(driver))+ "Vacation Searched");
        if(driver.getTitle().equals("All Inclusive Holidays"))
        {
            test.log(LogStatus.PASS, "Search is displayed");
        } else
        {
            test.log(LogStatus.FAIL, "Test Failed");

        }

    }

    @AfterClass
    public void tearDown(){
        report.endTest(test);
        report.flush();
        driver.quit();

    }




}
