package team4.tictactoe.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.common.LoginMessage;
import team4.tictactoe.common.TicTacToeMessage;

/**
 * 
 * @author 임혜균, 신민철
 * @since 2020.11
 */
public class TicTacToeClient extends JFrame {
	private static final long serialVersionUID = -5490724313330301162L;

	public ServerConnection serverConnection = null;
	public String hostName = null;
	public int port = 0;

	public String playerMarker = null;
	public String opponent = null;
	public String player = null;
	public String turn = null;
	/**
	 * 로그인 상태이면 사용자 이름을 저장한다. 로그인하지 않은 상태이면 null을 저장한다.
	 */
	public String userId = null;
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
	private javax.swing.JLabel jLabel6;
	private javax.swing.JTextField sendText;
	private javax.swing.JButton sendBtn;

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

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TicTacToeClient.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}

		/* 화면을 출력한다 */

		TicTacToeClient tttc = new TicTacToeClient(hostName, port);
		tttc.create();
		tttc.setResizable(false);
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

	public void setGame(String marker, String opponent_) {
		playerMarker = marker;
		opponent = opponent_;
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
		sendBtn = new javax.swing.JButton();
		sendText = new javax.swing.JTextField();
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
		jLabel6 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		loginPanel.setBackground(new java.awt.Color(135, 135, 135));
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

		jLabel6.setFont(new java.awt.Font("Lato Semibold", 2, 24)); // NOI18N
		jLabel6.setText("                TICTACTOEGAME");

		jTextField3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(loginPanel);
		loginPanel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30)
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE,
												213, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 24, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jTextField1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE, 383,
																Short.MAX_VALUE)
														.addComponent(jPasswordField,
																javax.swing.GroupLayout.Alignment.TRAILING)))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(24, 24, 24).addComponent(jTextField3)))))
						.addContainerGap())
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(71, 71, 71)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jRadioButton2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(72, 72, 72)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(57, 57, 57)
						.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(66, 66, 66)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jRadioButton2).addComponent(jRadioButton1))
						.addGap(56, 56, 56)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(40, 40, 40)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(24, 24, 24)));

		chatPanel.setBackground(new java.awt.Color(102, 102, 102));

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);
		chatPanel.setVisible(true);
		sendBtn.setText("Send");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(chatPanel);
		chatPanel.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(
						jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
										.addGroup(jPanel2Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jScrollPane1)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
														jPanel2Layout.createSequentialGroup().addComponent(sendText)
																.addGap(41, 41, 41).addComponent(sendBtn)))
										.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(sendBtn).addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		ticTacToePanel.setBackground(new java.awt.Color(153, 153, 153));

		jButton3.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton3MouseClicked(evt);
			}
		});

		jButton4.setIcon(new javax.swing.ImageIcon("empty.png")); // NOI18N
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

		jLabel5.setFont(new java.awt.Font("Lato Semibold", 0, 18)); // NOI18N

		ticTacToePanel.setVisible(true);

		javax.swing.GroupLayout ticTacToePanelLayout = new javax.swing.GroupLayout(ticTacToePanel);
		ticTacToePanel.setLayout(ticTacToePanelLayout);
		ticTacToePanelLayout.setHorizontalGroup(ticTacToePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(ticTacToePanelLayout.createSequentialGroup().addGap(99, 99, 99).addComponent(jLabel4)
						.addGap(99, 99, 99)
						.addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(5, 5, 5)
						.addGroup(ticTacToePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(5, 5, 5)
						.addGroup(ticTacToePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(99, 99, 99).addComponent(jLabel5).addGap(99, 99, 99)));
		ticTacToePanelLayout.setVerticalGroup(ticTacToePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticTacToePanelLayout.createSequentialGroup()
						.addGap(51, 51,
								51)
						.addGroup(ticTacToePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(ticTacToePanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jLabel4).addComponent(jLabel5))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(ticTacToePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(ticTacToePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(49, 49, 49)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(ticTacToePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(ticTacToePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(26, 26, 26)));

		pack();
	}

	/**
	* 
	*/
	public void create() {
		this.initComponents();

		java.awt.Rectangle bound = getGraphicsConfiguration().getBounds();
		int w = 750;
		int h = 536;
		int x = (bound.x + bound.width / 2) - w / 2;
		int y = (bound.y + bound.height / 2) - h / 2;
		setBounds(x, y, w, h);

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
		sendBtn.addActionListener(new ActionSendText());

	}

	@SuppressWarnings("unchecked")

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {

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
			System.exit(0);
		}
	}

	private class ActionOne extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(0);
				ticTacToeMsg.column = Integer.toString(0);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionTwo extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(0);
				ticTacToeMsg.column = Integer.toString(1);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionThree extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(0);
				ticTacToeMsg.column = Integer.toString(2);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionFour extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(1);
				ticTacToeMsg.column = Integer.toString(0);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionFive extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(1);
				ticTacToeMsg.column = Integer.toString(1);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionSix extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(1);
				ticTacToeMsg.column = Integer.toString(2);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionSeven extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(2);
				ticTacToeMsg.column = Integer.toString(0);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionEight extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(2);
				ticTacToeMsg.column = Integer.toString(1);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionNine extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (turn.equals(playerMarker)) {
				TicTacToeMessage ticTacToeMsg = new TicTacToeMessage();
				ticTacToeMsg.playerMark = playerMarker;
				ticTacToeMsg.row = Integer.toString(2);
				ticTacToeMsg.column = Integer.toString(2);
				ticTacToeMsg.turn = turn;
				System.out.println(ticTacToeMsg.row + " " + ticTacToeMsg.column);
				serverConnection.sendMessage(ticTacToeMsg);
			} else {
				JOptionPane.showMessageDialog(ticTacToePanel, "NOT YOUR TURN");
			}
		}
	}

	private class ActionSendText extends AbstractAction {
		private static final long serialVersionUID = -820241603353544302L;

		@Override
		public void actionPerformed(ActionEvent ev) {
			ChatMessage chatMsg = new ChatMessage();
			chatMsg.userId = userId;
			chatMsg.chatText = sendText.getText();

			// 화면에 표시
			jTextArea1.append(userName + ": " + chatMsg.chatText + "\n");

			// 채팅 서버에 전송
			serverConnection.sendMessage(chatMsg);
		}

	}

	private class ActionEnter extends AbstractAction {
		private static final long serialVersionUID = -1955989745471471167L;

		TicTacToeClient tictac = null;

		public ActionEnter(TicTacToeClient ticTacToeClient) {
			tictac = ticTacToeClient;

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
	 * @param args
	 *            the command line arguments
	 */

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
			userId = msg.userId;
			userName = msg.userName;
			openGame();
		} else {
			// 로그인 실패
			userId = null;
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
		processMessage(msg);
	}

	public void processMessage(ChatMessage msg) {
		jTextArea1.append(msg.userName + ": " + msg.chatText + "\n");
	}

	/**
	 * 틱텍토 메시지 수신을 처리한다.
	 * 
	 * @param msg
	 */
	public void onReceiveTicTacToeMessage(TicTacToeMessage msg) {

		/* 색을 칠할 공간 생성 */
		System.out.println("HELLO");
		Color color = null;
		Color red = new java.awt.Color(255, 55, 55);
		Color green = new java.awt.Color(55, 255, 55);
		String state=null;
	     	if(msg.gameState!=null) {
	    	 	 state = msg.gameState;
	      	}
		if (playerMarker == null) {
			playerMarker = msg.playerMark;
			turn = "X";
		}
		if (player == null) {
			player = msg.player;
			jLabel4.setText(player);

		}
		if (opponent == null) {
			opponent = msg.opponent;
			jLabel5.setText(opponent);

		}

		if (playerMarker.equals(msg.turn)) {
			color = red;
			System.out.println("color: " + red);
		} else if (!playerMarker.equals(msg.turn)) {
			color = green;
			System.out.println("color: " + green);

		} else {
			color = null;
		}
		if (msg.turn != null) {
			turn = msg.turn;
			System.out.println("TURN" + turn);
		}
			    
	     	if(state.equals("ALREADY")){
	    	 	if(turn.equals(playerMarker)) {
    		  		JOptionPane.showMessageDialog(ticTacToePanel, "Already selected");
	    	  	}
	      	}
		else if (msg.row != null && msg.column != null) {
			/* 색을 칠할 공간 생성 */
			if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 0) {
				jButton3.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 1) {
				jButton4.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 0 && Integer.parseInt(msg.column) == 2) {
				jButton5.setBackground(color);
			}

			if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 0) {
				jButton6.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 1) {
				jButton7.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 1 && Integer.parseInt(msg.column) == 2) {
				jButton8.setBackground(color);
			}

			if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 0) {
				jButton9.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 1) {
				jButton10.setBackground(color);
			}
			if (Integer.parseInt(msg.row) == 2 && Integer.parseInt(msg.column) == 2) {
				jButton11.setBackground(color);
			}
		}
		// 승패 결과를 알려주면서 게임을 계속할 것인지 물어보는 화면으로 전환한다.
		if (msg.gameState != null) {
			if (msg.gameState.equals("tie")) {
				System.out.println("Tie");
				int result = JOptionPane.showConfirmDialog(ticTacToePanel, "Draw | KEEP PLAYING?", "CONFIRM",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				} else if (result == JOptionPane.YES_OPTION) {
					jButton3.setBackground(null);
					jButton4.setBackground(null);
					jButton5.setBackground(null);
					jButton6.setBackground(null);
					jButton7.setBackground(null);
					jButton8.setBackground(null);
					jButton9.setBackground(null);
					jButton10.setBackground(null);
					jButton11.setBackground(null);
				} else {
					System.exit(0);
				}

			} //비겼을 경우
			else if (msg.gameState.equals("winO")) {

				if (playerMarker.equals("O")) {
					System.out.println("YOU Win");

	    		  		int result = JOptionPane.showConfirmDialog(ticTacToePanel, "YOU WIN | KEEP PLAYING?","CONFIRM",JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
						System.exit(0);
					} else if (result == JOptionPane.YES_OPTION) {
						jButton3.setBackground(null);
						jButton4.setBackground(null);
						jButton5.setBackground(null);
						jButton6.setBackground(null);
						jButton7.setBackground(null);
						jButton8.setBackground(null);
						jButton9.setBackground(null);
						jButton10.setBackground(null);
						jButton11.setBackground(null);
					} else {
						System.exit(0);
					}
				} else if (playerMarker.equals("X")) {
					System.out.println("YOU Lose");

	    		 		int result=JOptionPane.showConfirmDialog(ticTacToePanel, "YOU LOSE | KEEP PLAYING?","CONFIRM",JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
						System.exit(0);
					} else if (result == JOptionPane.YES_OPTION) {
						jButton3.setBackground(null);
						jButton4.setBackground(null);
						jButton5.setBackground(null);
						jButton6.setBackground(null);
						jButton7.setBackground(null);
						jButton8.setBackground(null);
						jButton9.setBackground(null);
						jButton10.setBackground(null);
						jButton11.setBackground(null);
					} else {
						System.exit(0);
					}

				}
			} //승패가 날 경우
			else if (msg.gameState.equals("winX")) {

				if (playerMarker.equals("X")) {
					System.out.println("YOU Win");

		    		  	int result = JOptionPane.showConfirmDialog(ticTacToePanel, "YOU WIN | KEEP PLAYING?","CONFIRM",JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
						System.exit(0);
					} else if (result == JOptionPane.YES_OPTION) {
						jButton3.setBackground(null);
						jButton4.setBackground(null);
						jButton5.setBackground(null);
						jButton6.setBackground(null);
						jButton7.setBackground(null);
						jButton8.setBackground(null);
						jButton9.setBackground(null);
						jButton10.setBackground(null);
						jButton11.setBackground(null);
					} else {
						System.exit(0);
					}

				} else if (playerMarker.equals("O")) {
					System.out.println("YOU Lose");

		    		  	int result = JOptionPane.showConfirmDialog(ticTacToePanel, "YOU LOSE | KEEP PLAYING?","CONFIRM",JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
						System.exit(0);
					} else if (result == JOptionPane.YES_OPTION) {
						jButton3.setBackground(null);
						jButton4.setBackground(null);
						jButton5.setBackground(null);
						jButton6.setBackground(null);
						jButton7.setBackground(null);
						jButton8.setBackground(null);
						jButton9.setBackground(null);
						jButton10.setBackground(null);
						jButton11.setBackground(null);
					} else {
						System.exit(0);
					}
				}

				/**
				 * NetBeans를 이용한 GUI
				 */

			}

		} // 게임이 끝난 후 결과 창이 나타나고 게임의 진행 여부를 묻는다.
	}

	/*연결을 끊고 초기화*/
	public void onCloseConnection() {
		userName = null;
		openLogin();
	}
}
