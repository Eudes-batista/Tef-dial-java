package tef.impressao;

import tef.impressao.gaveta.BematechGaveta;
import tef.impressao.gaveta.DarumaGaveta;
import tef.impressao.gaveta.ElginEpsonGaveta;
import tef.impressao.gaveta.GavetaPadrao;
import tef.dial.response.ResponseMessage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javax.print.attribute.HashPrintServiceAttributeSet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class ControleImpressao {

    private static ControleImpressao controleImpressao = null;
    private String impressora;
    private String modeloDaImpressora;
    private int quantidadeDeVias;
    private boolean isGaveta;

    public void imprime(File file) throws IOException, PrinterException {
        PDDocument doc = PDDocument.load(file);
        PrintService servico = detectaImpressoras();
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(doc));
        job.setPrintService(servico);
        job.setJobName(file.getName());
        job.print();
        doc.close();
    }

    public void imprimirComprovanteTefAdministrador(String comprovante) {
        this.imprimirComprovante(this.prepararComprovanteParaImpressao(comprovante));
    }

    public void imprimirComprovanteCancelamento(String comprovante) {
        for (int i = 0; i < this.quantidadeDeVias; i++) {
            this.imprimirComprovante(this.prepararComprovanteParaImpressao(comprovante));
        }
    }

    public void imprimirComprovanteTef(ResponseMessage responseMessage) throws IOException {
        if (this.quantidadeDeVias == 1) {
            this.imprimirComprovante(this.prepararComprovanteParaImpressao(responseMessage.getVoucherOfCustomer()));
        }
        if (this.quantidadeDeVias > 1) {
            for (int i = 0; i < this.quantidadeDeVias; i++) {
                this.imprimirComprovante(this.prepararComprovanteParaImpressao(responseMessage.getVoucherOfCustomer()));
            }
        }
        this.imprimirComprovante(this.prepararComprovanteParaImpressao(responseMessage.getVoucherOfStore()));
    }

    public void abrirGaveta() throws IOException {
        if (!this.isGaveta) {
            return;
        }
        GavetaPadrao gavetaPadrao;
        if (this.modeloDaImpressora.equals("ELGIN")) {
            gavetaPadrao = ElginEpsonGaveta.getElginGaveta();
            gavetaPadrao.abrirGaveta();
            return;
        }
        if (this.modeloDaImpressora.equals("BEMATECH")) {
            gavetaPadrao = BematechGaveta.getBematechGaveta();
            gavetaPadrao.abrirGaveta();
            return;
        }
        if (this.modeloDaImpressora.equals("DARUMA")) {
            gavetaPadrao = DarumaGaveta.getDarumaGaveta();
            gavetaPadrao.abrirGaveta();
            return;
        }
        gavetaPadrao = ElginEpsonGaveta.getElginGaveta();
        gavetaPadrao.abrirGaveta();
    }

    public static ControleImpressao getControleImpressao() {
        if (controleImpressao == null) {
            controleImpressao = new ControleImpressao();
        }
        return controleImpressao;
    }

    public void imprimirComprovante(String comprovante) {
        try {
            PrintService service = this.detectaImpressoras();
            DocPrintJob docPrintJob = service.createPrintJob();
            InputStream stream = new ByteArrayInputStream(comprovante.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, new HashDocAttributeSet());
            docPrintJob.print(doc, new HashPrintRequestAttributeSet());
        } catch (PrintException e) {

        }
    }

    public String cortarPapel() {
        return String.valueOf(new char[]{29, 86, 0});
    }

    private PrintService detectaImpressoras() {
        PrintService service = null;
        try {
            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(docFlavor, new HashPrintServiceAttributeSet());
            for (PrintService printService : ps) {
                if (printService.getName().toUpperCase().contains(this.impressora.toUpperCase())) {
                    service = printService;
                }
            }
            return service;
        } catch (Exception e) {
            return service;
        }
    }
    
    private String prepararComprovanteParaImpressao(String comprovante) {
        return comprovante + "\n\n\n\n\n" + this.cortarPapel();
    }

}
