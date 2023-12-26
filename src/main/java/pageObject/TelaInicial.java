package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaInicial {
    private static final By btnGuest = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/ul/li[5]/a");
    private static final By btnRegister = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/ul/li[5]/ul/li[2]/a");
    private static final By btnLogin = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/ul/li[5]/ul/li[1]/a");

    public void clicarBtnGuest(WebDriver driver){
        driver.findElement(btnGuest).click();
    }

    public void clicarBtnRegister(WebDriver driver){
        driver.findElement(btnRegister).click();
    }

    public void clicarBtnLogin(WebDriver driver){
        driver.findElement(btnLogin).click();
    }
}
