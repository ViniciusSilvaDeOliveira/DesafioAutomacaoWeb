package configuracoesProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfiguracoesProperties {
    private final Properties properties = new Properties();

    public ConfiguracoesProperties() throws IOException {
        properties.load(Files.newInputStream(Paths.get("C:\\Users\\tivin\\Desktop\\Estudos\\DesafioAutomacaoWebComReports\\src\\test\\resources\\properties\\urlPagina.properties")));
    }

    public String urlSite(){
        return properties.getProperty("url");
    }

    public String drivers(){
        return properties.getProperty("driver");
    }
}
