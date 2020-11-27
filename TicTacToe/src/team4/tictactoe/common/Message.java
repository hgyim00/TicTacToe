package team4.tictactoe.common;

/**
 * 메시지 공통 속성 
 * 
 * @author 임혜균
 * @since 2020.11
 */
public interface Message {

	/**
	 * 객체에 저장된 내용을 모두 초기화한다.
	 */
	void reset();

	/**
	 * 객체에 저장된 내용으로 메시지로 전송하기 위한 문자열을 얻는다. 
	 * @return
	 */
	String getMessageString();

	/**
	 * 메시지로 전달된 문자열을 해석하여 객체에 내용을 저장한다.
	 * 객체가 해석할 수 없는 메시지 문자열이면 false를 리턴한다.
	 * 
	 * @param msg
	 * @return
	 */
	boolean setMessageString(String msg);
}
