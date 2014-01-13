
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class board extends JPanel{
public void paintComponent(Graphics g){
    super.paintComponent(g);
     Graphics2D g2 = (Graphics2D) g;
    Rectangle2D rec = new Rectangle2D.Double(0,0,530,530);
    g2.setColor(Color.PINK);
    g2.fill(rec);
    
}    
}
