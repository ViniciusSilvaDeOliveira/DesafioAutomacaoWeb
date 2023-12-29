package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaBuscaDeCarro {

    private static final By btnSearch = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/ul/li[2]/a");
    private static final By inputPesquisa = By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[2]/a[2]/label/input");
    private static final By btnViewDetails = By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[3]/div[1]/div/div[3]/button[1]");
    private static final By corCarro = By.xpath("/html/body/div[4]/div/div/div/div[2]/table/tbody/tr[5]/td[2]");
    private static final By btnOk = By.xpath("/html/body/div[4]/div/div/div/div[3]/button[3]");
    private static final By anoCarro = By.xpath("/html/body/div[4]/div/div/div/div[2]/table/tbody/tr[4]/td[2]");

    public void clicarBtnSearch(WebDriver driver){
        driver.findElement(btnSearch).click();
    }

    public void pesquisarInformacoesCarro(String cor, WebDriver driver){
        driver.findElement(inputPesquisa).clear();
        driver.findElement(inputPesquisa).sendKeys(cor);
    }

    public void clicarBtnViewDatails(WebDriver driver){
        driver.findElement(btnViewDetails).click();
    }

    public String verificarCorCarro(WebDriver driver){
        return driver.findElement(corCarro).getText().toLowerCase();
    }

    public String verificarAnoCarro(WebDriver driver){
        return driver.findElement(anoCarro).getText();
    }

    public void btnOk(WebDriver driver){
        driver.findElement(btnOk).click();
    }
}
