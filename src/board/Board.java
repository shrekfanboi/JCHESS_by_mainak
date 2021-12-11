package board;
import java.util.ArrayList;
import java.util.List;
import pieces.*;
public class Board { 
    Tile[][] floor = new Tile[10][10];

    public Board(){
        this.resetBoard();
    }

    public void resetBoard(){
        
        this.floor[0][0] = new Tile(0,0,new Rook(0, 0, true, Type.WHITE));
        this.floor[0][1] = new Tile(0,1,new Knight(0, 1, true, Type.WHITE));
        this.floor[0][2] = new Tile(0,2,new Bishop(0, 2, true, Type.WHITE));
        this.floor[0][3] = new Tile(0,3,new King(0, 3, true, Type.WHITE));
        this.floor[0][4] = new Tile(0,4,new Queen(0, 4, true, Type.WHITE));
        this.floor[0][5] = new Tile(0,5,new Bishop(0, 5, true, Type.WHITE));
        this.floor[0][6] = new Tile(0,6,new Knight(0, 6, true, Type.WHITE));
        this.floor[0][7] = new Tile(0,7,new Rook(0, 7, true, Type.WHITE));
        this.placePawns(Type.WHITE);
        this.floor[7][0] = new Tile(7,0,new Rook(7, 0, true, Type.BLACK));
        this.floor[7][1] = new Tile(7,1,new Knight(7, 1, true, Type.BLACK));
        this.floor[7][2] = new Tile(7,2,new Bishop(7, 2, true, Type.BLACK));
        this.floor[7][3] = new Tile(7,3,new King(7, 3, true, Type.BLACK));
        this.floor[7][4] = new Tile(7,4,new Queen(7, 4, true, Type.BLACK));
        this.floor[7][5] = new Tile(7,5,new Bishop(7, 5, true, Type.BLACK));
        this.floor[7][6] = new Tile(7,6,new Knight(7, 6, true, Type.BLACK));
        this.floor[7][7] = new Tile(7,7,new Rook(7, 7, true, Type.BLACK));
        this.placePawns(Type.BLACK);
        this.placeEmptyTiles();

    }

    public void placePawns(Type color){
        for(int i=0;i<8;i++){
            if(color == Type.WHITE){
                this.floor[1][i] = new Tile(1,i,new Pawn(1, i, true, color));
            }
            else{
                this.floor[6][i] = new Tile(6,i,new Pawn(6, i, true, color));
            }
        }
    }

    public void placeEmptyTiles(){
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                this.floor[i][j] = new Tile(i, j, null);
            }
        }
    }

    public Tile getTile(int x,int y){
        if(x < 0 || x > 7 || y < 0 || y > 7){
            return null;
        }
        return this.floor[x][y];
    }


    public Tile getTile(int tileId){
        int x = tileId/8;
        int y = tileId%8;
        if(x < 0 || x > 7 || y < 0 || y > 7){
            throw new IndexOutOfBoundsException("Index out of Bound");
        }
        return this.floor[x][y];
    }

    public void setTile(Tile t,Piece p){
        floor[t.getX()][t.getY()].setPiece(p);
    }


    public List<Tile> getPieceTile(String piece,Type t){
        List<Tile> pieceTile = new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.getTile(i,j).getPiece()!=null && this.getTile(i, j).getPiece().getPieceType().equals(piece) && this.getTile(i, j).getPiece().getPieceColor()==t){
                    pieceTile.add(this.getTile(i, j));}
            }
        }
        return pieceTile;
    }

    public void displayBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.getTile(i, j).getPiece() != null){
                    Type color = this.getTile(i, j).getPiece().getPieceColor();
                    System.err.println("x="+i+" y="+j+"  "+this.getTile(i, j).getPiece().getPieceType()+" "+color);
                }
                else{
                    System.err.println("x="+i+" y="+j+"  __");
                }   
            }
        }
    }





}


