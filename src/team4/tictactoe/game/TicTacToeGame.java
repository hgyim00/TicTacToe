package team4.tictactoe.game;

import team4.tictactoe.common.TicTacToeMessage;
import team4.tictactoe.server.GameRoom;
import team4.tictactoe.server.LoginUser;

/**
 * 
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToeGame {

   /**
    * 게임방을 연결한다.
    * 
    */
   public LoginUser player1 =null;
   public LoginUser player2 =null;

	
	
   public GameRoom gameRoom = null;

   public TicTacToeGame(GameRoom newGameRoom) {
      gameRoom = newGameRoom;
      player1=newGameRoom.player1;
      player2=newGameRoom.player2;
	  int gameCount = 0;
      for (int row = 0; row < 3; row++)  {
          for(int col = 0; col < 3; col++) {
             board[row][col]= gameCount;
             gameCount++;
          }
       }   

   }

   
   static int[][] board = new int[3][3];
   //2차원 문자 배열을 이용하여서 보드를 나타낸다.
   
   public  int count =0;
  
   
   
   
   public static void setBoard(int i, int j, String playerMark) {
      if(playerMark.equals("X")) {
    	  board[i][j] = 100;
      }
      else {
    	  board[i][j] = 200;
      }
	   
	   
	
   }
  
   
   public int getBoard(int i, int j) {
      return board[i][j];
     
   }
   
   public boolean checkWin() {
      
      boolean win=false;
      
      for(int i=0;i<3;i++){
         if(board[i][0]==board[i][1]&&board[i][1]==board[i][2]){
            win = true;
          }
      }
      
      for(int i=0;i<3;i++){
         if(board[0][i]==board[1][i]&&board[1][i]==board[2][i]){
            win = true;
            }
      }
      
      if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]){
         win=true;
      }

      if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]){
         win=true;
      }
         
      return win;
   }//이겼는지 판단
   
   public boolean checkTie() {
     if(count==9) {
      return true;
     }
     else {
    	 return false;
     }
   }//비겼는지 판단
   
   /**
    * 승패 결과를 양쪽 클라이언트들에게 결과를 넘겨줌
    * @param msg
    */
   public void processMessage(TicTacToeMessage msg) {

	   String row_ = msg.row;
	   String column_=msg.column;
	   String mark_=msg.playerMark;
	   String turn_ = msg.turn;
	   
      TicTacToeGame.setBoard(Integer.parseInt(row_), Integer.parseInt(column_), mark_);
      count++;
      System.out.println("Hello");
      System.out.println(row_);
      System.out.println(column_);
      System.out.println(mark_);
      System.out.println(turn_);

      //사용자들의 정보를 보드판에 입력한다.
   // 게임 결과 보내기
      TicTacToeMessage newMsg = new TicTacToeMessage();
      newMsg.gameState = TicTacToeMessage.GAME_STATE_WAITING;
      newMsg.playerMark=mark_;
      newMsg.row=row_;
      newMsg.column=column_;
      
      if (checkWin()==true) {

         if(turn_.equals("X")) { // X플레이어가 이긴 상황
            
        	 if(turn_!=null) {
                 if(turn_.equals("X")) {
                    newMsg.turn = "O";
                 }
                 else {
                    newMsg.turn = "X";
                 }
            newMsg.gameState = "winX";
            int gameCount=0;
            for (int row = 0; row < 3; row++)  {
                for(int col = 0; col < 3; col++) {
                   board[row][col]= gameCount;
                   gameCount++;
                }
             }   
            count=0;
            
             gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
             gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
        	 }
         } 
         else { // O플레이어가 이긴 상황

        	 if(turn_!=null) {
                 if(turn_.equals("X")) {
                    newMsg.turn = "O";
                 }
                 else {
                    newMsg.turn = "X";
                 }
            newMsg.gameState = "winO"; 
            
            int gameCount=0;
            for (int row = 0; row < 3; row++)  {
                for(int col = 0; col < 3; col++) {
                   board[row][col]= gameCount;
                   gameCount++;
                }
             }   
            count=0;

             gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
             gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
        	 }
         }
      } 
      else if (checkTie()==true) { // 무승부인 상황

    	  if(turn_!=null) {
    	         if(turn_.equals("X")) {
    	            newMsg.turn = "O";
    	         }
    	         else {
    	            newMsg.turn = "X";
    	         }
         newMsg.gameState = "tie"; 
         
         int gameCount=0;
         for (int row = 0; row < 3; row++)  {
             for(int col = 0; col < 3; col++) {
                board[row][col]= gameCount;
                gameCount++;
             }
          }   
         count=0;


          gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
          gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
    	  }
      } 
      else { //승부가 나지 않은 상황에서는 다음 차례로 넘어간다.
    	  if(turn_!=null) {
         if(turn_.equals("X")) {
            newMsg.turn = "O";
         }
         else {
            newMsg.turn = "X";
         }
          gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
          gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
      }
      }
       

      

   }


   
}