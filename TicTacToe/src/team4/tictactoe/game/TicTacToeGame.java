package team4.tictactoe.game;

import team4.tictactoe.common.TicTacToeMessage;
import team4.tictactoe.server.GameRoom;

/**
 * 
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToeGame {

	/**
	 * 게임방을 연결한다.
	 */
	public GameRoom gameRoom = null;

	public TicTacToeGame(GameRoom newGameRoom) {
		gameRoom = newGameRoom;
	}

	public void processMessage(TicTacToeMessage msg) {
		// TODO Auto-generated method stub

	}

}
