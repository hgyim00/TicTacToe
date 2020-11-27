package team4.tictactoe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import team4.tictactoe.common.LoginMessage;

/**
 * 
 * @author 신민철
 * @since 2020.11
 *
 */
public class LoginPanel {
	private static final long serialVersionUID = -9077614322650298497L;

	TicTacToeClient ticTacToeClient= null;
	
	public void create(TicTacToeClient parent) {
		ticTacToeClient = parent;

	}


}

