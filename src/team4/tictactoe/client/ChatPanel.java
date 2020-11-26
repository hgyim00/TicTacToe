package team4.tictactoe.client;

import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author 
 * @since 2020.11
 *
 */
public class ChatPanel extends Panel {
	private static final long serialVersionUID = 3752014897574692475L;

	TicTacToeClient ticTacTocClient = null;
	JScrollPane scroll = null;	
	JTextArea jTextArea = null;

	public void create(TicTacToeClient parent) {
		ticTacTocClient = parent;
		
	      scroll = new javax.swing.JScrollPane();
	      jTextArea = new javax.swing.JTextArea();
	      
	      this.setBackground(new java.awt.Color(102, 102, 102));
	      
	        jTextArea.setColumns(20);
	        jTextArea.setRows(5);
	        scroll.setViewportView(jTextArea);
	        jTextArea.getAccessibleContext().setAccessibleParent(this);
	        
	        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(this);
	        this.setLayout(chatPanelLayout);
	        chatPanelLayout.setHorizontalGroup(
	            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(chatPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(scroll)
	                .addContainerGap())
	        );
	        chatPanelLayout.setVerticalGroup(
	                chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(chatPanelLayout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
	                    .addContainerGap())
	        );
	        
	        scroll.setVisible(true);
	        jTextArea.setVisible(true);

	        this.add(scroll);
	        this.add(jTextArea);
	        parent.add(this);

	}
}
