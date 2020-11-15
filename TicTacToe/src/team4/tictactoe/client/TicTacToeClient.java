package team4.tictactoe.client;

import java.awt.BorderLayout;
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

	private LoginPanel loginPanel = null;
	private TicTacToePanel ticTacToePanel = null;
	private ChatPanel chatPanel = null;

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

	/**
	 * 
	 */
	public void create() {
		setLayout(new BorderLayout());

		// 화면 중앙에 윈도우 배치
		java.awt.Rectangle bound = getGraphicsConfiguration().getBounds();
		int w = 700;
		int h = 600;
		int x = (bound.x + bound.width / 2) - w / 2;
		int y = (bound.y + bound.height / 2) - h / 2;
		setBounds(x, y, w, h);

		// 로그인 패널 준비
		loginPanel = new LoginPanel();
		loginPanel.create(this);
		loginPanel.setVisible(false);

		// 틱텍토 게임 패털 준비
		ticTacToePanel = new TicTacToePanel();
		ticTacToePanel.create(this);
		ticTacToePanel.setVisible(false);

		// 채팅 패털 준비
		chatPanel = new ChatPanel();
		chatPanel.create(this);
		chatPanel.setVisible(false);

//		// 서버에 접속
//		try {
//			Socket socket = new Socket(hostName, port);
//			serverConnection = new ServerConnection(socket, this);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

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
	}

	/**
	 * 
	 */
	public void onCloseConnection() {
		userName = null;
		openLogin();
	}
}
