package figures;

import java.awt.*;

public class Triangulo extends Figure {

	private int pontosx[], pontosy[];

	public Triangulo(int posx, int posy, int width, int hight, int stroke, int backgroundColor[], int strokeColor[]) {

        super();

		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.hight = hight;
		this.pontosx = new int [] {posx, posx, posx + width};
		this.pontosy = new int[] {posy, posy + hight, posy + hight};
		this.stroke = stroke;
		this.backgroundColor = backgroundColor;
		this.strokeColor = strokeColor;
	}

	public void paint (Graphics g, boolean focused) {

		this.pontosx = new int [] {posx, posx, posx + width};
		this.pontosy = new int[] {posy, posy + hight, posy + hight};

        super.paint(g, focused);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(backgroundColor[0],backgroundColor[1],backgroundColor[2]));
		g2d.fillPolygon(this.pontosx, this.pontosy, 3);
		g2d.setStroke(new BasicStroke(this.stroke));
		g2d.setColor(new Color(strokeColor[0],strokeColor[1],strokeColor[2]));
		g2d.drawPolygon(this.pontosx, this.pontosy, pontosx.length);

		if (focused) {
            for (rPoint p: this.points) {
                p.paint(g, false);
            }
        }
	}
}