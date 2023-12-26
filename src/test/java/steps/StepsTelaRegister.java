package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import leituraTxt.LeituraTxtCadastro;
import org.junit.Assert;
import pageObject.TelaInicial;
import pageObject.TelaLogin;
import utils.Excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepsTelaRegister {

    private static List<Map<String, String>> list;
    private static List<Boolean> textoPresenteNaTela = new ArrayList<>();
    private static int linhas;
    TelaInicial telaInicial = new TelaInicial();
    TelaLogin telaLogin = new TelaLogin();
    LeituraTxtCadastro leituraTxtCadastro = new LeituraTxtCadastro();

    //Contexto
    @Dado("ter a massa de dados dos usuarios")
    public void terAMassaDeDadosDosUsuarios(DataTable dataTable) throws Exception {
        list = dataTable.asMaps(String.class, String.class);
        linhas = Excel.getCellDadosEmailsInvalidos(list.get(0).get("planilha"), list.get(0).get("aba"));
        LeituraTxtCadastro.leituraTxtRegister(linhas);
    }

    @Quando("preencho as informacoes de cadastro e clico em register")
    public void preenchoAsInformacoesDeCadastroEClicoEmRegister() throws InterruptedException {
        textoPresenteNaTela = leituraTxtCadastro.preencherCadastro();
    }

    @Entao("valido que o cadastro do usuario foi realizado apos ser direcionado para a tela de login")
    public void validoQueOCadastroDoUsuarioFoiRealizadoAposSerDirecionadoParaATelaDeLogin() {
        for (int i = 0; i < linhas; i++){
            Assert.assertEquals(textoPresenteNaTela.get(i), true);
        }
    }
}
