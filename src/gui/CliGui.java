package gui;

import board.Board;
import board.Tile;
import board.Type;
public class CliGui {
    private Board board;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public CliGui(Board chessboard){
        this.board = chessboard;
    }


    public void drawBorders(){
        for(char k='A';k<'I';k++){
            if(k=='A'){
                System.err.print("\t"+k);
            }
            else{
                System.err.print(k);
            }
            System.err.print("\t");
        }
        System.err.println();
    }

    public void drawGUI(){
        
        this.drawBorders();
        for(int i = 7;i>=0;i--){
            System.err.print(i+1+"\t");
            for(int j=0;j<8;j++){
                Tile t = board.getTile(i, j);
                if(t.getPiece() == null){
                    System.err.print("_\t");
                }
                else{
                    if(t.getPiece().getPieceColor() == Type.WHITE){
                        System.err.print(ANSI_RED+t.getPiece().getPieceType()+ANSI_RESET);
                    }
                    else{
                        System.err.print(ANSI_YELLOW+t.getPiece().getPieceType()+ANSI_RESET);
                    }
                    System.err.print("\t");
                }
            }
            System.err.println();
            if(i==0){
                this.drawBorders();
            }
            else{
                System.err.println();
            }
            
        }
        
    }
}
