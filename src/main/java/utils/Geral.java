package utils;

import org.openqa.selenium.Keys;
import runner.Runner;

import java.text.Normalizer;

public class Geral {
    public static void AvancarPelosElementos(int quantidade) {
        for (int i = 0; i <= quantidade; i++) {
            Runner.ACTION.sendKeys(Keys.TAB).perform();
        }
    }
    public static void RetrocederPelosElementos(int quantidade) {
        for (int i = 0; i <= quantidade; i++) {
            Runner.ACTION.keyDown(Keys.LEFT_SHIFT).sendKeys(Keys.TAB).keyUp(Keys.LEFT_SHIFT).perform();
        }
    }
    public static String removerAcentosEspacos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll(" ", "_")
                ;
    }
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
