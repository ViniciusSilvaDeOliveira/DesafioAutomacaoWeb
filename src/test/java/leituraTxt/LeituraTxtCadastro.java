package leituraTxt;

import org.openqa.selenium.WebDriver;
import pageObject.TelaBuscaDeCarro;
import pageObject.TelaInicial;
import pageObject.TelaLogin;
import pageObject.TelaRegister;
import steps.Hook;
import utils.ArquivoTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraTxtCadastro {
    List<String> listaCor = new ArrayList<>();
    List<String> listaAno = new ArrayList<>();
    TelaRegister telaRegister = new TelaRegister();
    TelaLogin telaLogin = new TelaLogin();
    TelaInicial telaInicial = new TelaInicial();
    TelaBuscaDeCarro telaBuscaDeCarro = new TelaBuscaDeCarro();
    List<Boolean> textoPresenteNaTela = new ArrayList<>();
    private static List<String> firstname = new ArrayList<>();
    private static List<String> lastname = new ArrayList<>();
    private static List<String> username = new ArrayList<>();
    private static List<String> password = new ArrayList<>();
    private static List<String> cor = new ArrayList<>();
    private static List<String> ano = new ArrayList<>();

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

    public static void leituraTxtCor(int linhas){
        String nomeColuna;

        for (int i = 1; i <= linhas; i++){
            nomeColuna = ArquivoTxt.lerArquivo("COR_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                cor.add(nomeColuna);
            }
        }
    }

    public static void leituraTxtAno(int linhas){
        String nomeColuna;

        for (int i = 1; i <= linhas; i++){
            nomeColuna = ArquivoTxt.lerArquivo("ANO_" + i, "planilha-desafio.txt");
            if (!nomeColuna.isEmpty()){
                ano.add(nomeColuna);
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

    public List<String> preencherCorCarro(WebDriver driver) throws InterruptedException, IOException {
        for (int i = 0; i < cor.size(); i++){
            telaBuscaDeCarro.pesquisarInformacoesCarro(cor.get(i), driver);
            Thread.sleep(2000);
            Hook.tirarPrint("Carro pesquisado pela cor " + cor.get(i));
            telaBuscaDeCarro.clicarBtnViewDatails(Hook.driver);
            Thread.sleep(2000);
            listaCor.add(telaBuscaDeCarro.verificarCorCarro(Hook.driver));
            telaBuscaDeCarro.btnOk(Hook.driver);
        }

        return listaCor;
    }

    public List<String> preencherAnoCarro(WebDriver driver) throws InterruptedException, IOException {
        for (int i = 0; i < ano.size(); i++){
            telaBuscaDeCarro.pesquisarInformacoesCarro(ano.get(i), driver);
            Thread.sleep(2000);
            Hook.tirarPrint("Carro pesquisado pela ano " + ano.get(i));
            telaBuscaDeCarro.clicarBtnViewDatails(Hook.driver);
            Thread.sleep(2000);
            listaAno.add(telaBuscaDeCarro.verificarAnoCarro(Hook.driver));
            telaBuscaDeCarro.btnOk(Hook.driver);
        }

        return listaAno;
    }

    public List<String> corPesquisaExcel(){
        return cor;
    }

    public List<String> anoPesquisaExcel(){
        return ano;
    }
}
