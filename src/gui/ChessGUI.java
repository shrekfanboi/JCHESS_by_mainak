package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import board.Board;
import board.Tile;
import game.Game;
import pieces.Piece;

public class ChessGUI {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Tile source;
    private Tile destination;
    private Game chessGame;
    private static final Board chessboard = new Board();
    private final TakenPiecesPanel takenPiecesPanel;
    private static final Dimension GAME_FRAME_DIMENSION = new Dimension(600,600);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");
    public static final String PIECE_PATH = System.getProperty("user.dir").concat("\\src\\assets\\");
    public ChessGUI(){
        this.gameFrame = new JFrame("JChess");
        this.gameFrame.setSize(GAME_FRAME_DIMENSION);
        this.gameFrame.setLayout(new BorderLayout());
        JMenuBar tableMenuBar = new JMenuBar();
        this.populateMenuBar(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.boardPanel = new BoardPanel();
        this.takenPiecesPanel = new TakenPiecesPanel();
        this.gameFrame.add(boardPanel,BorderLayout.CENTER);
        this.gameFrame.add(this.takenPiecesPanel, BorderLayout.WEST);
        this.gameFrame.setVisible(true);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.chessGame = new Game("whitePlayer","blackPlayer",chessboard);
    }
    
    private void populateMenuBar(JMenuBar tableMenuBar){
        tableMenuBar.add(this.createFileMenu()) ;
        tableMenuBar.add(new JMenu("Preferences"));
        tableMenuBar.add(new JMenu("Options"));
    }

    private class BoardPanel extends JPanel{
        private ArrayList<TilePanel> boardTile;
        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTile = new ArrayList<>();
            for(int i=63;i>=0;i--){
                final TilePanel tilePanel = new TilePanel(i);
                this.boardTile.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        void drawBoard(Board chessBoard){
            for(TilePanel tilePanel:boardTile){
                tilePanel.drawTile(chessBoard);
            }
            this.
            validate();
        }

        void resetBorder(){
            for(TilePanel t:boardPanel.boardTile){
                t.setBorder(null);
            }
        }
    }

    private class TilePanel extends JPanel{
        private int tileId;
        TilePanel(int tileId){
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignTilePiece(chessboard);
            validate();
            addMouseListener(new MouseListener(){


                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    boardPanel.resetBorder();

                    if(e.getButton() == MouseEvent.BUTTON1){
                        //First Click
                        if(source==null){
                            source = chessboard.getTile(tileId);
                            if(source.getPiece()==null){source = null;}
                            else{
                                if(source.getPiece().getPieceColor().equals(chessGame.getCurrentTurn().getSide())){
                                    setBorder(new LineBorder(Color.BLUE,3));
                                }
                                else source = null; 
                            }
                        }
                        else{
                            //Subsequent Clicks
                            destination = chessboard.getTile(tileId);
                            if(destination.getPiece()!=null && destination.getPiece().getPieceColor() == source.getPiece().getPieceColor()){
                                source = destination;
                                destination = null;
                                setBorder(new LineBorder(Color.BLUE,3));
                            }
                            else {
                                chessGame.playerMove(source, destination);
                                takenPiecesPanel.setKilledPieces(chessGame.getPieceKilled());
                                chessGame.setPieceKilled(null);
                                source = destination = null;
                            }
                        }
                        /*
                        if(source!=null) System.out.print("Source:"+source.getPiece().getPieceType()+" ");
                        else System.out.print("Source:null");
                        if(destination!=null) System.out.println("Dest:"+destination.getPiece().getPieceType()+" ");
                        else System.out.print("Dest:null");
                        System.out.println();
                        */
                        boardPanel.drawBoard(chessboard);
                    }
                    
                }


                @Override
                public void mousePressed(MouseEvent e) {
                    // do not need this
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // do not need this
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // do not need this
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                  // do not need this
                }
                
            });
        }

        private void assignTilePiece(Board board){
            this.removeAll();
            Piece p = board.getTile(this.tileId).getPiece();
            if(p!=null){
                try{
                    String finalPath = PIECE_PATH+p.getPieceColor().toString()+File.separator+p.getPieceType().toLowerCase()+".gif";
                    BufferedImage image = ImageIO.read(new File(finalPath));
                    add(new JLabel(new ImageIcon(image)));
                }
                catch(Exception e){
                    System.err.println(e);
                }
            }
        }

        private void assignTileColor(){
            boolean isLight = ((tileId + tileId / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
        }

        public void drawTile(Board board){
            this.assignTileColor();
            this.assignTilePiece(board);
            validate();
        }
    }


    private JMenu createFileMenu(){
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitOption = new JMenuItem("Exit");
        JMenuItem newOption = new JMenuItem("New");
        JMenuItem openOption = new JMenuItem("Open");
        newOption.addActionListener(e->System.out.print("Create New "));
        openOption.addActionListener(e->System.out.print("Open Existing "));
        exitOption.addActionListener(e->System.exit(0));
        fileMenu.add(newOption);
        fileMenu.add(openOption);
        fileMenu.add(exitOption);
        return fileMenu;
    }

    public static void main(String[] args){
        new ChessGUI();
    }

}
