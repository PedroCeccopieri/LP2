import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Elipse> rs = new ArrayList<Elipse>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        rs.add(new Elipse(x,y, w,h));
                        repaint();  // outer.repaint()
                    }
                }
            }
        );

        this.setTitle("Lista de Elipses");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Elipse e: this.rs) {
            e.paint(g);
        }
    }
}

class Elipse {

    int posx, posy;
    int largura, altura;

    public Elipse(int posx, int posy, int largura, int altura) {

        this.posx = posx;
        this.posy = posy;
        this.largura = largura;
        this.altura = altura;
        
    }

    public void print () {
        System.out.format("Essa elipse tem largura %d e altura %d, esta na posicao (%d,%d)\n", this.largura, this.altura, this.posx, this.posy);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.posx, this.posy, this.largura, this.altura));
    }
}
