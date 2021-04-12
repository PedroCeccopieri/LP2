package figures;

import java.awt.*;

public class Retangulo extends Figure {

    public Retangulo (int posx, int posy, int width, int hight) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.hight = hight;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.posx,this.posy, this.width,this.hight);
    }
}