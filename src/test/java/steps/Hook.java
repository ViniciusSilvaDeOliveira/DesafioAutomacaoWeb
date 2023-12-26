package steps;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.service.ExtentService;
import configuracoesProperties.ConfiguracoesProperties;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

public class Hook {
    private final ConfiguracoesProperties configuracoesProperties;

    @Deprecated
    public static ChromeDriver driver = new ChromeDriver();

    public Hook() throws IOException {
        configuracoesProperties = new ConfiguracoesProperties();
    }

    @BeforeAll
    public static void before_all(){
        ExtentService.getInstance().getStats().setAnalysisStrategy(AnalysisStrategy.SUITE);
    }

    @Before
    public void beforeCenario(Scenario scenario){
        System.setProperty("webdriver.chrome.driver", configuracoesProperties.drivers());
        //driver = new ChromeDriver();
        System.out.println("@@@BN INFO: Inicialização realizada com sucesso");
        driver.get(configuracoesProperties.urlSite());
    }

    @AfterStep
    public void setupAfterReport(Scenario scenario){
        //Extent Reports para gerar relatórios em HTML usando o Spark Report. O Spark Report é uma extensão para o Extent Reports que permite gerar relatórios visuais aprimorados
        if (!scenario.isFailed()){
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Step Screenshot");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @After
    public void afterCenario(Scenario scenario){
        driver.quit();
    }
}
