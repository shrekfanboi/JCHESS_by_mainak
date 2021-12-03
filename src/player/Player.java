package player;
import java.util.ArrayList;
import java.util.List;

import board.Move;
import board.Type;

public class Player {
    Type side;
    boolean humanPlayer;
    String name;
    protected List<Move> moves = new ArrayList<>();
    public Player(Type side, boolean humanPlayer, String name) {
        this.side = side;
        this.humanPlayer = humanPlayer;
        this.name = name;
    }

    public void registerMove(Move m){
        moves.add(m);
    }
    
    public void getMoves(){
        System.err.println(moves);
    } 
    public String getName(){
        return this.name;
    }

    public Type getSide(){
        return this.side;
    }
}

