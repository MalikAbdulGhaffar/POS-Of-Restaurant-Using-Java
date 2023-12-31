/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package crescent.Orders;

import crescentMain.DbConnection;
import crescentMain.MAIN;
import static crescentMain.MAIN.balance;
import static crescentMain.MAIN.paid;
import static crescentMain.MAIN.tot;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class searchOrder extends javax.swing.JFrame {
private String type;
    /**
     * Creates new form searchOrder
     */

    public searchOrder(String type) {
        this.type = type;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        animation();
    }

    public searchOrder() {
        initComponents();
        animation();
    }
private void animation(){
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconLogo.png")));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        orderIDField = new javax.swing.JTextField();
        searchOrderBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        b = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("(PRINTED ON THE ORDER RECEIPT)");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 460, 40));

        orderIDField.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        orderIDField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderIDField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0)));
        jPanel1.add(orderIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 270, 40));

        searchOrderBTN.setBackground(new java.awt.Color(51, 0, 0));
        searchOrderBTN.setFont(new java.awt.Font("Gill Sans MT", 1, 17)); // NOI18N
        searchOrderBTN.setForeground(new java.awt.Color(255, 255, 204));
        searchOrderBTN.setText("SEARCH ORDER");
        searchOrderBTN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 204)));
        searchOrderBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchOrderBTNActionPerformed(evt);
            }
        });
        jPanel1.add(searchOrderBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 210, 44));

        b.setColumns(20);
        b.setRows(5);
        jScrollPane1.setViewportView(b);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 380, 260));

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SEARCH ANY ORDER BY ORDERID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void searchOrderBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchOrderBTNActionPerformed
       
        DbConnection db = new DbConnection();
       Integer orderID;
        try {
            db.OpenConnection();
            if(orderIDField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter An Order ID!");
            }
            else{
                orderID = Integer.parseInt(orderIDField.getText());
                String query = "Select * from " + type + " where OrderID = " + orderID;
                db.GetData(query);
                
                b.append("      Crescent Restaurant \n");
                b.append("------------------------------------------------------------\n");
                b.append("\tOrder ID: #" + orderID + "\n");
                b.append("  Item\t\tQty \tPrice" +"\n");
                 b.append("-----------------------------------------------------------\n");
                  while (db.rst.next()) {
                       
                       
                    String Name = db.rst.getString("Name");
                     String Qty = db.rst.getString("Quantity");
                      String Price = db.rst.getString("Price");
                       

                      b.append(Name+"\t"+Qty +"\t"+Price + "\n");
                 }
                query = "Select * from orderDetails where OrderID = " + orderID;
                db.GetData(query);
                  while (db.rst.next()) {
                    String total = db.rst.getString("TotalPrice");
                    String pay = db.rst.getString("CashReceived");
                    String bal = db.rst.getString("CashChange");
                    String tt = db.rst.getString("Type");
                    String da = db.rst.getString("Date");
                    b.append("--------------------------------------------------------------\n");
                    b.append("   Sub Total : " + total +"\n");
                    b.append("   Cash Received  : " + pay +"\n");
                    b.append("   Remaining Balance   : " + bal +"\n");
                    b.append(" --------------------------------------------------------------\n");
                    b.append("   Type Of Order   : " + tt +"\n");
                    b.append(" --------------------------------------------------------------\n");
                    b.append(" Time Of Ordering: " + da + "\n");
                  }
                }
             db.CloseConnection();
            
        } catch (Exception e) {
        }
      
    }//GEN-LAST:event_searchOrderBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(searchOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea b;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField orderIDField;
    private javax.swing.JButton searchOrderBTN;
    // End of variables declaration//GEN-END:variables
}
