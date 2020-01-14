package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.misc.NotRespondingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class IniciandoVenda {

    public static void main(String[] args) throws IOException, NotRespondingException {
        Scanner scanner = new Scanner(System.in);

        String condicao = "sim";

        IniciarTransacaoTef iniciarTransacaoTef = new IniciarTransacaoTef();
        while ("sim".equals(condicao)) {
            System.out.println("Deseja iniciar uma venda no tef: sim/nao");
            condicao = scanner.nextLine();
            if ("nao".equals(condicao)) {
                System.out.println("Finalizado venda do tef (ate mas ☺) ");
                break;
            }
            System.out.println("Informe a identificação da venda.");
            String idenficacao = scanner.nextLine();
            iniciarTransacaoTef.setIdentificacao(Integer.parseInt(idenficacao));
            iniciarTransacaoTef.setVenda(Integer.parseInt(idenficacao));
            System.out.println("Informe o valor da venda.");
            BigDecimal bigDecimal = new BigDecimal(scanner.nextLine());
            iniciarTransacaoTef.setValorDaVenda(bigDecimal);
            ResponseMessage resposta = iniciarTransacaoTef.inicarTransacaoTef();
            System.out.println("resposta = " + resposta.getStoreCode());
            System.out.println("Deseja cancelar a venda");
            String cancelarAVenda = scanner.nextLine();
            if ("sim".equals(cancelarAVenda)) {
                //CancelamentoVenda cancelamentoVenda = new CancelamentoVenda();
                //cancelamentoVenda.cancelamento(resposta);
                try {
                    InciandoCancelamentoUltimaVenda.testNCN(resposta);
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}
