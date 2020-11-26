package team4.tictactoe.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 틱택토 게임 서버
 * 
 * 사용자 접속을 받아들인다.<br/>
 * 사용자가 접속하면 쓰레드를 생성하여 사용자로부터 메시지를 받고 처리하도록 한다.
 * 
 * @author 임혜균
 * @since 2020.11
 */
public class TicTacToeServer {

	/**
	 * 틱택토 게임 서버 메인.<br/>
	 * 인수로 포트번호를 입력받는다.
	 * 
	 * @param args [0] 포트번호
	 */
	public static void main(String[] args) {
		int port = 6789;
		if (args.length > 0) {
			// 인수가 있으면 포트번호로 사용한다.
			port = Integer.parseInt(args[0]);
		}

		ServerSocket welcomeSocket;
		Socket connectionSocket;
		LoginUser loginUser;

		try {
			welcomeSocket = new ServerSocket(port);

			while (true) {
				// 사용자 접속을 받아들인다.
				connectionSocket = welcomeSocket.accept();

				// 사용자가 접속하면 사용자에 대한 쓰레드를 생성하고 실행한다.
				loginUser = new LoginUser(connectionSocket);
				loginUser.start();

				connectionSocket = null;
				loginUser = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}