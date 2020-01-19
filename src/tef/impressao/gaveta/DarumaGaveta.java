package tef.impressao.gaveta;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DarumaGaveta implements GavetaPadrao {

    private static DarumaGaveta darumaGaveta = null;

    private DarumaGaveta() {
    }

    @Override
    public void abrirGaveta() throws FileNotFoundException, IOException {
        this.realizarImpressao(new char[]{27, 'p'}); // 27,112
    }

    public static DarumaGaveta getDarumaGaveta() {
        if (darumaGaveta == null) {
            darumaGaveta = new DarumaGaveta();
        }
        return darumaGaveta;
    }

}
