package team4.tictactoe.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.common.LoginMessage;
import team4.tictactoe.common.Message;
import team4.tictactoe.common.TicTacToeMessage;

/**
 * 서버에 접속된 사용자에 대한 쓰레드.
 * 
 * @author 임혜균, 김민경
 * @since 2020.11
 */
public class LoginUser extends Thread {
	/**
	 * 쓰레드 작업 계속 진행 여부를 저장한다.
	 */
	public boolean goOn = true;

	/**
	 * 접속한 사용자 ID를 저장한다.<br/>
	 * 로그인한 상태이면 사용자 ID를 저장한다.<br/>
	 * 로그인하지 않은 상태이면 null을 저장한다.
	 */
	public String userId = null;

	/**
	 * 접속한 사용자 이름을 저장한다.
	 */
	public String userName = null;
	
	public String turn = null;

	/**
	 * 사용자가 입장한 게임방을 연결한다.
	 */
	public GameRoom gameRoom = null;
	

	
	public String playerMarker=null;
	/**
	 * 사용자와 접속하고 있는 소켓
	 */
	private Socket connectionSocket = null;

	/**
	 * 소켓에서 입력을 받는 기능을 담당하는 객체
	 */
	private BufferedReader inStream = null;

	/**
	 * 소켓으로 출력하는 기능을 담당하는 객체
	 */
	private DataOutputStream outStream = null;

	/**
	 * 생성자
	 * 
	 * @param connectionSocket
	 *            사용자와 접속하고 있는 소켓
	 */
	public LoginUser(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	/**
	 * 쓰레드 실행
	 */
	public void run() {
		// 메시지 한 줄을 읽어서 저장할 객체
		String msgLine;

		// 한 줄 메시지를 해석하여 로그인 메시지인지 확인하는 객체
		LoginMessage loginMessage = new LoginMessage();

		// 한 줄 메시지를 해석하여 채팅 메시지인지 확인하는 객체
		ChatMessage chatMessage = new ChatMessage();

		// 한 줄 메시지를 해석하여 틱택토 메시지인지 확인하는 객체
		TicTacToeMessage ticTacToeMessage = new TicTacToeMessage();

		try {
			// 소켓에서 입력 받는 기능을 담당하는 객체를 생성한다.
			this.inStream = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// 소켓에 출력하는 기능을 담당하는 객체를 생성한다.
			this.outStream = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			goOn = false;
		}
		
		while (goOn) {
			try {
				// 접속한 사용자로부터 메시지를 받는다.
				msgLine = this.inStream.readLine();
				System.out.println(msgLine);

				if (loginMessage.setMessageString(msgLine)) {
					// 로그인 메시지이면 로그인한다.
					processLoginMessage(loginMessage);
				} else {
					// 로그인이 되어 있으면 게임이나 채팅을 한다.
					if (chatMessage.setMessageString(msgLine)) {
						// 채팅 메시지이면 채팅 큐에 저장한다.
						chatMessage.userName =userName;
						gameRoom.enqueueChatMessage(chatMessage);
					} else if (ticTacToeMessage.setMessageString(msgLine)) {
						// 틱택토 메시지이면 틱택토 메시지 큐에 저장한다.
						
						gameRoom.enqueueTicTacToeMessage(ticTacToeMessage);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();

				// 접속이 끊기면 게임을 종료한다.
				goOn = false;
				if (gameRoom != null) {
					gameRoom.closed(this);
				}
			}
		}
	}

	/**
	 * 로그인한 사용자에게 메시지를 전송한다.
	 * 
	 * @param msg
	 */
	public void sendMessage(Message msg) {
		try {
			String msgStr = msg.getMessageString();
			this.outStream.writeBytes(msgStr + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param msg
	 */
	private void processLoginMessage(LoginMessage msg) {
		if (msg.userName != null) {
			// 사용자 이름이 있으면 회원가입으로 처리한다.
			doRegister(msg);
			// 회원가입 결과 메시지를 전송한다.
			msg.userId = null;
			msg.userPassword = null;
			msg.userName = null;
			sendMessage(msg); // state 전송 (다시 로그인창으로 넘어감)

		} else {
			if (doLogin(msg)) {
				// 사용자에게 로그인 성공 메시지를 전송한다.
				userId=msg.userId;
				userName=msg.userName;
				msg.userPassword = null;
				sendMessage(msg); // userId, userName 전송
				
				// 게임방에 입장한다.
				GameRoom.enter(this);
			} else {
				// 사용자에게 로그인 실패 메시지를 전송한다.
				msg.reset();
				sendMessage(msg);
			}
		}
	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	private boolean doRegister(LoginMessage msg) {
		/* 회원가입 시작 */
		File file = new File("account.txt");
		BufferedReader reader = null;
		try {
			/* read account information, 즁복 체크 */
			reader = new BufferedReader(new FileReader(file));

			/* 파일이 이미 있으면 추가로 작성 */
			String s = null;
			List<String> sList = null;
			while ((s = reader.readLine()) != null) {
				sList = Arrays.asList(s.split(","));
				if (sList.get(0).toString().equals(msg.userId)) {
					reader.close();
					msg.state = LoginMessage.REGISTER_FAIL;
					return false;
				}
			}
			reader.close();
		} catch (IOException e) {
		}
		try {
			/* write account information */
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // append 
			writer.append(msg.userId + "," + msg.userPassword + "," + msg.userName + "\n");
			writer.close();
			msg.state = LoginMessage.REGISTER_SUCCESS;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			msg.state = LoginMessage.REGISTER_FAIL;
			System.out.println("회원가입 실패");
			return false;
		}
		/* 회원가입 끝 */

	}
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private boolean doLogin(LoginMessage msg) {
		// msg.userId, msg.userPassword 확인해서 일치하는지 확인한다.

		File file = new File("account.txt");
		try {
			/* read account information, 즁복 체크 */
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s = null;
			String[] sList = null;
			while ((s = reader.readLine()) != null) {
				sList = s.split(",");
				if (sList[0].equals(msg.userId)) {
					reader.close();
					if (sList[1].equals(msg.userPassword)) {
						// 사용자 정보를 msg에 저장한다.
						// 로그인을 성공하면 사용자명을 저장한다.
						msg.userName = sList[2];
						this.userName=msg.userName;
						msg.state = LoginMessage.LOGIN_SUCCESS;
						return true;
					} else {
						msg.state = LoginMessage.LOGIN_FAIL;
						return false;
					}
				}
			}
			reader.close();
			System.out.println("User Id is not exsist.");
			msg.state = LoginMessage.LOGIN_FAIL;
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			msg.state = LoginMessage.LOGIN_FAIL;
			return false;
		}
	}
}