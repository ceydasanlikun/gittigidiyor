package Pages;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
        public WebDriver driver;
        //public static String loginUrl = "https://www.gittigidiyor.com/";

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Testinium\\IdeaProjects\\gittigidiyor\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get("https://www.gittigidiyor.com/");
        }

        @Test
        public void testing() throws InterruptedException {
            HomePAGE homepage = new HomePAGE(driver);
            homepage.login();

            SearchPAGE searchpage = new SearchPAGE(driver);
            searchpage.SearchProduct();
            searchpage.SearchPage();
            searchpage.SelectProduct();

            BasketPAGE basketpage = new BasketPAGE(driver);
            basketpage.AddBasketandCompare();
            basketpage.ChangeAmount();
            basketpage.DeleteProducts();


        }


        @After
        public void tearDown(){
            driver.quit();
        }
}


