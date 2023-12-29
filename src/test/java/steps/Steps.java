package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import leituraTxt.LeituraTxtCadastro;
import org.junit.Assert;
import pageObject.TelaBuscaDeCarro;
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
    private static List<String> corPesquisada = new ArrayList<>();
    private static List<String> anoPesquisado = new ArrayList<>();
    private static int linhas;
    TelaInicial telaInicial = new TelaInicial();
    TelaLogin telaLogin = new TelaLogin();
    LeituraTxtCadastro leituraTxtCadastro = new LeituraTxtCadastro();
    TelaBuscaDeCarro telaBuscaDeCarro = new TelaBuscaDeCarro();

    //CT001
    @Dado("ter a massa de dados dos usuarios")
    public void terAMassaDeDadosDosUsuarios(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosCadastro(list.get(0).get("planilha"), list.get(0).get("aba"));
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

    //CT003
    @E("seleciono a opção Search")
    public void selecionoAOpçãoSearch() {
        telaBuscaDeCarro.clicarBtnSearch(Hook.driver);
    }

    @E("pesquiso por um carro")
    public void pesquisoPorUmCarro(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellCorCarro(list.get(0).get("planilha"), list.get(0).get("aba"));
        LeituraTxtCadastro.leituraTxtCor(linhas);
        corPesquisada = leituraTxtCadastro.preencherCorCarro(Hook.driver);
    }

    @Entao("valido que a busca trouxe as cores corretas")
    public void validoQueABuscaTrouxeAsCoresCorretas() {
        for (int i = 0; i < linhas; i++){
            Assert.assertEquals(corPesquisada.get(i), leituraTxtCadastro.corPesquisaExcel().get(i));
        }
    }

    //CT004
    @Quando("pesquiso por um carro pelo ano")
    public void pesquisoPorUmCarroPeloAno(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellAnoCarro(list.get(0).get("planilha"), list.get(0).get("aba"));
        LeituraTxtCadastro.leituraTxtAno(linhas);
        anoPesquisado = leituraTxtCadastro.preencherAnoCarro(Hook.driver);
    }

    @Entao("valido que a busca me trouxe apenas de acordo com o ano do carro")
    public void validoQueABuscaMeTrouxeApenasDeAcordoComOAnoDoCarro() {
        for (int i = 0; i < linhas; i++){
            Assert.assertEquals(anoPesquisado.get(i), leituraTxtCadastro.anoPesquisaExcel().get(i));
        }
    }
}
