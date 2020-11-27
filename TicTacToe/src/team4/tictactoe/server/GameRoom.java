package team4.tictactoe.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

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


	public int ticCount =0 ;
	
	public int chatCount =0 ;
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
	private List<ChatMessage> chatMessageQ = (List<ChatMessage>) Collections.synchronizedList(new LinkedList<ChatMessage>());

	/**
	 * 틱택토 게임 메시지 큐
	 */
	private LinkedList<TicTacToeMessage> ticTacToeMessageQ = new LinkedList<TicTacToeMessage>();

	/**
	 * 택택토 게임 기능을 담당하는 객체
	 */
	private static  TicTacToeGame ticTacToeGame=null;

	public TicTacToeMessage tempTic= null;
	
	public ChatMessage tempChat = null;
	/**
	 * 채팅 기능을 담당하는 객체
	 */
	private ChatAgent chatAgent;

	/**
	 * 입장 가능한 게임방
	 */
	public static GameRoom newGameRoom = null;

	/**
	 * 생성자
	 */
	public GameRoom() {
		// 틱택토 게임 진행 객체를 생성한다.
		// 채팅 대행 객체를 생성한다.
		ticTacToeGame = new TicTacToeGame(this);

		
		chatAgent = new ChatAgent(this);
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
			newGameRoom.player1.playerMarker="X";
			
			// 사용자에게 방을 배정한다.
			loginUser.gameRoom = newGameRoom;

			// 게임을 대기시킨다.
			newGameRoom.start();
		} else {
			// 개설된 방이 있으면 입장하는 사용자를 Player2로 지정한다.
			newGameRoom.player2 = loginUser;
			newGameRoom.player2.playerMarker="O";

			TicTacToeMessage play1 = new TicTacToeMessage();
			play1.playerMark="X";
			play1.player=newGameRoom.player1.userName;
			play1.opponent = newGameRoom.player2.userName;
			newGameRoom.player1.sendMessage(play1);
			
			TicTacToeMessage play2 = new TicTacToeMessage();
			play2.playerMark="O";
			play2.player=newGameRoom.player1.userName;
			play2.opponent = newGameRoom.player2.userName;
			newGameRoom.player2.sendMessage(play2);
			
			
			
			// 사용자에게 방을 배정한다.
			loginUser.gameRoom = newGameRoom;

			// 방에 입장이 완료되었으므로 다음에 사용자가 입장하면 방을 새로 개설하도록 한다.
			newGameRoom = null;
		}
	}

	/**
	 * 채팅 메시지를 큐에 저장한다.
	 * 
	 * @param msg
	 */
	public  void enqueueChatMessage(ChatMessage msg) {
		chatMessageQ.add(msg);
	}

	/**
	 * 채팅 메시지 큐에서 메시지를 한 개 빼온다.
	 * 
	 * @return
	 */
	public ChatMessage dequeueChatMessage() {
		if (chatMessageQ.isEmpty()) {
			return null;
		} else {
			
			ChatMessage rm = chatMessageQ.remove(0);
			return rm;
		
			
		}
	}

	/**
	 * 틱텍토 게임 메시지를 큐에 저장한다.
	 * 
	 * @param msg
	 */
	public synchronized void enqueueTicTacToeMessage(TicTacToeMessage msg) {
		//ticTacToeMessageQ.add(msg);
		tempTic =msg;
		ticCount++;
	}
	/**
	 * 틱텍토 게임 메시지 큐에서 메시지를 한 개 빼온다.
	 * 
	 * @return
	 */
	//public synchronized TicTacToeMessage dequeueTicTacToeMessage() {
	//
	//	TicTacToeMessage temp= new TicTacToeMessage();
	//	
	//	temp=ticTacToeMessageQ.get(count);
	//	
	//	count++;
		
	//	return temp;
	//}
	
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
				while (ticCount>0) {
			
					ticTacToeGame.processMessage(tempTic);
					tempTic=null;
					ticCount--;
				}
				
			
		}
	}
}
