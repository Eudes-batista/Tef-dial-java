package tef;

import javax.swing.JOptionPane;
import tef.dial.response.ResponseMessage;

public class TesteTefAdministrador {
    public static void main(String[] args) {
        
        try {
            TefAdministrador tefAdministrador = TefAdministrador.getTefAdministrador();
            ResponseMessage response = tefAdministrador.iniciarTefAdministrador();
            boolean operacaoCancelada = TefInital.verificarCancelamentoOperacao(response);
            if(operacaoCancelada){
                JOptionPane.showMessageDialog(null, "Operação cancelada");
                return;
            }
            tefAdministrador.realizarImpressao(response);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
}
