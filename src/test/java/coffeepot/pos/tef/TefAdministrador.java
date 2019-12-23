package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.CNF;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import java.io.File;
import java.io.IOException;

public class TefAdministrador {

    public static void main(String[] args) throws IOException, Exception {
        testAdm();
    }

    public static void testAdm() throws Exception {
        System.out.println("");
        System.out.println("");
        System.out.println("adm");
        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));
        ResponseMessage adm = instance.adm();
        Long nsuCancelamento = adm.getCanceledNsu();
        if (adm.isTransactionOk()) {
            if (nsuCancelamento != null && nsuCancelamento != 0) {
                CNF cnf = new CNF();
                cnf.setIdentifier(adm.getIdentifier());
                cnf.setControlCode(adm.getControlCode());
                cnf.setNetwork(adm.getNetwork());
                cnf.setNsu(adm.getNsu());
                cnf.setFiscalDocumentNumberLinked(adm.getFiscalDocumentNumberLinked());
                StatusResponse sts = instance.cnf(cnf);
                System.out.println("cnf:\n\n");
                System.out.println(sts.getOperation());
                System.out.println(sts.getIdentifier());
                System.out.println(sts.getFields());
                String comprovante = adm.getVoucherImage();
                System.out.println(comprovante);
            }
        }
    }

}
