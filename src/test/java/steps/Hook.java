package steps;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.service.ExtentService;
import configuracoesProperties.ConfiguracoesProperties;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import runner.Runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static runner.Runner.SCREENSHOTS;

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
        //Extent Reports para gerar relatórios em HTML usando o Spark Report.
        // O Spark Report é uma extensão para o Extent Reports que permite gerar relatórios visuais aprimorados
        if (!scenario.isFailed()){
            try {
                tirarPrint("Passo Finalizado");

                for(String screenshotName: SCREENSHOTS.keySet()) {
                    //Anexar a captura de tela ao cenário (Scenario) no formato "image/png" com o nome "Step Screenshot"
                    scenario.attach(SCREENSHOTS.get(screenshotName), "image/png", screenshotName);
                }
            } catch (Exception e){
                //Imprimir a exceção em caso de erro durante a captura de tela
                e.printStackTrace();
            }
        }
        SCREENSHOTS = new HashMap<>();
    }

    @After
    public void afterCenario(){
        driver.quit();
    }
    public static void tirarPrint(String printStepName) throws IOException {
        Runner.SCREENSHOTS.put(printStepName,((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}
