package team4.tictactoe.common;

/**
 * 
 * @author 
 * @since 2020.11
 *
 */
public class ChatMessage implements Message {
	private static final String MSG_NAME = "ChatMessage:";
	private static final String ATTR_USER_ID = "userId=";
	private static final String ATTR_USER_NAME = "userName=";
	private static final String ATTR_CHAT_TEXT = "chatText=";

	public String userId;
	public String userName;
	public String chatText;
	@Override
	public void reset() {
		userId=null;
		userName=null;
		chatText=null;
		
	}

	@Override
	public String getMessageString() {
		StringBuffer msgBuf = new StringBuffer();

		msgBuf.append(MSG_NAME);
		if (userId!=null && !userId.isEmpty()) {
			msgBuf.append(ATTR_USER_ID + userId + ",");
		}
		if (userName!=null && !userId.isEmpty()) {
			msgBuf.append(ATTR_USER_NAME + userName + ",");
		}
		if (chatText!=null && !chatText.isEmpty()) {
			msgBuf.append(ATTR_CHAT_TEXT + chatText + ",");
		}

		return msgBuf.toString();
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
			if (msgItem.startsWith(ATTR_USER_ID)) {
				userId = msgItem.substring(ATTR_USER_ID.length());
			}
			if (msgItem.startsWith(ATTR_USER_NAME)) {
				userName = msgItem.substring(ATTR_USER_NAME.length());
			}
			if (msgItem.startsWith(ATTR_CHAT_TEXT)) {
				chatText = msgItem.substring(ATTR_CHAT_TEXT.length());
			}
		}

		return true;
	}
}
