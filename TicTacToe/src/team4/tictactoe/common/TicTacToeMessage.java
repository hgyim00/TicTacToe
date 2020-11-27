package team4.tictactoe.common;

/**
 * TicTacToe 게임 정보 송수신 메시지
 * 
 * 클라이언트가 자신의 클릭한 버튼의 행과 열, X 또는 O 표시를 서버에 전송한다.
 * 서버는 현재 게임판을 보고 승패 여부를 판단하여 클라이언트에게 전송한다.
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToeMessage implements Message {

   private static final String MSG_NAME = "TicTacToeMessage:";
   private static final String ATTR_USER_MARK = "playerMark=";
   private static final String ATTR_USER_ROW = "row=";
   private static final String ATTR_USER_COLUMN = "column=";
   private static final String ATTR_USER_TURN = "turn=";
   private static final String ATTR_USER_PLAYER = "player=";
   private static final String ATTR_USER_OPPONENT = "opponent=";
   public static final String GAME_STATE_WAITING = "gameState=";
   /**
    * O 또는 X.
    */
   public String playerMark;

   public String player;
   
   public String opponent;
   /**
    * 보드판에 표시해야 할 행.
    */
   public String row;

   /**
    * 보드판에 표시해야 할 열.
    */
   public String column;
   
   /**
    * 게임 순서를 알려준다.(O/X)
    */
   public String turn;
   
   /**
    * 게임 상황를 알려준다.(winO/winX/Tie)
    */
   public String gameState;

   @Override
   public void reset() {
      playerMark = null;
      row = null;
      column = null;
      turn = null;
      gameState = null;
      player = null;
      opponent = null;
   }
   
   @Override
   public String getMessageString() {
      StringBuffer msgBuf = new StringBuffer();

      msgBuf.append(MSG_NAME);
      if (playerMark!=null && !playerMark.isEmpty()) {
         msgBuf.append(ATTR_USER_MARK + playerMark + ",");
      }
      if (row!=null && !row.isEmpty()) {
         msgBuf.append(ATTR_USER_ROW + row + ",");
      }
      if (column!=null && !column.isEmpty()) {
         msgBuf.append(ATTR_USER_COLUMN + column + ",");
      }
      if (turn!=null && !turn.isEmpty()) {
         msgBuf.append(ATTR_USER_TURN + turn + ",");
      }
      if (gameState!=null && !gameState.isEmpty()) {
         msgBuf.append(GAME_STATE_WAITING + gameState + ",");
      }
      if(player!=null && !player.isEmpty()) {
    	  msgBuf.append(ATTR_USER_PLAYER + player + ",");
      }
      if(opponent!=null && !opponent.isEmpty()) {
    	  msgBuf.append(ATTR_USER_OPPONENT + opponent + ",");
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
         if (msgItem.startsWith(ATTR_USER_MARK)) {
            playerMark = msgItem.substring(ATTR_USER_MARK.length());
         }
         if (msgItem.startsWith(ATTR_USER_ROW)) {
            row = msgItem.substring(ATTR_USER_ROW.length());
         }
         if (msgItem.startsWith(ATTR_USER_COLUMN)) {
            column = msgItem.substring(ATTR_USER_COLUMN.length());
         }
         if (msgItem.startsWith(ATTR_USER_TURN)) {
            turn = msgItem.substring(ATTR_USER_TURN.length());
         }
         if (msgItem.startsWith(GAME_STATE_WAITING)) {
            gameState = msgItem.substring(GAME_STATE_WAITING.length());
         } 
         if (msgItem.startsWith(ATTR_USER_PLAYER)) {
             player = msgItem.substring(ATTR_USER_PLAYER.length());
         }
         if (msgItem.startsWith(ATTR_USER_OPPONENT)) {
             opponent = msgItem.substring(ATTR_USER_OPPONENT.length());
         }
      }

      return true;
   }
}

