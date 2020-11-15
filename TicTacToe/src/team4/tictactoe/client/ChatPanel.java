package team4.tictactoe.client;

import java.awt.Panel;

/**
 * 
 * @author 
 * @since 2020.11
 *
 */
public class ChatPanel extends Panel {
	private static final long serialVersionUID = 3752014897574692475L;

	TicTacToeClient ticTacTocClient = null;

	public void create(TicTacToeClient parent) {
		ticTacTocClient = parent;

	}
}
