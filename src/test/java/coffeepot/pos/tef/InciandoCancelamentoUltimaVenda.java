package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.CRT;
import coffeepot.pos.tef.dial.dispatch.NCN;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InciandoCancelamentoUltimaVenda {

    public static void main(String[] args) {
        try {
            testNCN();
        } catch (Exception ex) {
            Logger.getLogger(InciandoCancelamentoUltimaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void testNCN() throws Exception {
        System.out.println("");
        System.out.println("");
        System.out.println("testNCN");
        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));

        CRT crt = new CRT();
        crt.setCurrency(Currency.getInstance("BRL"));
        crt.setTotalValue(new BigDecimal("19.99"));
        crt.setFiscalDocumentNumberLinked(123456);

        ResponseMessage resp = instance.crt(crt);
        if (resp.isTransactionOk()) {
            NCN ncn = new NCN();
            ncn.setIdentifier(resp.getIdentifier());
            ncn.setControlCode(resp.getControlCode());
            ncn.setNetwork(resp.getNetwork());
            ncn.setNsu(resp.getNsu());
            ncn.setTaxDocumentNumberLinked(resp.getFiscalDocumentNumberLinked());
            StatusResponse sts = instance.ncn(ncn);
            System.out.println("ncn:\n\n");
            System.out.println(sts.getOperation());
            System.out.println(sts.getIdentifier());
            System.out.println(sts.getFields());
        }
        System.out.println("conteúdo do arquivo:\n" + resp.getOriginalFile());
    }
    
}
