package team4.tictactoe.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.common.LoginMessage;
import team4.tictactoe.common.TicTacToeMessage;

/**
 * 
 * @author 임혜균
 * @since 2020.11
 */
public class TicTacToeClient extends JFrame {
   private static final long serialVersionUID = -5490724313330301162L;

   public ServerConnection serverConnection = null;
   public String hostName = null;
   public int port = 0;

   
   
   /**
    * 로그인 상태이면 사용자 이름을 저장한다. 로그인하지 않은 상태이면 null을 저장한다.
    */
   public String userName = null;

   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton10;
   private javax.swing.JButton jButton11;
   private javax.swing.JButton jButton2;
   private javax.swing.JButton jButton3;
   private javax.swing.JButton jButton4;
   private javax.swing.JButton jButton5;
   private javax.swing.JButton jButton6;
   private javax.swing.JButton jButton7;
   private javax.swing.JButton jButton8;
   private javax.swing.JButton jButton9;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JPanel loginPanel;
   private javax.swing.JPanel chatPanel;
   private javax.swing.JPanel ticTacToePanel;
   private javax.swing.JRadioButton jRadioButton1;
   private javax.swing.JRadioButton jRadioButton2;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTextArea jTextArea1;
   private javax.swing.JTextField jTextField1;
   private javax.swing.JPasswordField jPasswordField;
   private javax.swing.JTextField jTextField3;
   private javax.swing.ButtonGroup buttonGroup1;
   /**
    * 
    * @param args
    */
   public static void main(String[] args) {
      String hostName = "127.0.0.1";
      int port = 6789;

      if (args.length > 0) {
         hostName = args[0];
      }
      if (args.length > 1) {
         port = Integer.parseInt(args[1]);
      }

      
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
          java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
     

      TicTacToeClient tttc = new TicTacToeClient(hostName, port);
      tttc.create();
      tttc.openLogin();
      tttc.setVisible(true);
      tttc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**
    * 
    * @param hostName
    * @param port
    */
   public TicTacToeClient(String hostName, int port) {
      this.hostName = hostName;
      this.port = port;
   }
   private void initComponents() {

       buttonGroup1 = new javax.swing.ButtonGroup();
       loginPanel = new javax.swing.JPanel();
       jRadioButton1 = new javax.swing.JRadioButton();
       jRadioButton2 = new javax.swing.JRadioButton();
       jTextField1 = new javax.swing.JTextField();
       jPasswordField = new javax.swing.JPasswordField();
       jButton1 = new javax.swing.JButton();
       jButton2 = new javax.swing.JButton();
       jLabel1 = new javax.swing.JLabel();
       jLabel2 = new javax.swing.JLabel();
       jLabel3 = new javax.swing.JLabel();
       jTextField3 = new javax.swing.JTextField();
       chatPanel = new javax.swing.JPanel();
       jScrollPane1 = new javax.swing.JScrollPane();
       jTextArea1 = new javax.swing.JTextArea();
       ticTacToePanel = new javax.swing.JPanel();
       jButton3 = new javax.swing.JButton();
       jButton4 = new javax.swing.JButton();
       jButton5 = new javax.swing.JButton();
       jButton6 = new javax.swing.JButton();
       jButton7 = new javax.swing.JButton();
       jButton8 = new javax.swing.JButton();
       jButton9 = new javax.swing.JButton();
       jButton10 = new javax.swing.JButton();
       jButton11 = new javax.swing.JButton();
       jLabel4 = new javax.swing.JLabel();
       jLabel5 = new javax.swing.JLabel();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       loginPanel.setBackground(new java.awt.Color(204, 204, 204));
       buttonGroup1.add(jRadioButton1);

       jRadioButton1.setText("로그인");
       buttonGroup1.add(jRadioButton2);

       jRadioButton2.setText("회원가입");

       jTextField1.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField1ActionPerformed(evt);
           }
       });

       jPasswordField.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField2ActionPerformed(evt);
           }
       });

       jButton1.setText("Enter");

       jButton2.setText("Exit");
       jButton2.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jButton2ActionPerformed(evt);
           }
       });
       
       jLabel1.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
       jLabel1.setText(" ID  : ");

       jLabel2.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
       jLabel2.setText("PW : ");

       jLabel3.setFont(new java.awt.Font("굴림", 0, 12)); // NOI18N
       jLabel3.setText("Name :");

       jTextField3.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField3ActionPerformed(evt);
           }
       });

       javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
       loginPanel.setLayout(loginPanelLayout);
       loginPanelLayout.setHorizontalGroup(
           loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(loginPanelLayout.createSequentialGroup()
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(loginPanelLayout.createSequentialGroup()
                       .addGap(63, 63, 63)
                       .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                           .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                       .addGap(0, 59, Short.MAX_VALUE))
                   .addGroup(loginPanelLayout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jTextField3)))
               .addContainerGap())
           .addGroup(loginPanelLayout.createSequentialGroup()
               .addGap(30, 30, 30)
               .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(18, 18, 18)
               .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
               .addGap(36, 36, 36))
           .addGroup(loginPanelLayout.createSequentialGroup()
               .addContainerGap()
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(loginPanelLayout.createSequentialGroup()
                       .addComponent(jLabel1)
                       .addGap(14, 14, 14)
                       .addComponent(jTextField1))
                   .addGroup(loginPanelLayout.createSequentialGroup()
                       .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jPasswordField)))
               .addContainerGap())
       );
       loginPanelLayout.setVerticalGroup(
           loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(loginPanelLayout.createSequentialGroup()
               .addGap(14, 14, 14)
               .addComponent(jRadioButton2)
               .addGap(18, 18, 18)
               .addComponent(jRadioButton1)
               .addGap(199, 199, 199)
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(jLabel1)
                   .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addGap(40, 40, 40)
               .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(43, 43, 43))
       );

       chatPanel.setBackground(new java.awt.Color(102, 102, 102));

       jTextArea1.setColumns(20);
       jTextArea1.setRows(5);
       jScrollPane1.setViewportView(jTextArea1);
       chatPanel.setVisible(true);

       javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
       chatPanel.setLayout(chatPanelLayout);
       chatPanelLayout.setHorizontalGroup(
           chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(chatPanelLayout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane1)
               .addContainerGap())
       );
       chatPanelLayout.setVerticalGroup(
           chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(chatPanelLayout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
               .addContainerGap())
       );

       ticTacToePanel.setBackground(new java.awt.Color(153, 153, 153));

       jButton3.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
       jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon("C:\\Users\\신민철\\Desktop\\O.png")); // NOI18N
       jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               jButton3MouseClicked(evt);
           }
       });

       jButton4.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
       jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon("C:\\Users\\신민철\\Desktop\\X.png")); // NOI18N
       jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               jButton4MouseClicked(evt);
           }
       });
       jButton4.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jButton4ActionPerformed(evt);
           }
       });

       jButton5.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
       jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               jButton5MouseClicked(evt);
           }
       });

       jButton6.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
       jButton6.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jButton6ActionPerformed(evt);
           }
       });

       jButton7.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
       jButton7.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jButton7ActionPerformed(evt);
           }
       });

       jButton8.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N

       jButton9.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N

       jButton10.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N

       jButton11.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N

       jLabel4.setFont(new java.awt.Font("Lato Semibold", 0, 18)); // NOI18N
       jLabel4.setText("PLAYER1");

       jLabel5.setFont(new java.awt.Font("Lato Semibold", 0, 18)); // NOI18N
       jLabel5.setText("PLAYER2");

       ticTacToePanel.setVisible(true);

       javax.swing.GroupLayout ticTacToePanelLayout = new javax.swing.GroupLayout(ticTacToePanel);
       ticTacToePanel.setLayout(ticTacToePanelLayout);
       ticTacToePanelLayout.setHorizontalGroup(
           ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(ticTacToePanelLayout.createSequentialGroup()
               .addGap(99, 99, 99)
               .addComponent(jLabel4)
               .addGap(99, 99, 99)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(5, 5, 5)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(5, 5, 5)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(99, 99, 99)
               .addComponent(jLabel5)
               .addGap(99, 99, 99))
       );
       ticTacToePanelLayout.setVerticalGroup(
           ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticTacToePanelLayout.createSequentialGroup()
               .addGap(51, 51, 51)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                       .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(jLabel4)
                   .addComponent(jLabel5))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(49, 49, 49))
       );

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(ticTacToePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(ticTacToePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGap(26, 26, 26))
       );

       pack();
   }// </editor-fold>                        

   /**
    * 
    */
   public void create() {
            this.initComponents();
         
            jRadioButton1.addActionListener(new ActionSelectLogin());
         jRadioButton2.addActionListener(new ActionSelectRegister());
         jButton1.addActionListener(new ActionEnter(this));
         jButton2.addActionListener(new ActionExit());
         jButton3.addActionListener(new ActionOne());
         jButton4.addActionListener(new ActionTwo());
         jButton5.addActionListener(new ActionThree());
         jButton6.addActionListener(new ActionFour());
         jButton7.addActionListener(new ActionFive());
         jButton8.addActionListener(new ActionSix());
         jButton9.addActionListener(new ActionSeven());
         jButton10.addActionListener(new ActionEight());
         jButton11.addActionListener(new ActionNine());
         
         
         
   }

          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

          private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        

          private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           

          private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           

          private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           

          private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        

          private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {                                      
              // TODO add your handling code here:
          }                                     

          private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        

          private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        

          private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {                                      
              // TODO add your handling code here:
          }                                     

          private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {                                      
              // TODO add your handling code here:
          }                                     
          public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
               
            }
          private class ActionSelectLogin extends AbstractAction {
            private static final long serialVersionUID = 7908292853477113329L;

            @Override
            public void actionPerformed(ActionEvent e) {
               // login을 선택하면
               // Name 항목을 비활성화 한다.
               jTextField3.setEnabled(false);
            }
         }

         private class ActionSelectRegister extends AbstractAction {
            private static final long serialVersionUID = -573796346868072638L;

            @Override
            public void actionPerformed(ActionEvent e) {
               // register를 선택하면
               // Name 항목을 활성화 한다.
               jTextField3.setEnabled(true);
            }
         }

     
         private class ActionExit extends AbstractAction {
            private static final long serialVersionUID = -1955989745471471167L;

            @Override
            public void actionPerformed(ActionEvent e) {
               // 서버에 접속하여 회원가입을 한다.
            }
         }
         private class ActionOne extends AbstractAction {
            @Override
            public void actionPerformed(ActionEvent e) {
               TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(0);
               ticTacToeMsg.column=Integer.toString(0);
               jButton3.setBackground(new java.awt.Color(255, 55, 55));

            }
         }
       private class ActionTwo extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(0);
               ticTacToeMsg.column=Integer.toString(1);
               jButton4.setBackground(new java.awt.Color(55, 255, 55));
          }
         }
       private class ActionThree extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(0);
               ticTacToeMsg.column=Integer.toString(2);
          }
         }
       private class ActionFour extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(1);
               ticTacToeMsg.column=Integer.toString(0);
          }
         }
       private class ActionFive extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(1);
               ticTacToeMsg.column=Integer.toString(1);
          }
         }
       private class ActionSix extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(1);
               ticTacToeMsg.column=Integer.toString(2);
          }
         }
       private class ActionSeven extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(2);
               ticTacToeMsg.column=Integer.toString(0);
          }
         }
       private class ActionEight extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(2);
               ticTacToeMsg.column=Integer.toString(1);
          }
         }
       private class ActionNine extends AbstractAction {
          @Override
            public void actionPerformed(ActionEvent e) {
             TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
               ticTacToeMsg.row=Integer.toString(2);
               ticTacToeMsg.column=Integer.toString(2);
          }
         }
         private class ActionEnter extends AbstractAction {
            private static final long serialVersionUID = -1955989745471471167L;
            
            TicTacToeClient tictac =null;
            
            
            public ActionEnter(TicTacToeClient ticTacToeClient) {
               tictac=ticTacToeClient;
               
            }
            @Override
            public void actionPerformed(ActionEvent a) {
               try {
               // 서버에 접속
               Socket socket = new Socket(tictac.hostName, tictac.port);
               ServerConnection serverConnection = new ServerConnection(socket, tictac);
               tictac.serverConnection = serverConnection;
               serverConnection.start();

               LoginMessage loginMsg = new LoginMessage();
               loginMsg.userId = jTextField1.getText();
               loginMsg.userPassword = new String(jPasswordField.getPassword());
               if (jRadioButton2.isSelected()) {
                  // 회원가입이면 사용자 이름을 함께 전송한다.
                  loginMsg.userName = jTextField3.getText();
               }

               // 로그인/회원가입 요청
               serverConnection.sendMessage(loginMsg);
            } catch (UnknownHostException e) {
               // 서버를 찾을 수 없습니다.
               // e.printStackTrace();
            } catch (IOException e) {
               // 서버에 접속할 수 없습니다.
               // e.printStackTrace();
            }

            }
            }
   
         

        
          /**
           * @param args the command line arguments
           */
          
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
          
        

          // Variables declaration - do not modify                     

          // End of variables declaration                   
      


//      // 서버에 접속
//      try {
//         Socket socket = new Socket(hostName, port);
//         serverConnection = new ServerConnection(socket, this);
//      } catch (UnknownHostException e) {
//         e.printStackTrace();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
   

   /**
    * 로그인 화면으로 전환한다.
    */
   public void openLogin() {
      loginPanel.setVisible(true);
      ticTacToePanel.setVisible(false);
      chatPanel.setVisible(false);
   }

   /**
    * 게임 화면으로 전환한다.
    */
   public void openGame() {
      loginPanel.setVisible(false);
      ticTacToePanel.setVisible(true);
      chatPanel.setVisible(true);
   }
   
   /**
    * 로그인 메시지 수신을 처리한다.
    * 
    * @param msg
    */
   public void onReceiveLoginMessage(LoginMessage msg) {
      if (msg.userName != null) {
         // 로그인을 성공하면 게임 화면으로 전환한다.
         userName = msg.userName;
         System.out.println("Success");
         openGame();
      } else {
         // 로그인 실패
         userName = null;

         openLogin();
      }
   }

   /**
    * 채팅 메시지 수신을 처리한다.
    * 
    * @param msg
    */
   public void onReceiveChatMessage(ChatMessage msg) {
      
   }

   /**
    * 틱텍토 메시지 수신을 처리한다.
    * 
    * @param msg
    */
   public void onReceiveTicTacToeMessage(TicTacToeMessage msg) {
	   // state 처리하고 색칠해주면 마지막 칸은 반영이 안됨
	  /* color by marker */
	   System.out.println("msg");
	  Color red = new java.awt.Color(255, 55, 55);
 	  Color green = new java.awt.Color(55, 255, 55);
 	  Color color = null;
 	  
 	  if (msg.playerMark == "O")
 	  {
 		  color = red;
 	  }
 	  else if (msg.playerMark == "X")
 	  {
 		  color = green;
 	  }
 	  
 	  /* Color right space */
 	  if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 0) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 1) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 2) {
 		  jButton3.setBackground(color);
 	  }
 	  
 	  if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 0) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 1) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 2) {
 		  jButton3.setBackground(color);
 	  }
 	  
 	  if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 0) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 1) {
 		  jButton3.setBackground(color);
 	  }
 	  if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 2) {
 		  jButton3.setBackground(color);
 	  }
      //승패 결과를 알려주면서 게임을 계속할 것인지 물어보는 화면으로 전환한다.
      if(msg.gameState == "tie") { 
         
         /**
          * NetBeans를 이용한 GUI?
          */
         
      }//비겼습니다. 다시 하시겠습니까?
      else if(msg.gameState == "winO") {
         
         /**
          * NetBeans를 이용한 GUI?
          */
         
      }//player O가 이겼습니다. 다시 하시겠습니까?
      else if(msg.gameState == "winX") {
         
         /**
          * NetBeans를 이용한 GUI?
          */
         
      }else {
    	  
         /**
          * NetBeans를 이용한 GUI?
          */
  
      }//승패가 나지 않았을 경우에는 보드판에 상대방의 수를 표시하며 게임을 진행한다.
   }
   
   public void onCloseConnection() {
      userName = null;
      openLogin();
   }
}