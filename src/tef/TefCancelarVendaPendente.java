package tef;

import java.io.File;
import tef.config.ArquivoConfiguracao;
import tef.dial.TefDial;
import tef.dial.dispatch.NCN;
import tef.dial.response.StatusResponse;
import tef.misc.NotRespondingException;
import java.io.IOException;
import tef.dial.Constants;
import tef.dial.response.ResponseMessage;
import tef.dial.response.ResponseReader;

public class TefCancelarVendaPendente {

    private int identificador;
    private String codigoControle;
    private String nomeDaRede;
    private String nsu;

    private static TefCancelarVendaPendente tefCancelarPendente = null;

    public void cancelarFormaPagamentoPendenteConfirmacaoTef() throws IOException, NotRespondingException {
        File arquivTemporario = new File(ArquivoConfiguracao.getPastas().getCaminhoTefTemp() + "/" + Constants.FILE_NAME_REQUEST);
        if (arquivTemporario.exists()) {
            ResponseMessage responseMessage = ResponseReader.getResponseMessage(arquivTemporario);
            this.codigoControle = responseMessage.getControlCode();
            this.identificador = responseMessage.getIdentifier();
            this.nomeDaRede = responseMessage.getNetwork();
            this.nsu = String.valueOf(responseMessage.getNsu());
            this.cancelarFormaPagamentoPedente();
        }
    }

    public static TefCancelarVendaPendente getTefCancelarPendente() {
        if (tefCancelarPendente == null) {
            tefCancelarPendente = new TefCancelarVendaPendente();
        }
        return tefCancelarPendente;
    }

    private TefCancelarVendaPendente() {
    }

    private void cancelarFormaPagamentoPedente() throws IOException, NotRespondingException {
        TefDial instance = TefInital.getTefDial(ArquivoConfiguracao.getPastas().getCaminhoRequisicao(), ArquivoConfiguracao.getPastas().getCaminhoResposta());

        NCN ncn = new NCN();
        ncn.setIdentifier(this.identificador);
        ncn.setControlCode(this.codigoControle);
        ncn.setNetwork(this.nomeDaRede);
        ncn.setNsu(Long.parseLong(this.nsu));
        ncn.setTaxDocumentNumberLinked(this.identificador);
        StatusResponse sts = instance.ncn(ncn);
    }

}
