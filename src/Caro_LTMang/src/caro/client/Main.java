/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caro.client;

import caro.common.GPos;
import caro.common.CPiece;
import caro.common.KMessage;
import caro.common.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Nguyen Cao Ky
 */
public class Main extends javax.swing.JFrame implements inReceiveMessage{

    ListenServer listenServer;
    //PanelBoard windowPanel;
    
    JGoban Goban;
    JScrollPane jScroll;
    
    int GameState = 0;
    static final int WAIT = 0;
    static final int MY_TURN = 1;
    static final int YOU_WIN = 2;
    static final int YOU_LOSE = 3;
    
    static int interval;
    static Timer timer;
    int timeLimit;
    /**
     * Creates new form Main
     */
    public Main(ListenServer listenServer, int _time) {
        initComponents();
        this.timeLimit = _time; 
        interval = timeLimit;
        setTitle("Game Caro");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 565);
        
        
        setLayout(new BorderLayout());
        //windowPanel = new PanelBoard(socket);
        //add("Center", windowPanel);
        
        InitGame();
        setLocationRelativeTo(null);
        
      
        lblTime.setText(Integer.toString(timeLimit));
        
        this.listenServer = listenServer;
        this.listenServer.receive = this;
        
        try {
            listenServer.SendMessage(28, null); //Lay thong tin 2 ng
            listenServer.SendMessage(30, null); //Lay thong tin 2 ng
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void InitGame() {

        Goban = new JGoban();
        Goban.setPreferredSize(new Dimension(500,500));
        
        panelCaro.setViewportView(Goban);

        Goban.Sakiyomi = true;
        Goban.Kinjite = true;

        Goban.init(500, 500);
        Goban.Initialize();
        Goban.Draw();
    }
    

    void putStatus(String strStt)
    {
        lblStatus.setText(strStt);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.        

    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName1 = new javax.swing.JLabel();
        lblName2 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnExitRoom = new javax.swing.JButton();
        panelCaro = new javax.swing.JScrollPane();
        lblTime = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName1.setText("PLAYER 1");

        lblName2.setText("PLAYER 2");

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStatus.setText("Doi nguoi choi thu 2");
        lblStatus.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("X");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("O");

        btnExitRoom.setText("EXIT");
        btnExitRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitRoomMouseClicked(evt);
            }
        });
        btnExitRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitRoomActionPerformed(evt);
            }
        });

        panelCaro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCaroMouseClicked(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTime.setText("3000");
        lblTime.setToolTipText("");

        jLabel223.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel223.setText("Time: ");
        jLabel223.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblName1)
                        .addGap(212, 212, 212)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCaro, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel223)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTime)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName1)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblName2)
                        .addComponent(btnExitRoom)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel223, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(487, 487, 487))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCaro, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void exitRoom(){
        try {
            listenServer.SendMessage(39, null);   
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new RoomFrame(listenServer).setVisible(true);
                }
            });
            this.dispose();
        } catch (Exception e) {
        }
    }
    private void btnExitRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitRoomMouseClicked
        // TODO add your handling code here:
         try {
            listenServer.SendMessage(39, null);   
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new RoomFrame(listenServer).setVisible(true);
                }
            });
            this.dispose();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnExitRoomMouseClicked

    private void panelCaroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCaroMouseClicked
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        
        if (GameState==MY_TURN)
        {
            GPos pos = new GPos();
            int offetX = Goban.getX();
            int offetY = Goban.getY();

            if( !Goban.GetPos(x-offetX,y-offetY,pos) )
                return;

            if(Goban.Pieces[pos.x][pos.y].State == CPiece.EMPTY)
            {
                try {
                    
                    GameState = WAIT;
                    putStatus("Doi...");
                    timer.cancel();
                    timer.purge();
                    listenServer.SendMessage(30, pos);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                putStatus("Ban khong duoc danh vao vung nay!");
            }
        }

    }//GEN-LAST:event_panelCaroMouseClicked

    private void btnExitRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitRoomActionPerformed

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
                new LoginForm().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExitRoom;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTime;
    private javax.swing.JScrollPane panelCaro;
    // End of variables declaration//GEN-END:variables


    
    @Override
            public void ReceiveMessage(KMessage msg) throws IOException {
            switch (msg.getType()) {
                case 30: // get ban co
                {
                    Goban.Pieces = (CPiece[][])msg.getObject();
                    Goban.Draw();

                    break;
                }
                case 31:
                {
                    putStatus("Toi luot ban");
                    GameState = MY_TURN;
                    int delay = 1000;
                    int period = 1000;
                    timer = new Timer();
                    
                    lblTime.setText(Integer.toString(interval));
                    timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        if (interval == 1)
                            timer.cancel();
                            --interval;
                            lblTime.setText(Integer.toString(interval));
                            if (interval == 0){
                                JOptionPane.showMessageDialog(null,"Ban bi xu thua");
                                exitRoom();
                            }
                        }
                    }, delay, period);
                    break;
                }
                case 34: // Thong tin 2 ng choi
                {
                    Users[] arrUser = (Users[])msg.getObject();
                    if (arrUser!=null && arrUser.length>=2)
                    {
                        lblName1.setText(arrUser[0].getUsername());
                        lblName2.setText(arrUser[1].getUsername());
                    }
                    
                    break;
                }
                case 35:
                {
                    
                    if ("win".equalsIgnoreCase(msg.getObject().toString()))
                    {
                        GameState = YOU_WIN;
                        putStatus("Ban da thang");
                        timer.cancel();
                        timer.purge();
                        
                    }
                    else if ("lose".equalsIgnoreCase(msg.getObject().toString()))
                    {
                        GameState = YOU_LOSE;
                        putStatus("Ban da thua");
                    }
                    //System.out.println(msg.getObject());
                    break;
                }    
                case 36:
                {
                    putStatus("Doi...");

                    break;
                }   
                case 40: // Recieve Message
                {
                    String strMess = msg.getObject().toString();
                    /*if (ChatHistory.getText().isEmpty())
                        ChatHistory.setText(strMess);
                    else
                        ChatHistory.setText(ChatHistory.getText()+'\n'+strMess);
                    break;*/
                }   
                case 66:{
                    //String strTime = msg.getObject().toString();
                    //timeLimit = Integer.parseInt(strTime);
                    //lblTime.setText(Integer.toString(timeLimit));
                    break;
                }
            }
        }

    
}
