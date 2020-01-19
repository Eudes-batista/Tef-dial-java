package tef;

import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import tef.dial.response.ResponseMessage;
import tef.impressao.ControleImpressao;
import tef.misc.NotRespondingException;

public class TesteIniciaTransacao {

    public static void main(String[] args) {

        try {
            TefInicaTransacao tefInicaTranssacao = TefInicaTransacao.getTefInicaTranssacao();
            tefInicaTranssacao.setIdentificacao(123);
            tefInicaTranssacao.setVenda(123);
            tefInicaTranssacao.setValorDaVenda(BigDecimal.valueOf(12));
            ResponseMessage response = tefInicaTranssacao.inicarTransacaoTef();
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
            controleImpressao.imprimirComprovanteTef(response);
        } catch (IOException | NotRespondingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
