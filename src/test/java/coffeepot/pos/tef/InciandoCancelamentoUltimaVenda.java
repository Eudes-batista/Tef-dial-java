package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.NCN;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import java.io.File;

public class InciandoCancelamentoUltimaVenda {

    public static void testNCN(ResponseMessage requisicao) throws Exception {
        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));

        NCN ncn = new NCN();
        ncn.setIdentifier(requisicao.getIdentifier());
        ncn.setControlCode(requisicao.getControlCode());
        ncn.setNetwork(requisicao.getNetwork());
        ncn.setNsu(requisicao.getNsu());
        ncn.setTaxDocumentNumberLinked(requisicao.getFiscalDocumentNumberLinked());
        StatusResponse sts = instance.ncn(ncn);
        System.out.println("ncn:\n\n");
        System.out.println(sts.getOperation());
        System.out.println(sts.getIdentifier());
        System.out.println(sts.getFields());
        System.out.println("conteúdo do arquivo:\n" + requisicao.getOriginalFile());
    }
}
