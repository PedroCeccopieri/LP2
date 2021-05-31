package figures;

import java.io.Serializable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class rPoint implements Serializable {

    private int index;

    private double posx, posy;
    private int diametro;

    public rPoint (double posx, double posy, int index) {
    	
        this.index = index;

        this.posx = posx;
        this.posy = posy;
        this.diametro = 10;

    }

    public void setPos(double x, double y) {
        this.posx = x;
        this.posy = y;
    }

    public boolean clicked (int x, int y) {
        return (this.posx <= x && x <= this.posx + this.diametro && this.posy <= y && y <= this.posy + this.diametro);
    }

    public void paint (Graphics g, boolean focused) {

        Graphics2D g2d = (Graphics2D) g;
    
		g2d.setColor(new Color(255,255,255));
		g2d.fill(new Ellipse2D.Double(this.posx, this.posy, this.diametro, this.diametro));
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(new Color(0,0,0));
		g2d.draw(new Ellipse2D.Double(this.posx, this.posy, this.diametro, this.diametro));
	}
}
