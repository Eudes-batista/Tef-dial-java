package tef;

import tef.impressao.ControleImpressao;
import tef.config.ArquivoConfiguracao;
import tef.dial.TefDial;
import tef.dial.dispatch.CNF;
import tef.dial.response.ResponseMessage;
import tef.dial.response.StatusResponse;
import java.io.IOException;

public class TefAdministrador {
    
    private static TefAdministrador tefAdministrador;

    private TefAdministrador() {
    }

    public static TefAdministrador getTefAdministrador() {
        return tefAdministrador;
    }

    public ResponseMessage iniciarTefAdministrador() throws Exception {
        TefDial instance = TefInital.getTefDial(ArquivoConfiguracao.getPastas().getCaminhoRequisicao(),ArquivoConfiguracao.getPastas().getCaminhoResposta());
        ResponseMessage adm = instance.adm();
        if (adm.isTransactionOk()) {
            CNF cnf = new CNF();
            cnf.setIdentifier(adm.getIdentifier());
            cnf.setControlCode(adm.getControlCode());
            cnf.setNetwork(adm.getNetwork());
            cnf.setNsu(adm.getNsu());
            cnf.setFiscalDocumentNumberLinked(adm.getFiscalDocumentNumberLinked());
            StatusResponse sts = instance.cnf(cnf);
        }
        return  adm;
    }

    public void realizarImpressao(ResponseMessage response) throws IOException {
        String comprovante = response.getVoucherImage();
        ControleImpressao controleImpressao = ControleImpressao.getControleImpressao();
        controleImpressao.setImpressora("nomeDaSuaImpressora");
        controleImpressao.setIsGaveta(false);
        controleImpressao.setModeloDaImpressora("modeloDaSuaImpressoa");
        controleImpressao.setQuantidadeDeVias(1);
        if (comprovante != null && !comprovante.isEmpty()) {
            controleImpressao.imprimirComprovanteTefAdministrador(comprovante);
            return;
        }
        controleImpressao.imprimirComprovanteTef(response);
    }

}
