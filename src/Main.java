import game.*;
import board.*;
public class Main {


    public static void main(String[] args){
        Board chessboard = new Board();
        Game chessGame = new Game("Player 1", "Player 2",chessboard);
        chessGame.playerMove(0, 2, 2, 4);
        chessGame.playerMove(7, 2, 5, 4);
    }
    
}
