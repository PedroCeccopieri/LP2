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
    ArrayList<Figure> points = new ArrayList<Figure>();
    Random rand = new Random();

    private int mouseCordx,mouseCordy;
    private int mouseClickx, mouseClicky;
    private Figure selectedFigure;
    private Retangulo selectedRect;

    private boolean background = true;
    private boolean stroke = false;
    private boolean strokeN = false;
    private boolean r = false, g = false, b = false;

    ProjetoFrame () {

        createPoints();

        this.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
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

                        if(background || strokeN) {
                            background = false;
                            stroke = true;
                            strokeN = false;
                            System.out.println("stroke selecionado");
                        } else if(stroke) {
                            background = true;
                            stroke = false;
                            System.out.println("background selecionado");
                        }

                    } else if (evt.getKeyChar() == '5') {
                        strokeN = true;
                        background = false;
                        stroke = false;

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
                    } repaint();

                }

                public void keyReleased(KeyEvent evt) {

                    int r1 = rand.nextInt(255);
                    int g1 = rand.nextInt(255);
                    int b1 = rand.nextInt(255);
                    int r2 = rand.nextInt(255);
                    int g2 = rand.nextInt(255);
                    int b2 = rand.nextInt(255);

                    if (evt.getKeyChar() == 'r') {
                        Retangulo r = new Retangulo(mouseCordx,mouseCordy,50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2});
                        figs.add(r);

                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Elipse(mouseCordx,mouseCordy,50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyCode() == 127) { // del //
                        figs.remove(selectedFigure);
                        selectedFigure = null;
                        selectedRect = new Retangulo(0,0,0,0,0,null,new int[] {0,0,0});
                        plotPoints(false);
                    } repaint();
                }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent evt) {
                mouseClickx = evt.getX();
                mouseClicky = evt.getY();
                selectFigure(mouseClickx,mouseClicky);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved (MouseEvent evt) {
                mouseCordx = evt.getX();
                mouseCordy = evt.getY();
            }

            public void mouseDragged(MouseEvent evt) {

                /*if (isPoint(mouseClickx, mouseClicky)) {
                    selectedFigure.resize(evt.getX(), evt.getY(),whichPoint(evt.getX(), evt.getY()));
                }*/
                
                if (selectedFigure instanceof Figure) {
                    selectedFigure.drag(evt.getX(), evt.getY());
                    selectedRect = new Retangulo(selectedFigure.getPosx()-5,selectedFigure.getPosy()-5,selectedFigure.getWidth()+10,selectedFigure.getHight()+10,2,null,new int[] {255,0,0});
                    plotPoints(true);
                }

                repaint();
            }
        });

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    private void selectFigure(int x, int y) {

        if (isPoint(x,y)) {
            return;
        }

        if (!figs.isEmpty()) {

            selectedFigure = null;

            for (Figure fig: this.figs) {
                if (fig.getPosx() <= x && x <= fig.getPosx() + fig.getWidth() && fig.getPosy() <= y && y <= fig.getPosy() + fig.getHight()) { 
                    selectedFigure = fig;
                }
            }

            if (selectedFigure != null) {
                System.out.println("uma forma foi selecionada");

                System.out.println(selectedFigure);
                figs.remove(selectedFigure);
                figs.add(selectedFigure);
                selectedRect = new Retangulo(selectedFigure.getPosx()-5,selectedFigure.getPosy()-5,selectedFigure.getWidth()+10,selectedFigure.getHight()+10,2,null,new int[] {255,0,0});
                plotPoints(true);

            } else {
                selectedRect = new Retangulo(0,0,0,0,0,null,new int[] {0,0,0});
                plotPoints(false);
            } repaint();
        }
    }

    private void createPoints () {

        if (selectedFigure == null) {

            Elipse p1 = new Elipse(0,0,0,0,0,null,new int[] {0,0,0});
            Elipse p2 = new Elipse(0,0,0,0,0,null,new int[] {0,0,0});
            Elipse p3 = new Elipse(0,0,0,0,0,null,new int[] {0,0,0});
            Elipse p4 = new Elipse(0,0,0,0,0,null,new int[] {0,0,0});
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);
        }

        System.out.println(points.size());
    }

    private void plotPoints(boolean p) {

        if (p) {

            int x = selectedFigure.getPosx()-5;
            int y = selectedFigure.getPosy()-5;
            int w = selectedFigure.getWidth()+5;
            int h = selectedFigure.getHight()+5;

            points.set(0, new Elipse(x + w/2,y,5,5,1,new int[] {255,255,255}, new int[] {0,0,0}));
            points.set(1, new Elipse(x,y + h/2,5,5,1,new int[] {255,255,255}, new int[] {0,0,0}));
            points.set(2, new Elipse(x + w/2,y + h,5,5,1,new int[] {255,255,255}, new int[] {0,0,0}));
            points.set(3, new Elipse(x + w,y + h/2,5,5,1,new int[] {255,255,255}, new int[] {0,0,0}));
        } else {
            points.set(0, new Elipse(0,0,0,0,0,null,new int[] {0,0,0}));
            points.set(1, new Elipse(0,0,0,0,0,null,new int[] {0,0,0}));
            points.set(2, new Elipse(0,0,0,0,0,null,new int[] {0,0,0}));
            points.set(3, new Elipse(0,0,0,0,0,null,new int[] {0,0,0}));
        }
    }

    private boolean isPoint(int x, int y) {
        for (Figure p: this.points) {
            if (p.getPosx() <= x && x <= p.getPosx() + p.getWidth() && p.getPosy() <= y && y <= p.getPosy() + p.getHight()) { 
                return true;
            }
        } return false;
    }

    private Figure whichPoint(int x, int y) {
        for (Figure p: this.points) {
            if (p.getPosx() <= x && x <= p.getPosx() + p.getWidth() && p.getPosy() <= y && y <= p.getPosy() + p.getHight()) { 
                return p;
            }
        } return null;
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (selectedRect != null) selectedRect.paint(g);

        if (!points.isEmpty()) for (Figure point: this.points) point.paint(g);
    }
}