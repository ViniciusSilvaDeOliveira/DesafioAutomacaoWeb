package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TelaLogin {
    private static final By textoLogin = By.xpath("//h3[text()='Please sign in']");

    public boolean textoPresenteNaTela(WebDriver driver){
        WebElement texto = driver.findElement(textoLogin);
        if (texto.isDisplayed()){
            return true;
        }

        return false;
    }
}
