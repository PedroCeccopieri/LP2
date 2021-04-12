import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ProjetoApp {
    public static void main (String[] args) {
        ProjetoFrame frame = new ProjetoFrame();
        frame.setVisible(true);
    }
}

class ProjetoFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();

    private int mouseCordx,mouseCordy;
    private Figure selectedFigure;
    private Retangulo selectedRect;

    ProjetoFrame () {
        this.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
        });

        this.addKeyListener (new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        Retangulo r = new Retangulo(mouseCordx,mouseCordy,50,25);
                        figs.add(r);
                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Elipse(mouseCordx,mouseCordy,50,25));
                    }
                    repaint();
                }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent evt) {
                selectFigure(evt.getX(),evt.getY());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved (MouseEvent evt) {
                mouseCordx = evt.getX();
                mouseCordy = evt.getY();
            }

            public void mouseDragged(MouseEvent evt) {
                selectedFigure.drag(evt.getX(), evt.getY());
                repaint();
            }
        });

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    private void selectFigure(int x, int y) {

        if(selectedFigure instanceof Figure) {
            System.out.println("uma forma ja estava selecionada");
        }

        for (Figure fig: this.figs) {
            if (fig.getPosx() <= x && x <= fig.getPosx() + fig.getWidth() && fig.getPosy() <= y && y <= fig.getPosy() + fig.getHight()) { 
                selectedFigure = fig;
                System.out.println("uma forma foi selecionada");
            }
        }

        System.out.println(selectedFigure);
        //selectedRect = new Retangulo(selectedFigure.getPosx(),selectedFigure.getPosy(),selectedFigure.getWidth(),selectedFigure.getHight());
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}