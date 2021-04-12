package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Elipse extends Figure {

    public Elipse (int posx, int posy, int width, int hight) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.hight = hight;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.posx, this.posy, this.width, this.hight));
    }
}