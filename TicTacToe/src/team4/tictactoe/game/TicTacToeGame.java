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
		// TODO 게임 처리
		
		
		// 게임 결과 보내기
		TicTacToeMessage newMsg = new TicTacToeMessage();
		newMsg.gameState = TicTacToeMessage.GAME_STATE_WAITING;
		//TODO 추가 멤버변수 값 설정
		gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
		gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
	}
}
