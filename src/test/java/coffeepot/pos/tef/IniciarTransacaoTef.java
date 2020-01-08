package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.CNF;
import coffeepot.pos.tef.dial.dispatch.CRT;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import coffeepot.pos.tef.misc.NotRespondingException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JOptionPane;

public class IniciarTransacaoTef {
    
    private int identificacao;
    private int venda;
    private BigDecimal valorDaVenda;

    public ResponseMessage inicarTransacaoTef(){
        try {
            System.out.println("");
            System.out.println("");
            System.out.println("crt");
            TefDial instance = new TefDial();
            instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
            instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));

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
                System.out.println("cnf:\n\n");
                System.out.println(sts.getOperation());
                System.out.println(sts.getIdentifier());
                System.out.println(sts.getFields());
                String voucherOfCustomer = resp.getVoucherOfCustomer();
                System.out.println("voucherOfCustomer = " + voucherOfCustomer);
                String voucherOfStore = resp.getVoucherOfStore();
                System.out.println("voucherOfStore = " + voucherOfStore);
                System.out.println("Iniciando impressão da via do cliente");
                imprimirComprovanteCartao(voucherOfCustomer+String.valueOf(new char[]{29,86,0}));
                System.out.println("Iniciando impressão da via da loja");
                imprimirComprovanteCartao(voucherOfStore+String.valueOf(new char[]{29,86,0}));
            } else {
                String mensagemUsuario = resp.getOperatorMessage();
                System.out.println(mensagemUsuario);
            }
            return resp;
        } catch (IOException | NotRespondingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    private static void imprimirComprovanteCartao(String comprovante) {
        try {
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob dpj = service.createPrintJob();
            InputStream stream = new ByteArrayInputStream(comprovante.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, new HashDocAttributeSet());
            dpj.print(doc, new HashPrintRequestAttributeSet());
        } catch (PrintException e) {
            System.err.println(e);
        }
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
