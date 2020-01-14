package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.CNC;
import coffeepot.pos.tef.dial.dispatch.CNF;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import coffeepot.pos.tef.misc.NotRespondingException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

public class CancelamentoVenda {

    public void cancelamento(ResponseMessage responseMessage) throws IOException, NotRespondingException {

        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));

        CNC cnc = new CNC();

        cnc.setIdentifier(responseMessage.getIdentifier());
        cnc.setTotalValue(responseMessage.getTotalValue());
        cnc.setNsu(responseMessage.getNsu());
        cnc.setNetwork(responseMessage.getNetwork());
        cnc.setCheckDate(responseMessage.getCheckDate());
        cnc.setTransactionTimestamp(responseMessage.getTransactionTimestamp());
        cnc.setDoc(responseMessage.getDoc());
        cnc.setTaxDocumentNumberLinked(responseMessage.getFiscalDocumentNumberLinked());

        ResponseMessage resposta = instance.cnc(cnc);

        if (responseMessage.isTransactionOk()) {
            CNF cnf = new CNF();
            cnf.setIdentifier(resposta.getIdentifier());
            cnf.setControlCode(resposta.getControlCode());
            cnf.setNetwork(resposta.getNetwork());
            cnf.setNsu(resposta.getNsu());
            cnf.setFiscalDocumentNumberLinked(resposta.getFiscalDocumentNumberLinked());
            StatusResponse sts = instance.cnf(cnf);
            System.out.println("cnf:\n\n");
            System.out.println(sts.getOperation());
            System.out.println(sts.getIdentifier());
            System.out.println(sts.getFields());
            String comprovante = resposta.getVoucherImage();
            System.out.println(comprovante);
            imprimirComprovanteCartao(comprovante);
        }
    }

     private void imprimirComprovanteCartao(String comprovante) {
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
    
}
