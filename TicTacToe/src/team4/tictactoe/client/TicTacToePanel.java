package team4.tictactoe.client;

import java.awt.Panel;

import team4.tictactoe.common.TicTacToeMessage;

/**
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToePanel extends Panel {
	private static final long serialVersionUID = 8800342916813796346L;

	TicTacToeClient ticTacTocClient = null;

	public void create(TicTacToeClient parent) {
		ticTacTocClient = parent;

	}

	public void processMessage(TicTacToeMessage msg) {
		// TODO Auto-generated method stub
		
	}
}
