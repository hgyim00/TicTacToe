package team4.tictactoe.common;

/**
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToeMessage implements Message {
	
	private static final String MSG_NAME = "TicTacToeMessage:";
	private static final String ATTR_GAME_STATE = "gameState=";

	public static final int GAME_STATE_UNDEFINED = -1;
	public static final int GAME_STATE_WAITING = 0;
	
	/**
	 * 게임 진행 상태
	 * -1 : 미지정
	 * 0 : 대기중
	 * 1 : Player 1 승
	 * 2 : Player 2 승
	 * 3 : 비김
	 * 4 : 진행중
	 */
	public int gameState;
	
	@Override
	public void reset() {
		gameState = GAME_STATE_UNDEFINED;
		
		//TODO 멤버변수 추가되면 해당 멤버변수 초기화
	}

	@Override
	public String getMessageString() {
		StringBuffer msgBuf = new StringBuffer();

		msgBuf.append(MSG_NAME);
		if (gameState!=-1) {
			msgBuf.append(ATTR_GAME_STATE + gameState + ",");
		}
		
		// TODO 멤버변수 추가되면 해당 멤버변수를 메시지에 추가
		
		return null;
	}

	@Override
	public boolean setMessageString(String msg) {
		if (!msg.startsWith(MSG_NAME)) {
			return false;
		}

		reset();
		String msgItems[] = msg.substring(MSG_NAME.length()).split(",");
		String msgItem;
		int idx;
		for (idx=0; idx<msgItems.length; idx++) {
			msgItem = msgItems[idx];
			if (msgItem.startsWith(ATTR_GAME_STATE)) {
				gameState = Integer.parseInt(msgItem.substring(ATTR_GAME_STATE.length()));
			}
			

			// TODO 멤버변수 추가되면 해당 멤버변수를 메시지에서 읽도록 if문 추가
		}

		return true;
	}
}
