package tef;

import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import tef.dial.response.ResponseMessage;
import tef.impressao.ControleImpressao;
import tef.misc.NotRespondingException;

public class TesteTefCancelamento {

    public static void main(String[] args) {
        try {
            TefCancelamento tefCancelamento = TefCancelamento.getTefCancelamento();
            tefCancelamento.setDataEhoraTransacao(new DateTime());
            tefCancelamento.setIdentificacao("123");
            tefCancelamento.setNsu("123456");
            tefCancelamento.setRede("REDE");
            tefCancelamento.setTotalVenda(BigDecimal.valueOf(12));
            ResponseMessage response = tefCancelamento.cancelamento();
            boolean operacaoCancelada = TefInital.verificarCancelamentoOperacao(response);
            if(operacaoCancelada){
                JOptionPane.showMessageDialog(null, "Operação cancelada");
                return;
            }
            ControleImpressao controleImpressao = ControleImpressao.getControleImpressao();
            controleImpressao.setImpressora("nomeDaSuaImpressora");
            controleImpressao.setIsGaveta(false);
            controleImpressao.setModeloDaImpressora("modeloDaSuaImpressoa");
            controleImpressao.setQuantidadeDeVias(1);
            controleImpressao.imprimirComprovanteCancelamento(response.getVoucherImage());
        } catch (IOException | NotRespondingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

}
