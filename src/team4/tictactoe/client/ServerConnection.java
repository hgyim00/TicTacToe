package team4.tictactoe.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.common.LoginMessage;
import team4.tictactoe.common.Message;
import team4.tictactoe.common.TicTacToeMessage;

/**
 * 서버 접속 상태를 유지하고 메시지를 수신하여 클라이언트 기능에 전달한다.
 * 
 * @author 임혜균
 * @since 2020.11
 */
public class ServerConnection extends Thread {
	public boolean goOn = true;

	private TicTacToeClient ticTacToeClient = null;

	private BufferedReader inStream = null;
	private DataOutputStream outStream = null;

	public ServerConnection(Socket connectionSocket, TicTacToeClient ticTacToeClient) {
		try {
			this.ticTacToeClient = ticTacToeClient;
			this.inStream = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			this.outStream = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 서버에서 메시지를 수신하여 클라이언트 기능에 전달한다.
	 */
	public void run() {
		String msgLine;
		LoginMessage loginMessage = new LoginMessage();
		ChatMessage chatMessage = new ChatMessage();
		TicTacToeMessage ticTacTocMessage = new TicTacToeMessage();

		while (goOn) {
			try {
				msgLine = this.inStream.readLine();

				if (loginMessage.setMessageString(msgLine)) {
					// 로그인 메시지이면 로그인한다.
					ticTacToeClient.onReceiveLoginMessage(loginMessage);
				} else if (ticTacToeClient.userName != null) {
					// 로그인이 되어 있으면 게임이나 채팅을 한다.
					if (chatMessage.setMessageString(msgLine)) {
						ticTacToeClient.onReceiveChatMessage(chatMessage);
					} else if (ticTacTocMessage.setMessageString(msgLine)) {
						ticTacToeClient.onReceiveTicTacToeMessage(ticTacTocMessage);
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();

				// 접속이 끊기면 로그아웃 처리하고 게임을 종료한다.
				ticTacToeClient.onCloseConnection();
				goOn = false;
			}
		}
	}

	/**
	 * 서버에 메시지를 전송한다.
	 * 
	 * @param msg
	 */
	public void sendMessage(Message msg) {
		try {
			String msgStr = msg.getMessageString();
			this.outStream.writeBytes(msgStr + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
