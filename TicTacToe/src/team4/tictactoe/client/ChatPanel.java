package team4.tictactoe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import team4.tictactoe.common.ChatMessage;

/**
 * 
 * @author
 * @since 2020.11
 *
 */
public class ChatPanel extends Panel {
	private static final long serialVersionUID = 3752014897574692475L;

	TicTacToeClient ticTacTocClient = null;

	JButton sendBtn = null;
	JTextField sendText = null;
	JTextArea gotMessage = null;
	JScrollPane scroll = null;

	public void create(TicTacToeClient parent) {
		ticTacTocClient = parent;

		// 화면 배치를 고정 값으로 하기 위해 매니저 해제
		setLayout(null);

		sendBtn = new JButton();
		sendText = new JTextField();
		gotMessage = new JTextArea();
		scroll = new JScrollPane(gotMessage);

		sendBtn.setText("Send");

		sendText.setBounds(10, 90, 80, 24);
		sendBtn.setBounds(90, 90, 40, 24);
		gotMessage.setBounds(10, 10, 120, 80);

		sendBtn.addActionListener(new ActionSendText());

		sendBtn.setVisible(true);
		sendText.setVisible(true);
		gotMessage.setVisible(true);

		this.add(sendText);
		this.add(sendBtn);
		this.add(gotMessage);

		parent.add(this);
	}

	public void processMessage(ChatMessage msg) {
		// 화면에 표시
		gotMessage.append(msg.userName + ": " + msg.chatText + "\n");
	}

	private class ActionSendText extends AbstractAction {
		private static final long serialVersionUID = -820241603353544302L;

		@Override
		public void actionPerformed(ActionEvent ev) {
			ChatMessage chatMsg = new ChatMessage();
			chatMsg.userId = ticTacTocClient.userId;
			chatMsg.chatText = sendText.getText();

			// 화면에 표시
			gotMessage.append(ticTacTocClient.userName + ": " + chatMsg.chatText + "\n");
			
			// 채팅 서버에 전송
			ticTacTocClient.serverConnection.sendMessage(chatMsg);
		}
	}
}
