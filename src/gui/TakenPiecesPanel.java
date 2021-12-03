package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import board.Type;
import pieces.Piece;
public class TakenPiecesPanel extends JPanel {
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private static final Color PANEL_COLOR = Color.decode("0xFDF5E6");
    private static final Dimension TAKEN_PIECES_PANEL_DIMENSION = new Dimension(40,80);
    private final JPanel northPanel;
    private final JPanel southPanel;
    private final ArrayList<Piece> whiteKilledPieces = new ArrayList<>();
    private final ArrayList<Piece> blackKilledPieces = new ArrayList<>();
    public TakenPiecesPanel(){
        super(new BorderLayout());
        setBackground(Color.decode("0xFDF5E6"));
        setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        add(this.northPanel,BorderLayout.NORTH);
        add(this.southPanel,BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_PANEL_DIMENSION);
    }
    public void setKilledPieces(Piece p){
        if(p!=null){
            if(p.getPieceColor()==Type.WHITE) whiteKilledPieces.add(p);
            else blackKilledPieces.add(p);
            this.renderPiece();
        }
    }
    public void renderPiece(){
        this.northPanel.removeAll();
        this.southPanel.removeAll();
        for(Piece p:this.whiteKilledPieces){
            try{
                String finalPath = ChessGUI.PIECE_PATH+"WHITE"+File.separator+p.getPieceType().toLowerCase()+".gif";
                BufferedImage image = ImageIO.read(new File(finalPath));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() - 15, icon.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.southPanel.add(imageLabel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } 

        for(Piece p:this.blackKilledPieces){
            try{
                String finalPath = ChessGUI.PIECE_PATH+"BLACK"+File.separator+p.getPieceType().toLowerCase()+".gif";
                BufferedImage image = ImageIO.read(new File(finalPath));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() - 15, icon.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.northPanel.add(imageLabel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
