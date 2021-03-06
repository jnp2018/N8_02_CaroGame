/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caro.server;

import caro.common.Room;
import caro.common.Users;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Cao Ky
 */
public class Main extends javax.swing.JFrame {

    static public ArrayList<ClientHandler> lstClient;
    
    static public ArrayList<Room> lstRoom;

    static java.util.List<Users> uslist = new ArrayList<Users>();
    
   
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
              
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("SERVER RUNNING");
                ServerListener server;
                try {
                    server = new ServerListener();
                    server.start();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                lstClient = new ArrayList<ClientHandler>();
                lstRoom = new ArrayList<Room>();
             
                    //Room temp = new Room(i,4+i);
                    //lstRoom.add(temp);
                  
                
                new Main().setVisible(false);
            }
        });
    }

    static class ServerListener extends Thread {

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(1234);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socketToClient);
                    lstClient.add(clientHandler);
                    System.out.println("Server: " + socketToClient.getInetAddress() + " is connecting");
                    clientHandler.start();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
            }
        }
    }
    
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
