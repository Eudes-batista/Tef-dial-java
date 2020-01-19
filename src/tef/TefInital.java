package tef;

import tef.dial.TefDial;
import java.io.File;
import tef.dial.response.ResponseMessage;

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
    
    public static boolean verificarCancelamentoOperacao(ResponseMessage resposta) {
        if (resposta == null) {
            return true;
        }
        String codigoDaOperacaoCancelada = "6";
        if (codigoDaOperacaoCancelada.equals(resposta.getErrorCode())) {
            return true;
        }
        return !resposta.isTransactionOk();
    }

}
