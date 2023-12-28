package leituraTxt;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageObject.TelaInicial;
import pageObject.TelaLogin;
import pageObject.TelaRegister;
import runner.Runner;
import steps.Hook;
import utils.ArquivoTxt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraTxtCadastro {
    TelaRegister telaRegister = new TelaRegister();
    TelaLogin telaLogin = new TelaLogin();
    TelaInicial telaInicial = new TelaInicial();
    List<Boolean> textoPresenteNaTela = new ArrayList<>();
    private static List<String> firstname = new ArrayList<>();
    private static List<String> lastname = new ArrayList<>();
    private static List<String> username = new ArrayList<>();
    private static List<String> password = new ArrayList<>();
    public static void leituraTxtRegister(int linhas){
        String nomeColuna;

        for (int i = 1; i <= linhas; i++){
            nomeColuna = ArquivoTxt.lerArquivo("FIRSTNAME_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                firstname.add(nomeColuna);
            }

            nomeColuna = ArquivoTxt.lerArquivo("LASTNAME_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                lastname.add(nomeColuna);
            }

            nomeColuna = ArquivoTxt.lerArquivo("USERNAME_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                username.add(nomeColuna);
            }

            nomeColuna = ArquivoTxt.lerArquivo("PASSWORD_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                password.add(nomeColuna);
            }
        }
    }

    public List<Boolean> preencherCadastro() throws InterruptedException, IOException {
        for (int i = 0; i < password.size(); i++){
            telaInicial.clicarBtnGuest(Hook.driver);
            telaInicial.clicarBtnRegister(Hook.driver);
            telaRegister.preencherInputFirstname(firstname.get(i), Hook.driver);
            telaRegister.preencherInputLastname(lastname.get(i), Hook.driver);
            telaRegister.preencherInputUsername(username.get(i), Hook.driver);
            telaRegister.preencherInputPassword(password.get(i), Hook.driver);
            Hook.tirarPrint("Usuário " + username.get(i) + " cadastrado");
            Thread.sleep(2000);
            telaRegister.clicarBtnRegister(Hook.driver);
            Thread.sleep(2000);
            textoPresenteNaTela.add(telaLogin.textoPresenteNaTela(Hook.driver));
        }

        return textoPresenteNaTela;
    }

    public void preencherLogin(){
        telaLogin.preencherUsername(username.get(0), Hook.driver);
        telaLogin.preencherPassword(password.get(0), Hook.driver);
    }

    public String nomeLoginRealizado(){
        return firstname.get(0) + " " + lastname.get(0);
    }
}
