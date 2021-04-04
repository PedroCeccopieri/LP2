package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Elipse extends Figure {
    int posx, posy;
    int largura, altura;

    public Elipse (int posx, int posy, int largura, int altura) {
        this.posx = posx;
        this.posy = posy;
        this.largura = largura;
        this.altura = altura;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.posx, this.posy, this.largura, this.altura));
    }
}