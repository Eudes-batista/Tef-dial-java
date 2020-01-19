package br.com.saiphpdv.impressao.gaveta;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BematechGaveta implements GavetaPadrao{

    private static BematechGaveta bematechGaveta = null;

    private BematechGaveta() {
    }

    
    public static BematechGaveta getBematechGaveta() {
        if(bematechGaveta == null){
            bematechGaveta = new BematechGaveta();
        }
        return bematechGaveta;
    }

    @Override
    public void abrirGaveta() throws FileNotFoundException, IOException {
        this.realizarImpressao(new char[]{27, 118, 140});
    }
    
}
