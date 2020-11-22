package team4.tictactoe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import team4.tictactoe.common.LoginMessage;

/**
 * 
 * @author 임혜균
 * @since 2020.11
 *
 */
public class LoginPanel extends Panel {
	private static final long serialVersionUID = -9077614322650298497L;

	TicTacToeClient ticTacTocClient = null;

	/* login, register 중 하나만 선택 */
	ButtonGroup grp = new ButtonGroup();

	JRadioButton rdLogin = null;
	JRadioButton rdRegister = null;
	JLabel idLabel = null;
	JTextField idField = null;
	JLabel pwLabel = null;
	JPasswordField pwField = null;
	JLabel nameLabel = null;
	JTextField nameField = null;
	JButton btnEnter = null;
	JButton btnExit = null;

	public void create(TicTacToeClient parent) {
		ticTacTocClient = parent;

		// 화면 배치를 고정 값으로 하기 위해 매니저 해제
		setLayout(null);

		// 화면 구성 항목 객체 생성
		rdLogin = new JRadioButton();
		rdRegister = new JRadioButton();
		grp.add(rdRegister);
		grp.add(rdLogin);

		idLabel = new JLabel("ID");
		idField = new JTextField();
		pwLabel = new JLabel("PW");
		pwField = new JPasswordField();
		nameLabel = new JLabel("Name");
		nameField = new JTextField();
		btnEnter = new JButton();
		btnExit = new JButton();

		// 화면 구성 항목 레이블
		rdLogin.setText("login");
		rdRegister.setText("register");
		btnEnter.setText("Enter");
		btnExit.setText("Exit");

		// 화면 구성 항목 배치
		rdRegister.setBounds(10, 10, 80, 24);
		rdLogin.setBounds(90, 10, 60, 24);
		idLabel.setBounds(10, 40, 40, 24);
		idField.setBounds(50, 40, 80, 24);
		pwLabel.setBounds(10, 70, 40, 24);
		pwField.setBounds(50, 70, 80, 24);
		nameLabel.setBounds(10, 100, 40, 24);
		nameField.setBounds(50, 100, 80, 24);
		btnEnter.setBounds(140, 40, 80, 24);
		btnExit.setBounds(140, 70, 80, 24);

		// 화면 구성 항목 동작 지정
		rdLogin.addActionListener(new ActionSelectLogin());
		rdRegister.addActionListener(new ActionSelectRegister());
		btnEnter.addActionListener(new ActionEnter());
		btnExit.addActionListener(new ActionExit());

		// 화면 구성 항목 보이기
		rdLogin.setVisible(true);
		rdRegister.setVisible(true);
		idLabel.setVisible(true);
		idField.setVisible(true);
		pwLabel.setVisible(true);
		pwField.setVisible(true);
		nameLabel.setVisible(true);
		nameField.setVisible(true);
		btnEnter.setVisible(true);
		btnExit.setVisible(true);

		// 화면 구성 항목 관리
		this.add(rdRegister);
		this.add(rdLogin);
		this.add(idLabel);
		this.add(idField);
		this.add(pwLabel);
		this.add(pwField);
		this.add(nameLabel);
		this.add(nameField);
		this.add(btnEnter);
		this.add(btnExit);

		parent.setContentPane(this);
	}

	private class ActionSelectLogin extends AbstractAction {
		private static final long serialVersionUID = 7908292853477113329L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// login을 선택하면
			// Name 항목을 비활성화 한다.
			nameField.setEnabled(false);
		}
	}

	private class ActionSelectRegister extends AbstractAction {
		private static final long serialVersionUID = -573796346868072638L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// register를 선택하면
			// Name 항목을 활성화 한다.
			nameField.setEnabled(true);
		}
	}

	private class ActionEnter extends AbstractAction {
		private static final long serialVersionUID = 8627631814865883106L;

		@Override
		public void actionPerformed(ActionEvent ev) {
			// 서버에 접속하여 로그인 한다.

			try {
				// 서버에 접속
				Socket socket = new Socket(ticTacTocClient.hostName, ticTacTocClient.port);
				ServerConnection serverConnection = new ServerConnection(socket, ticTacTocClient);
				ticTacTocClient.serverConnection = serverConnection;
				serverConnection.start();

				LoginMessage loginMsg = new LoginMessage();
				loginMsg.userId = idField.getText();
				loginMsg.userPassword = new String(pwField.getPassword());
				if (rdRegister.isSelected()) {
					// 회원가입이면 사용자 이름을 함께 전송한다.
					loginMsg.userName = nameField.getText();
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

	private class ActionExit extends AbstractAction {
		private static final long serialVersionUID = -1955989745471471167L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// 서버에 접속하여 회원가입을 한다.
		}
	}
}
