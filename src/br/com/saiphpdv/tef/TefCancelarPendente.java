package br.com.saiphpdv.tef;

import br.com.saiphpdv.tef.config.ArquivoConfiguracao;
import br.com.saiphpdv.tef.dial.TefDial;
import br.com.saiphpdv.tef.dial.dispatch.NCN;
import br.com.saiphpdv.tef.dial.response.StatusResponse;
import br.com.saiphpdv.tef.misc.NotRespondingException;
import java.io.IOException;

public class TefCancelarPendente {

    private int identificador;
    private String codigoControle;
    private String nomeDaRede;
    private String nsu;

    private static TefCancelarPendente tefCancelarPendente = null;

    private TefCancelarPendente() {
    }

    public void cancelarFormaPagamentoPedente() throws IOException, NotRespondingException {
        TefDial instance = TefInital.getTefDial(ArquivoConfiguracao.getPastas().getCaminhoRequisicao(),ArquivoConfiguracao.getPastas().getCaminhoResposta());

        NCN ncn = new NCN();
        ncn.setIdentifier(this.identificador);
        ncn.setControlCode(this.codigoControle);
        ncn.setNetwork(this.nomeDaRede);
        ncn.setNsu(Long.parseLong(this.nsu));
        ncn.setTaxDocumentNumberLinked(this.identificador);
        StatusResponse sts = instance.ncn(ncn);
    }

    public static TefCancelarPendente getTefCancelarPendente() {
        if (tefCancelarPendente == null) {
            tefCancelarPendente = new TefCancelarPendente();
        }
        return tefCancelarPendente;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getCodigoControle() {
        return codigoControle;
    }

    public void setCodigoControle(String codigoControle) {
        this.codigoControle = codigoControle;
    }

    public String getNomeDaRede() {
        return nomeDaRede;
    }

    public void setNomeDaRede(String nomeDaRede) {
        this.nomeDaRede = nomeDaRede;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }
    
    
}
