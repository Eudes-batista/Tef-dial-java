package tef;

import tef.impressao.ControleImpressao;
import tef.config.ArquivoConfiguracao;
import tef.dial.TefDial;
import tef.dial.dispatch.CNF;
import tef.dial.response.ResponseMessage;
import tef.dial.response.StatusResponse;
import java.io.IOException;

public class TefAdministrador {

    public static void iniciarTefAdministrador() throws Exception {
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
            realizarImpressao(adm);
        }
    }

    private static void realizarImpressao(ResponseMessage response) throws IOException {
        String comprovante = response.getVoucherImage();
        ControleImpressao controleImpressao = ControleImpressao.getControleImpressao();
        if (comprovante != null && !comprovante.isEmpty()) {
            controleImpressao.imprimirComprovanteTefAdministrador(comprovante);
            return;
        }
        controleImpressao.imprimirComprovanteTef(response);
    }

}
