package br.com.saiphpdv.impressao.gaveta;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ElginEpsonGaveta implements GavetaPadrao{

    private static ElginEpsonGaveta elginEpsonGaveta = null;

    private ElginEpsonGaveta() {
    }

    
    @Override
    public void abrirGaveta() throws FileNotFoundException, IOException {
        this.realizarImpressao(new char[]{27, 112, 0, 60, 120});// ou new char[]{27, 112, 0, 60, 120}
    }

    public static ElginEpsonGaveta getElginGaveta() {
        if (elginEpsonGaveta == null) {
            elginEpsonGaveta = new ElginEpsonGaveta();
        }
        return elginEpsonGaveta;
    }

}
