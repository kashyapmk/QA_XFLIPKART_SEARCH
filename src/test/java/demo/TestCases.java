package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     @Test
     public void testCase01(){
        
        Wrappers wrap = new Wrappers(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //navigate to www.flipkart.com
        driver.get("https://www.google.com/");
        wrap.NavigateToUrl("https://www.flipkart.com/");
        //search for washing machine
        wrap.searchAndEnter("Washing Machine");
        //set popularity
        wrap.setPopularity();
        //count items less than or equal to 4 star rating
        wrap.countItemsLessThan(4);
        
     }

     @Test
     public void testCase02(){

        Wrappers wrap = new Wrappers(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //navigate to www.flipkart.com
        driver.get("https://www.google.com/");
        wrap.NavigateToUrl("https://www.flipkart.com/");
        //search for iPhone
        wrap.searchAndEnter("iPhone");
        //print titles and discount % of all Items with discount Greater than 17%
        wrap.getTitlesAndDiscounts(17);

     }

     @Test
     public void testCase03(){

        Wrappers wrap = new Wrappers(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //navigate to www.flipkart.com
        driver.get("https://www.google.com/");
        wrap.NavigateToUrl("https://www.flipkart.com/");
        //search for Coffee Mug
        wrap.searchAndEnter("Coffee Mug");
        //set customer rating to 4 & above
        wrap.setCustomerRatings(4);
        //print the title and imageURL of 5 items with highest number of reviews
        wrap.getTitleAndImageURL(5);

     }


     
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }



    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}