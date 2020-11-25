package team4.tictactoe.game;

import team4.tictactoe.common.TicTacToeMessage;
import team4.tictactoe.server.GameRoom;

/**
 * 
 * 
 * @author 
 * @since 2020.11
 */
public class TicTacToeGame {

   /**
    * 게임방을 연결한다.
    */
   public GameRoom gameRoom = null;

   public TicTacToeGame(GameRoom newGameRoom) {
      gameRoom = newGameRoom;

   }

   
   static char[][] board = new char[3][3];
   //2차원 문자 배열을 이용하여서 보드를 나타낸다.
   

   TicTacToeGame() {
        for (int row = 0; row < 3; row++)  {
           for(int col = 0; col < 3; col++) {
              board[row][col]= ' ';
           }
        }         
   }//2차원 배열을 초기화한다.

   public static void setBoard(int i, int j, char playerMark) {
       board[i][j] = playerMark;
       
   }
   
   public char getBoard(int i, int j) {
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
      for (int row = 0; row < 3; row++)  {
         for(int col = 0; col < 3; col++) {
              if(board[row][col]== ' ') {
                 return false;
              }
           }
       }   
      return true;
   }//비겼는지 판단
   
   /**
    * 승패 결과를 양쪽 클라이언트들에게 결과를 넘겨줌
    * @param msg
    */
   public void processMessage(TicTacToeMessage msg) {
      
      char mark = msg.playerMark.charAt(0);
      TicTacToeGame.setBoard(Integer.parseInt(msg.row), Integer.parseInt(msg.column), mark);
      System.out.println(msg.row+msg.column);
      //사용자들의 정보를 보드판에 입력한다.
   // 게임 결과 보내기
      TicTacToeMessage newMsg = new TicTacToeMessage();
      newMsg.gameState = TicTacToeMessage.GAME_STATE_WAITING;
   
      newMsg.row=msg.row;
      newMsg.column=msg.row;
      System.out.println(msg.getMessageString());
       
      if (checkWin()==true) {

         if(msg.turn=="X") { // X플레이어가 이긴 상황
            
            newMsg.gameState = "winX";
            
             gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
             gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
         } 
         else { // O플레이어가 이긴 상황

            newMsg.gameState = "winO"; 
            
             gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
             gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
         }
      } 
      else if (checkTie()==true) { // 무승부인 상황

         newMsg.gameState = "tie"; 

          gameRoom.player1.sendMessage(newMsg); // Player 1에게 메시지 전송
          gameRoom.player2.sendMessage(newMsg); // Player 2에게 메시지 전송
      } 
      else { //승부가 나지 않은 상황에서는 다음 차례로 넘어간다.

         if(msg.turn == "X") {
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