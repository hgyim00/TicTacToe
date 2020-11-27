package team4.tictactoe.common;

/**
 * 로그인 정보 송수신 메시지
 * 
 * 로그인 요청할 때 사용자 ID와 비밀번호를 저장한 메시지를 서버에 전송한다.
 * 회원가입을 요청할 때 사용자 ID, 비밀번호, 사용자 이름을 저장한 메시지를 서버에 전송한다.
 * 
 * 로그인을 성공하면 서버에서 사용자 ID와 사용자 이름을 저장한 메시지를 전송한다.
 * 로그인을 실패하면 서버에서 사용자 ID는 저장하고 사용자 이름은 저장하지 않은 메시지를 전송한다.
 * 
 * @author 김민경, 임혜균
 * @since 2020.11
 */
public class LoginMessage implements Message {
	
	private static final String MSG_NAME = "LoginMessage:";
	private static final String ATTR_USER_ID = "userId=";
	private static final String ATTR_USER_PASSWORD = "userPassword=";
	private static final String ATTR_USER_NAME = "userName=";
	private static final String ATTR_STATE = "state=";
	
	public static final int REGISTER_FAIL = 10;
	public static final int REGISTER_SUCCESS = 11;
	public static final int LOGIN_FAIL = 20;
	public static final int LOGIN_SUCCESS = 21;

	/**
	 * 로그인 또는 회원가입할 사용자 ID
	 */
	public String userId;

	/**
	 * 로그인 또는 회원가입할 사용자 비밀번호
	 */
	public String userPassword;

	/**
	 * 회원가입할 사용자 이름 또는 로그인한 사용자 이름.
	 */
	public String userName;
	
	/**
	 * 로그인, 회원가입 상태
	 */
	public int state;
	

	@Override
	public void reset() {
		userId = null;
		userPassword = null;
		userName = null;
		state = 0;
	}
	@Override
	public String getMessageString() {
		StringBuffer msgBuf = new StringBuffer();

		msgBuf.append(MSG_NAME);
		if (userId!=null && !userId.isEmpty()) {
			msgBuf.append(ATTR_USER_ID + userId + ",");
		}
		if (userPassword!=null && !userPassword.isEmpty()) {
			msgBuf.append(ATTR_USER_PASSWORD + userPassword + ",");
		}
		if (userName!=null && !userName.isEmpty()) {
			msgBuf.append(ATTR_USER_NAME + userName + ",");
		}
		if (state!=0) {
			msgBuf.append(ATTR_STATE + state + ",");
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
			if (msgItem.startsWith(ATTR_USER_PASSWORD)) {
				userPassword = msgItem.substring(ATTR_USER_PASSWORD.length());
			}
			if (msgItem.startsWith(ATTR_USER_NAME)) {
				userName = msgItem.substring(ATTR_USER_NAME.length());
			}
			if (msgItem.startsWith(ATTR_STATE)) {
				state = Integer.parseInt(msgItem.substring(ATTR_STATE.length()));
			}
		}

		return true;
	}

}
