package tef;

import java.io.IOException;
import javax.swing.JOptionPane;
import tef.misc.NotRespondingException;

public class TesteTefCancelarVendaPendente {
    public static void main(String[] args) {
        
        try {
            TefCancelarVendaPendente cancelarVendaPendente = TefCancelarVendaPendente.getTefCancelarPendente();
            cancelarVendaPendente.cancelarFormaPagamentoPendenteConfirmacaoTef();
        } catch (IOException | NotRespondingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
}
