package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import coffeepot.pos.tef.dial.dispatch.CNF;
import coffeepot.pos.tef.dial.response.ResponseMessage;
import coffeepot.pos.tef.dial.response.StatusResponse;
import java.io.File;
import javax.swing.JOptionPane;

public class FormTefAdministrador extends javax.swing.JFrame {

    public FormTefAdministrador() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Tef Administrador");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String comprovate = this.testAdm();
            this.jTextArea1.setText(comprovate);
            System.out.println("comprovate = " + comprovate.isEmpty());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public String testAdm() throws Exception {
        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));
        ResponseMessage adm = instance.adm();
        if (adm.isTransactionOk()) {
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
            return comprovante;
        }
        return "";
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormTefAdministrador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
