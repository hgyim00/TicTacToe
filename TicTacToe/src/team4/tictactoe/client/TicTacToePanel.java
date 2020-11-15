package team4.tictactoe.client;

import java.awt.Panel;

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
}
