package figures;

import java.awt.*;

public class Retangulo extends Figure {

    public Retangulo (int posx, int posy, int width, int hight, int stroke, int backgroundColor[], int strokeColor[]) {

        super();

        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.hight = hight;
        this.stroke = stroke;
        this.backgroundColor = backgroundColor;
		this.strokeColor = strokeColor;
    }

    public void paint (Graphics g, boolean focused) {

        super.paint(g, focused);

    	Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(new Color(backgroundColor[0],backgroundColor[1],backgroundColor[2]));
		g2d.fillRect(this.posx, this.posy, this.width, this.hight);
		g2d.setStroke(new BasicStroke(this.stroke));
		g2d.setColor(new Color(strokeColor[0],strokeColor[1],strokeColor[2]));
		g2d.drawRect(this.posx, this.posy, this.width, this.hight);

		if (focused) {
            for (rPoint p: this.points) {
                p.paint(g, false);
            }
        }
    }
}