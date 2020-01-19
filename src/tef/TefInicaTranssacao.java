package tef;

import tef.config.ArquivoConfiguracao;
import tef.dial.TefDial;
import tef.dial.dispatch.CNF;
import tef.dial.dispatch.CRT;
import tef.dial.response.ResponseMessage;
import tef.dial.response.StatusResponse;
import tef.misc.NotRespondingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public class TefInicaTranssacao {

    private int identificacao;
    private int venda;
    private BigDecimal valorDaVenda;

    private static TefInicaTranssacao tefInicaTranssacao = null;

    private TefInicaTranssacao() {
    }

    public static TefInicaTranssacao getTefInicaTranssacao() {
        if (tefInicaTranssacao == null) {
            tefInicaTranssacao = new TefInicaTranssacao();
        }
        return tefInicaTranssacao;
    }

    public ResponseMessage inicarTransacaoTef() throws IOException, NotRespondingException {
        TefDial instance = TefInital.getTefDial(ArquivoConfiguracao.getPastas().getCaminhoRequisicao(),ArquivoConfiguracao.getPastas().getCaminhoResposta());
        CRT crt = new CRT();
        crt.setCurrency(Currency.getInstance("BRL"));
        crt.setTotalValue(valorDaVenda);
        crt.setFiscalDocumentNumberLinked(venda);
        crt.setIdentifier(identificacao);

        ResponseMessage resp = instance.crt(crt);
        if (resp.isTransactionOk()) {
            CNF cnf = new CNF();
            cnf.setIdentifier(resp.getIdentifier());
            cnf.setControlCode(resp.getControlCode());
            cnf.setNetwork(resp.getNetwork());
            cnf.setNsu(resp.getNsu());
            cnf.setFiscalDocumentNumberLinked(resp.getFiscalDocumentNumberLinked());
            StatusResponse sts = instance.cnf(cnf);
        }
        return resp;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public void setValorDaVenda(BigDecimal valorDaVenda) {
        this.valorDaVenda = valorDaVenda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

}
