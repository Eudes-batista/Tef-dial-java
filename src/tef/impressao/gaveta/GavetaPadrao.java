package tef.impressao.gaveta;

import tef.impressao.ControleImpressao;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface GavetaPadrao {

    public void abrirGaveta() throws FileNotFoundException, IOException;

    default void realizarImpressao(char[] comandos) throws FileNotFoundException, IOException {
        ControleImpressao.getControleImpressao().imprimirComprovante(String.valueOf(comandos));
    }
}
