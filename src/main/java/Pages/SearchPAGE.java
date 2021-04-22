package Pages;

import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.Random;

public class SearchPAGE {
    WebDriver driver;

    public SearchPAGE(WebDriver driver) {
        this.driver = driver;
    }

    public void SearchProduct() {

        //Arama kısmı bulunur ve bu kısma bilgisyar yazılır.
        WebElement search = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
        search.sendKeys("bilgisayar");

        //Arama butonu bulunur ve tıklanır.
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[2]/form/div/div[2]/button"));
        searchButton.click();
    }

    public void SearchPage() throws InterruptedException {

        //Ürünlerin olduğu sayfada 2. sayfaya yönlendiren butonun olduğu kısmı bulana kadar sayfayı aşağı indirir.
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[2]/a")));
        Thread.sleep(2000);

        //2.sayfaya yönlendiren butonu bulur ve tıklar.
        WebElement pageButton2 = driver.findElement((By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[2]/a")));
        pageButton2.click();
        Thread.sleep(2000);

        //2.sayfa açılmış mı kontrol edilir
        Assert.assertEquals("2.sayfada değilsiniz!","https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2",driver.getCurrentUrl());

    }

    public void SelectProduct() throws InterruptedException {

        //Rastgele bir ürün seçmek için maksimum 48 değerine kadar random bir sayı üretilir. 48 olmasının sebebi bir sayfada maksimum 48 adet ürün bulunmasıdır.
        Random random = new Random();
        int randomsayi = random.nextInt(48);

        //random sayı'ncı ürüne gider.
        WebElement selectedproduct = driver.findElement(By.xpath("//*[@product-index=" + randomsayi + "]"));
        selectedproduct.click();
        Thread.sleep(4000);
    }

}
