package team4.tictactoe.chat;

import team4.tictactoe.common.ChatMessage;
import team4.tictactoe.server.GameRoom;

/**
 * 
 * @author 안건하, 임혜균
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

		if (gameRoom.player1 != null && msg.userId.equals(gameRoom.player1.userId)) {
			if(gameRoom.player2!=null) {
				gameRoom.player2.sendMessage(msg);
			}
		}
		if (gameRoom.player2 != null && msg.userId.equals(gameRoom.player2.userId)) {
			if(gameRoom.player1!=null) {
				gameRoom.player1.sendMessage(msg);
			}
		}
	}
}
