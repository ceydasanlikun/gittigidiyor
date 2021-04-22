package Pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePAGE {
    WebDriver driver;
    public HomePAGE (WebDriver driver){this.driver = driver;}

    public void login () throws InterruptedException {

        //Sayfada Giriş Yap kısmı görünür olana kadar bekler.
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]")));
        Thread.sleep(2000);

        //Giriş yapılacak kısım tanımlandı.
        WebElement element = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]"));
        Thread.sleep(2000);

        //Giriş yapılacak kısımın üzerine gelir ve Giriş yap butonu görünür.
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        Thread.sleep(2000);

        //Giriş Yap butonu bulunur ve tıklanır Giriş Yapma sayfasına gidilir.
        WebElement loginbutton = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a/span"));
        loginbutton.click();

        //Kullanıcı adı ve şifreler girilir.
        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait11.until(ExpectedConditions.visibilityOfElementLocated(By.id("L-UserNameField")));

        driver.findElement(By.id("L-UserNameField")).sendKeys("ceydasanlikun3@gmail.com");
        Thread.sleep(2000);

        driver.findElement(By.id("L-PasswordField")).sendKeys("2149922figen");
        Thread.sleep(2000);

        driver.findElement(By.id("gg-login-enter")).click();
        Thread.sleep(2000);
        //Başarılı bir şekilde giriş yapılınca anasayfaya yönlendiriliyor. Açılmasını beklediğimiz ve açılan sayfa ana sayfa olmalıdır. Bu ikisi kıyaslanarak login olunmuş mu kıyaslanır.
        Assert.assertEquals("Giriş Yapılmadı ya da Anasayfada değilsiniz!",driver.getCurrentUrl(),"https://www.gittigidiyor.com/");
    }

}
