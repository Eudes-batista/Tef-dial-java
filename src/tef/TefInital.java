package tef;

import tef.dial.TefDial;
import java.io.File;

public class TefInital {
    private static TefInital tefInital = null;
    private static TefDial tefDial = null;

    public static TefDial getTefDial(String caminhoDoArquivoRequisicao,String caminhoDoArquivoResposta) {
        if(tefDial == null){
            tefDial = new TefDial();
            tefInital = new TefInital();
        }
        tefDial.setRequestDirectory(new File(caminhoDoArquivoRequisicao));
        tefDial.setResponseDirectory(new File(caminhoDoArquivoResposta));
        return tefDial;
    }

}
