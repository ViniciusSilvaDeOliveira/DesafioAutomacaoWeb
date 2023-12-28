package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import leituraTxt.LeituraTxtCadastro;
import org.junit.Assert;
import pageObject.TelaInicial;
import pageObject.TelaLogin;
import utils.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Steps {

    private static List<Map<String, String>> list;
    private static List<Boolean> textoPresenteNaTela = new ArrayList<>();
    private static int linhas;
    TelaInicial telaInicial = new TelaInicial();
    TelaLogin telaLogin = new TelaLogin();
    LeituraTxtCadastro leituraTxtCadastro = new LeituraTxtCadastro();

    //CT001
    @Dado("ter a massa de dados dos usuarios")
    public void terAMassaDeDadosDosUsuarios(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosEmailsInvalidos(list.get(0).get("planilha"), list.get(0).get("aba"));
        LeituraTxtCadastro.leituraTxtRegister(linhas);
    }

    @Quando("preencho as informacoes de cadastro e clico em register")
    public void preenchoAsInformacoesDeCadastroEClicoEmRegister() throws InterruptedException, IOException {
        textoPresenteNaTela = leituraTxtCadastro.preencherCadastro();
    }

    @Entao("valido que o cadastro do usuario foi realizado apos ser direcionado para a tela de login")
    public void validoQueOCadastroDoUsuarioFoiRealizadoAposSerDirecionadoParaATelaDeLogin() {
        for (int i = 0; i < linhas; i++){
            Assert.assertEquals(textoPresenteNaTela.get(i), true);
        }
    }

    //CT002
    @E("clico em Guest")
    public void clicoEmGuest() {
        telaInicial.clicarBtnGuest(Hook.driver);
    }

    @E("seleciono a opção de login")
    public void selecionoAOpçãoDeLogin() {
        telaInicial.clicarBtnLogin(Hook.driver);
    }

    @Quando("preencho as informacoes")
    public void preenchoAsInformacoes() {
        leituraTxtCadastro.preencherLogin();
    }

    @E("realizo o login")
    public void realizoOLogin() {
        telaLogin.clicarBtnLogin(Hook.driver);
    }

    @Entao("valido que o login foi realizado com sucesso")
    public void validoQueOLoginFoiRealizadoComSucesso() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertEquals(telaLogin.nomeLogin(Hook.driver), leituraTxtCadastro.nomeLoginRealizado());
    }
}
