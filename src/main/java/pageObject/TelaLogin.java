package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TelaLogin {
    private static final By textoLogin = By.xpath("//h3[text()='Please sign in']");
    private static final By inputUsername = By.xpath("//*[@id='login-form']/fieldset/div[1]/input");
    private static final By inputPassword = By.xpath("//*[@id='login-form']/fieldset/div[2]/input");
    private static final By btnLogin = By.xpath("//*[@id='login-form']/fieldset/div[3]/button");
    private static final By textoLoginNome = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/ul/li[5]/a/span[1]");

    public String nomeLogin(WebDriver driver){
        WebElement texto = driver.findElement(textoLoginNome);
        return texto.getText();
    }
    public boolean textoPresenteNaTela(WebDriver driver){
        WebElement texto = driver.findElement(textoLogin);
        if (texto.isDisplayed()){
            return true;
        }

        return false;
    }

    public void preencherUsername(String username, WebDriver driver){
        driver.findElement(inputUsername).clear();
        driver.findElement(inputUsername).sendKeys(username);
    }

    public void preencherPassword(String password, WebDriver driver){
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void clicarBtnLogin(WebDriver driver){
        driver.findElement(btnLogin).click();
    }
}
