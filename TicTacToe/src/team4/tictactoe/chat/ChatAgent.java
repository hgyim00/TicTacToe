package team4.tictactoe.chat;

import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.server.GameRoom;

/**
 * 
 * @author
 * @since 2020.11
 *
 */
public class ChatAgent {

	/**
	 * 게임방을 연결한다.
	 */
	public GameRoom gameRoom = null;

	public ChatAgent(GameRoom newGameRoom) {
		gameRoom = newGameRoom;
	}

	public void processMessage(ChatMessage msg) {
		// TODO Auto-generated method stub

	}
}
