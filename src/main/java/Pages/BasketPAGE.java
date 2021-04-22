package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasketPAGE {
    WebDriver driver;
    String loginUrl = "https://www.gittigidiyor.com/";

    public BasketPAGE(WebDriver driver) {
        this.driver = driver;
    }

    public void AddBasketandCompare() throws InterruptedException {

        //indirimli ürünlerin fiyatı sp-price-lowPrice'dır. Fakat indirim yoksa kod hata veriyor. Hata alındığı zaman yerine sp-price-highPrice yazılmalıdır.
        String productprice = driver.findElement(By.id("sp-price-lowPrice")).getText(); //Seçilen ürün fiyatı sepetteki fiyat ile karşılaştırmak için önceden kaydedilir.

        //Sepete ekle butonu bulunana kadar sayfa aşağı iner ve bulununca tıklar.
        WebElement addbasket = driver.findElement(By.id("add-to-basket"));
        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("arguments[0].scrollIntoView()", addbasket);
        addbasket.click();
        Thread.sleep(4000);

        // Sepetim PopUp'ını açar.
        driver.findElement(By.xpath("//*[@class='basket-container robot-header-iconContainer-cart']/a")).click();
        // Sepet fiyatı kaydedilir.
        String basketprice = driver.findElement(By.xpath("//*[@class='total-price']")).getText();

        //SEpet ve ürün fıyatı karşılaştırılır.
        Assert.assertEquals("eşit değil", productprice, basketprice);
    }

    public void ChangeAmount() throws InterruptedException {

        //Adet değiştirmek için amount'a tıklanır
        WebElement amount = driver.findElement(By.xpath("//*[@class='amount']"));
        amount.click();

        //2 adet seçilir.
        WebElement piece2 = driver.findElement(By.xpath("//*[@class='amount']/option[2]"));
        piece2.click();
        Thread.sleep(4000);

        //Kaç adet seçildiyse kaydedilir.
        String selectedpiece = driver.findElement(By.xpath("//*[@class='gg-d-16 detail-text']")).getText();

        //Boşluğa tıklanarak Amount kapatılır.
        driver.findElement(By.xpath("//*[@class='cart-summary-title']")).click();
        Thread.sleep(2000);

        //ürün fiyatı ve sepet fiyatı karşılaştırlır.
        Assert.assertEquals("Ürün Toplamı (2 Adet)", selectedpiece);
    }

    public void DeleteProducts () throws InterruptedException {

        WebElement delete = driver.findElement(By.xpath("//*[@class='gg-icon gg-icon-bin-medium']"));
        delete.click();

        driver.get(loginUrl);
        Actions action2 = new Actions(driver);
        action2.moveToElement(driver.findElement(By.xpath("//*[@class='sc-84am1q-0 sc-1r48nyr-0 gpYIaK']"))).build().perform();

        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1tdlrg-0 yf6n83-0 rpwBO ciohle-1 hyotRr']")));

        String productpiece = driver.findElement(By.xpath("//*[@class='sc-1tdlrg-0 yf6n83-0 rpwBO ciohle-1 hyotRr']")).getText();
        String product0 ="0 Ürün";
        Assert.assertEquals("Sepette ürün var",productpiece,product0);
    }
}
