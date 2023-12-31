package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runner.Runner;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\tivin\\Desktop\\Estudos\\DesafioAutomacaoWebComReports\\src\\test\\resources\\features\\registerLogin.feature",
        plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        glue = "steps",
        tags = "@CT004"
)
public class RunnerATestRegister {
    @BeforeClass
    public static void beforeClass(){
        Runner.ONE_EXECUTION = true;
    }
}
