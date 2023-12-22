package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaRegister {
    private static final By inputFirstname = By.xpath("//*[@id='registration-form']/fieldset/div[1]/input");
    private static final By inputLastname = By.xpath("//*[@id='registration-form']/fieldset/div[2]/input");
    private static final By inputUsername = By.xpath("//*[@id='registration-form']/fieldset/div[3]/input");
    private static final By inputPassword = By.xpath("//*[@id='registration-form']/fieldset/div[4]/input");
    private static final By btnRegister = By.xpath("//*[@id='registration-form']/fieldset/div[5]/button");

    private void preencherInputFirstname(String firstname, WebDriver driver){
        driver.findElement(inputFirstname).clear();
        driver.findElement(inputFirstname).sendKeys(firstname);
    }

    private void preencherInputLastname(String lastname, WebDriver driver){
        driver.findElement(inputLastname).clear();
        driver.findElement(inputLastname).sendKeys(lastname);
    }

    private void preencherInputUsername(String username, WebDriver driver){
        driver.findElement(inputUsername).clear();
        driver.findElement(inputUsername).sendKeys(username);
    }

    private void preencherInputPassword(String password, WebDriver driver){
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }

    private void clicarBtnRegister(WebDriver driver){
        driver.findElement(btnRegister).click();
    }
}
