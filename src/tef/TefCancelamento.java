package tef;

import tef.config.ArquivoConfiguracao;
import tef.dial.TefDial;
import tef.dial.dispatch.CNC;
import tef.dial.dispatch.CNF;
import tef.dial.response.ResponseMessage;
import tef.dial.response.StatusResponse;
import tef.misc.NotRespondingException;
import java.io.IOException;
import java.math.BigDecimal;
import org.joda.time.DateTime;

public class TefCancelamento {

    private String identificacao;
    private BigDecimal totalVenda;
    private String nsu;
    private String rede;
    private DateTime dataEhoraTransacao;

    private static TefCancelamento tefCancelamento = null;

    private TefCancelamento() {
    }

    public static TefCancelamento getTefCancelamento() {
        if (tefCancelamento == null) {
            tefCancelamento = new TefCancelamento();
        }
        return tefCancelamento;
    }

    public ResponseMessage cancelamento() throws IOException, NotRespondingException {

        TefDial instance = TefInital.getTefDial(ArquivoConfiguracao.getPastas().getCaminhoRequisicao(),ArquivoConfiguracao.getPastas().getCaminhoResposta());

        CNC cnc = new CNC();

        cnc.setIdentifier(Integer.parseInt(identificacao));
        cnc.setTotalValue(totalVenda);
        cnc.setNsu(Long.parseLong(nsu));
        cnc.setNetwork(rede);
        cnc.setTransactionTimestamp(dataEhoraTransacao);
        cnc.setTaxDocumentNumberLinked(Integer.parseInt(identificacao));

        ResponseMessage resposta = instance.cnc(cnc);

        if (resposta.isTransactionOk()) {
            CNF cnf = new CNF();
            cnf.setIdentifier(resposta.getIdentifier());
            cnf.setControlCode(resposta.getControlCode());
            cnf.setNetwork(resposta.getNetwork());
            cnf.setNsu(resposta.getNsu());
            cnf.setFiscalDocumentNumberLinked(resposta.getFiscalDocumentNumberLinked());
            StatusResponse sts = instance.cnf(cnf);
        }
        return resposta;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        this.totalVenda = totalVenda;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String rede) {
        this.rede = rede;
    }

    public DateTime getDataEhoraTransacao() {
        return dataEhoraTransacao;
    }

    public void setDataEhoraTransacao(DateTime dataEhoraTransacao) {
        this.dataEhoraTransacao = dataEhoraTransacao;
    }

    
    
}
