import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddGroup {
        public WebDriver driver;

        @Test
        @Parameters({"user","password","grupo"})
        public void ejecucionTest(String user, String password, String grupo){
            accionLogIn(user, password);
            searchGroupAndAdd(grupo);
            cancelAddGroup();
        }


        public void accionLogIn(String user, String password) {
            WebElement userinput=driver.findElement(By.id("email"));
            userinput.sendKeys(user);
            WebElement passinput=driver.findElement(By.id("pass"));
            passinput.sendKeys(password);
            WebElement searchButton = driver.findElement(By.xpath("//input[@data-testid='royal_login_button']"));
            searchButton.click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }


    public void searchGroupAndAdd(String grupo) {
        WebElement searchTextField = driver.findElement(By.name("q"));
        searchTextField.sendKeys(grupo);
        WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='facebar_search_button']"));
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement groupButton = driver.findElement(By.xpath("//*[@data-edge='keywords_groups']"));
        groupButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement clickgrupo=driver.findElement(By.xpath("//*[text()='EL LENGUAJE DEL UNIVERSO']"));
        clickgrupo.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement unirme = driver.findElement(By.xpath("//*[@aria-label='Unirte al grupo EL LENGUAJE DEL UNIVERSO']"));
        unirme.click();
    }

    public void cancelAddGroup() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement cancelar = driver.findElement(By.id("u_0_12"));
        cancelar.click();
    }
        @BeforeMethod
        public void inicioNavegador(){
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("--start-maximized");
            opts.addArguments("--disable-notifications");
            driver= new ChromeDriver(opts);
            driver.get("http://facebook.com");
        }

        @AfterMethod
        public void cierroNavegador(){
            driver.close();
        }

}
