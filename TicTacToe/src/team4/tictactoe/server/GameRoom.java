package team4.tictactoe.server;

import java.util.LinkedList;

import team4.tictactoe.chat.ChatAgent;
import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.common.TicTacToeMessage;
import team4.tictactoe.game.TicTacToeGame;

/**
 * 
 * 
 * @author 임혜균
 * @since 2020.11
 */
public class GameRoom extends Thread {
	/**
	 * 쓰레드 작업 계속 진행 여부를 저장한다.
	 */
	public boolean goOn = true;

	/**
	 * Player1
	 */
	public LoginUser player1 = null;

	/**
	 * Player2
	 */
	public LoginUser player2 = null;

	/**
	 * 채팅 메시지 큐
	 */
	private LinkedList<ChatMessage> chatMessageQ = new LinkedList<ChatMessage>();

	/**
	 * 틱택토 게임 메시지 큐
	 */
	private LinkedList<TicTacToeMessage> ticTacToeMessageQ = new LinkedList<TicTacToeMessage>();

	/**
	 * 택택토 게임 기능을 담당하는 객체
	 */
	private TicTacToeGame ticTacToeGame;

	/**
	 * 채팅 기능을 담당하는 객체
	 */
	private ChatAgent chatAgent;

	/**
	 * 입장 가능한 게임방
	 */
	private static GameRoom newGameRoom = null;

	/**
	 * 생성자
	 */
	public GameRoom() {
		// 틱택토 게임 진행 객체를 생성한다.
		ticTacToeGame = new TicTacToeGame(newGameRoom);

		// 채팅 대행 객체를 생성한다.
		chatAgent = new ChatAgent(newGameRoom);
	}

	/**
	 * 방을 개설하거나 개설된 방에 입장한다.
	 * 
	 * @param loginUser
	 */
	public static void enter(LoginUser loginUser) {
		if (newGameRoom == null) {
			// 개설된 방이 없으면 새로 개설한다.
			newGameRoom = new GameRoom();

			// 새로 개설된 방이면 입장하는 사용자를 Playe1으로 지정한다.
			newGameRoom.player1 = loginUser;

			// 사용자에게 방을 배정한다.
			loginUser.gameRoom = newGameRoom;
			
			// 사용자에게 o,x를 알린다
		       TicTacToeMessage gameStartMsg = new TicTacToeMessage();
		       gameStartMsg.playerMark = "O";
		       gameStartMsg.turn = "O";

			// 게임을 대기시킨다.
			newGameRoom.start();
		} else {
			// 개설된 방이 있으면 입장하는 사용자를 Player2로 지정한다.
			newGameRoom.player2 = loginUser;

			// 사용자에게 방을 배정한다.
			loginUser.gameRoom = newGameRoom;
			
			// 사용자에게 o,x를 알린다
		       TicTacToeMessage gameStartMsg = new TicTacToeMessage();
		       gameStartMsg.playerMark = "X";
		       gameStartMsg.turn = "O";

			// 방에 입장이 완료되었으므로 다음에 사용자가 입장하면 방을 새로 개설하도록 한다.
			newGameRoom = null;
		}
	}

	/**
	 * 채팅 메시지를 큐에 저장한다.
	 * 
	 * @param msg
	 */
	public synchronized void enqueueChatMessage(ChatMessage msg) {
		chatMessageQ.add(msg);
	}

	/**
	 * 채팅 메시지 큐에서 메시지를 한 개 빼온다.
	 * 
	 * @return
	 */
	public synchronized ChatMessage dequeueChatMessage() {
		if (chatMessageQ.isEmpty()) {
			return null;
		} else {
			return chatMessageQ.removeFirst();
		}
	}

	/**
	 * 틱텍토 게임 메시지를 큐에 저장한다.
	 * 
	 * @param msg
	 */
	public synchronized void enqueueTicTacToeMessage(TicTacToeMessage msg) {
		ticTacToeMessageQ.add(msg);
	}

	/**
	 * 틱텍토 게임 메시지 큐에서 메시지를 한 개 빼온다.
	 * 
	 * @return
	 */
	public synchronized TicTacToeMessage dequeueTicTacToeMessage() {
		if (ticTacToeMessageQ.isEmpty()) {
			return null;
		} else {
			return ticTacToeMessageQ.removeFirst();
		}
	}
	
	/**
	 * 사용자 접속이 끊긴 경우 실행된다. 다른 사용자에게 접속이 끊긴 것을 알리고 게임을 종료한다.
	 *  
	 * @param loginUser
	 */
	public void closed(LoginUser loginUser) {
		if (loginUser==player1) {
			player1 = null;
		} else if (loginUser==player2){
			player2 = null;
		}
		
		if (player1 == null && player2 == null) {
			goOn = false;
		}
	}

	/**
	 * 
	 */
	public void run() {
		while (goOn) {
			// 채팅 메시지를 먼저 처리한다.
			while (!chatMessageQ.isEmpty()) {
				chatAgent.processMessage(dequeueChatMessage());
			}
		
			// 틱텍토 게임 메시지를 처리한다.
			while (!ticTacToeMessageQ.isEmpty()) {
				ticTacToeGame.processMessage(dequeueTicTacToeMessage());
			}
		}
	}
}
