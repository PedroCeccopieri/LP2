import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import figures.*;
import ivisible.*;

class ProjetoApp {
    public static void main (String[] args) {
        ProjetoFrame frame = new ProjetoFrame();
        frame.setVisible(true);
    }
}

class ProjetoFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    private Point mouseCord;
    private Point mouseClick;
    private Figure selectedFigure;
    private Retangulo selectedRect;

    private boolean background = true;
    private boolean stroke = false;
    private boolean strokeN = false;
    private boolean r = false, g = false, b = false;

    private boolean bresize = false;

    ProjetoFrame () {

        try {
            FileInputStream f = new FileInputStream("projeto.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception err) {
            System.out.println("projeto.bin não existe!");
        }

        this.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {

                    try {
                        FileOutputStream f = new FileOutputStream("projeto.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception err) {
                        System.out.println("não foi possivel salvar em projeto.bin");
                    }

                    System.exit(0);
                }
        });

        this.addKeyListener (new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    
                    if (evt.getKeyChar() == '1') {
                        r = true;
                        g = false;
                        b = false;
                        System.out.println("vermelho selecionado");

                    } else if (evt.getKeyChar() == '2') {
                        r = false;
                        g = true;
                        b = false;
                        System.out.println("verde selecionado");

                    } else if (evt.getKeyChar() == '3') {
                        r = false;
                        g = false;
                        b = true;
                        System.out.println("azul selecionado");

                    } else if (evt.getKeyChar() == '4') {

                        if(background || strokeN || bresize) {
                            background = false;
                            stroke = true;
                            strokeN = false;
                            bresize = false;
                            System.out.println("stroke selecionado");
                        } else if(stroke) {
                            background = true;
                            stroke = false;
                            bresize = false;
                            System.out.println("background selecionado");
                        }

                    } else if (evt.getKeyChar() == '5') {
                        strokeN = true;
                        background = false;
                        stroke = false;
                        bresize = false;

                    } else if (evt.getKeyChar() == 'q') {

                        if (background || strokeN || stroke) {
                            System.out.println("resize selecionado");
                            bresize = true;
                            strokeN = false;
                            background = false;
                            stroke = false;
                        }

                    } else if (evt.getKeyCode() == 38) { // seta pra cima //
                        if (background) {
                            if (r) selectedFigure.changeBackgroundColor(1,0,0);
                            if (g) selectedFigure.changeBackgroundColor(0,1,0);
                            if (b) selectedFigure.changeBackgroundColor(0,0,1);
                        }

                        if (stroke) {
                            if (r) selectedFigure.changeStrokeColor(1,0,0);
                            if (g) selectedFigure.changeStrokeColor(0,1,0);
                            if (b) selectedFigure.changeStrokeColor(0,0,1);
                        }

                        if (strokeN) selectedFigure.changeStroke(1);

                        if (bresize) selectedFigure.resize(0,-1);

                    } else if (evt.getKeyCode() == 40) { // seta pra baixo //
                        if (background) {
                            if (r) selectedFigure.changeBackgroundColor(-1,0,0);
                            if (g) selectedFigure.changeBackgroundColor(0,-1,0);
                            if (b) selectedFigure.changeBackgroundColor(0,0,-1);
                        }

                        if (stroke) {
                            if (r) selectedFigure.changeStrokeColor(-1,0,0);
                            if (g) selectedFigure.changeStrokeColor(0,-1,0);
                            if (b) selectedFigure.changeStrokeColor(0,0,-1);
                        }

                        if (strokeN) selectedFigure.changeStroke(-1);

                        if (bresize) selectedFigure.resize(0,1);

                    } else if (evt.getKeyCode() == 37) {
                        if (bresize) selectedFigure.resize(-1,0);
                    } else if (evt.getKeyCode() == 39) {
                        if (bresize) selectedFigure.resize(1,0);
                    }

                    if (selectedFigure != null) {
                        selectedRect = new Retangulo(selectedFigure.getPosx()-5,selectedFigure.getPosy()-5,selectedFigure.getWidth()+10,selectedFigure.getHight()+10,2,null,new int[] {255,0,0});
                        
                    }

                    repaint();
                }

                public void keyReleased(KeyEvent evt) {

                    int r1 = rand.nextInt(255);
                    int g1 = rand.nextInt(255);
                    int b1 = rand.nextInt(255);
                    int r2 = rand.nextInt(255);
                    int g2 = rand.nextInt(255);
                    int b2 = rand.nextInt(255);

                    if (evt.getKeyChar() == 'r') {
                        Retangulo r = new Retangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2});
                        figs.add(r);

                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Elipse((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyChar() == 't') {
                        figs.add(new Triangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyChar() == 'y') {
                        figs.add(new Line((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyCode() == 127) { // del //
                        figs.remove(selectedFigure);
                        selectedFigure = null;
                        selectedRect = new Retangulo(0,0,0,0,0,null,new int[] {0,0,0});
                    } repaint();
                }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent evt) {
                mouseClick = evt.getPoint();
                selectFigure((int) mouseClick.getX(),(int) mouseClick.getY());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved (MouseEvent evt) {
                mouseCord = evt.getPoint();
            }

            public void mouseDragged(MouseEvent evt) {

                if (selectedFigure instanceof Figure) {
                    selectedFigure.drag((int) (evt.getX() - mouseClick.getX()), (int) (evt.getY() - mouseClick.getY()));
                    mouseClick = evt.getPoint();
                    selectedRect = new Retangulo(selectedFigure.getPosx()-5,selectedFigure.getPosy()-5,selectedFigure.getWidth()+10,selectedFigure.getHight()+10,2,null,new int[] {255,0,0}); 
                } repaint();
            }
        });

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    private void selectFigure(int x, int y) {

        if (!figs.isEmpty()) {

            selectedFigure = null;

            for (Figure fig: this.figs) {
                if (fig.clicked(x,y)) {
                    selectedFigure = fig;
                }
            }

            if (selectedFigure != null) {
                System.out.println("uma forma foi selecionada");

                System.out.println(selectedFigure);
                figs.remove(selectedFigure);
                figs.add(selectedFigure);
                selectedRect = new Retangulo(selectedFigure.getPosx()-5,selectedFigure.getPosy()-5,selectedFigure.getWidth()+10,selectedFigure.getHight()+10,2,null,new int[] {255,0,0});
                
            } else {
                selectedRect = new Retangulo(0,0,0,0,0,null,new int[] {0,0,0});
            } 
        } repaint();
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (selectedRect != null) selectedRect.paint(g);
    }
}