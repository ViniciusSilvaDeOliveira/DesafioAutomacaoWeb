package configuracoesProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfiguracoesProperties {
    private final Properties url = new Properties();
    private final Properties drivers = new Properties();

    public ConfiguracoesProperties() throws IOException {
        url.load(Files.newInputStream(Paths.get("DesafioAutomacaoWebComReports/src/test/resources/properties/urlPagina.properties")));
        drivers.load(Files.newInputStream(Paths.get("DesafioAutomacaoWebComReports/src/test/resources/properties/urlPagina.properties")));
    }

    public String urlSite(){
        return url.getProperty("url");
    }

    public String drivers(){
        return drivers.getProperty("driver");
    }
}
