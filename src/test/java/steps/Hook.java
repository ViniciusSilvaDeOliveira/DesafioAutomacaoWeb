package steps;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.service.ExtentService;
import configuracoesProperties.ConfiguracoesProperties;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hook {
    private final ConfiguracoesProperties configuracoesProperties;

    @Deprecated
    public static WebDriver driver;

    public Hook(ConfiguracoesProperties configuracoesProperties) {
        this.configuracoesProperties = configuracoesProperties;
    }

    @BeforeAll
    public static void before_all(){
        ExtentService.getInstance().getStats().setAnalysisStrategy(AnalysisStrategy.SUITE);
    }

    @Before
    public void beforeCenario(Scenario scenario){
        System.setProperty("webdriver.chrome.driver", configuracoesProperties.drivers());
        System.out.println("@@@BN INFO: Inicialização realizada com sucesso");
        driver.get(configuracoesProperties.urlSite());
    }

    @AfterStep
    public void setupAfterReport(Scenario scenario){
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
