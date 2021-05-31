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
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private ArrayList<Button> buts = new ArrayList<Button>();

    private Random rand = new Random();

    private Point mouseCord;
    private Point mouseClick;

    private Figure selectedFigure;
    private Button selectedButton;

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
            System.out.println(err);
            System.out.println("projeto.bin não existe!");
        }

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);

        // adicionando os butoes //

        buts.add(new Button(1, new Elipse(0,0,0,0,0,new int[] {0,0,0},new int[] {0,0,0})));
        buts.add(new Button(2, new Retangulo(0,0,0,0,0,new int[] {0,0,0},new int[] {0,0,0})));
        buts.add(new Button(3, new Triangulo(0,0,0,0,0,new int[] {0,0,0},new int[] {0,0,0})));
        buts.add(new Button(4, new Linha(0,0,0,0,0,new int[] {0,0,0},new int[] {0,0,0})));

        this.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {

                    try {
                        FileOutputStream f = new FileOutputStream("projeto.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception err) {
                        System.out.println(err);
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
                        figs.add(new Retangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Elipse((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyChar() == 't') {
                        figs.add(new Triangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyChar() == 'y') {
                        figs.add(new Linha((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));

                    } else if (evt.getKeyCode() == 127) { // del //
                        figs.remove(selectedFigure);
                        selectedFigure = null;
                    } repaint();
                }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent evt) {

                mouseClick = evt.getPoint();

                if (evt.getButton() == 1) {
                    boolean isFigure = selectFigure((int) mouseClick.getX(),(int) mouseClick.getY());
                    boolean isButton = selectButton((int) mouseClick.getX(),(int) mouseClick.getY());

                    if (!(isButton || isFigure)) {
                        if (selectedButton != null) {

                            int r1 = rand.nextInt(255);
                            int g1 = rand.nextInt(255);
                            int b1 = rand.nextInt(255);
                            int r2 = rand.nextInt(255);
                            int g2 = rand.nextInt(255);
                            int b2 = rand.nextInt(255);  

                            switch(selectedButton.getIndex()) {                          

                                case 1:
                                    figs.add(new Elipse((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));
                                    break;
                                case 2:
                                    figs.add(new Retangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));
                                    break;
                                case 3:
                                    figs.add(new Triangulo((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));
                                    break;
                                case 4:
                                    figs.add(new Linha((int) mouseCord.getX(),(int) mouseCord.getY(),50,25,5,new int[] {r1,g1,b1}, new int[] {r2,g2,b2}));
                                    break;
                            }
                        }
                    }
                }

                if (evt.getButton() == 3) {

                    selectedFigure = null;
                    selectedButton = null;
                }

                repaint();
            }

            public void mouseReleased(MouseEvent evt) {
               bresize = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved (MouseEvent evt) {
                mouseCord = evt.getPoint();
            }

            public void mouseDragged(MouseEvent evt) {

                if (selectedFigure instanceof Figure) {

                    if (selectedFigure.isPoint((int) mouseClick.getX(),(int) mouseClick.getY())) {
                        bresize = true;
                        selectedFigure.resize((int) (evt.getX() - mouseClick.getX()), (int) (evt.getY() - mouseClick.getY()), selectedFigure.getPointIndex((int) mouseClick.getX(),(int) mouseClick.getY()));
                    }

                    if (!bresize) {
                        selectedFigure.drag((int) (evt.getX() - mouseClick.getX()), (int) (evt.getY() - mouseClick.getY()));
                    }
                } 

                repaint();
                mouseClick = evt.getPoint();
            }
        });
    }

    private boolean selectFigure(int x, int y) {

        if (!figs.isEmpty()) {

            selectedFigure = null;

            for (Figure fig: this.figs) {
                if (fig.clicked(x,y)) {
                    selectedFigure = fig;
                }
            }

            if (selectedFigure != null) {
                figs.remove(selectedFigure);
                figs.add(selectedFigure);
                selectedFigure.setPoints();
                return true;
            }
        }

        return false;
    }

    private boolean selectButton(int x, int y) {

        if (!buts.isEmpty()) {

            for (Button but: this.buts) {
                if (but.clicked(x,y)) {
                    selectedButton = but;
                    return true;
                }
            } 
        }

        return false;
    }

    public void paint (Graphics g) {
        super.paint(g);

        for (Figure fig: this.figs) {
            fig.paint(g, fig ==  selectedFigure);
        }

        for (Button but: this.buts) {
            but.paint(g, but == selectedButton);
        }
    }
}