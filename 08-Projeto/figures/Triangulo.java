package figures;

import java.awt.*;

public class Triangulo extends Figure {

	int pontosx[], pontosy[];

	public Triangulo(int posx, int posy, int width, int hight, int stroke, int backgroundColor[], int strokeColor[]) {

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

	@Override
	public void drag(int x, int y) {
    	this.posx = x;
    	this.posy = y;
    	this.pontosx = new int [] {posx, posx, posx + width};
		this.pontosy = new int[] {posy, posy + hight, posy + hight};
    }

    @Override
    public void resize(int dx, int dy) {
    	if (dx != 0) {
    		this.width += dx;
    	}

    	if (dy != 0) {
    		this.hight += dy;
    	}

    	this.pontosx = new int [] {posx, posx, posx + width};
		this.pontosy = new int[] {posy, posy + hight, posy + hight};
    }

	public void paint (Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(backgroundColor[0],backgroundColor[1],backgroundColor[2]));
		g2d.fillPolygon(this.pontosx, this.pontosy, 3);
		g2d.setStroke(new BasicStroke(this.stroke));
		g2d.setColor(new Color(strokeColor[0],strokeColor[1],strokeColor[2]));
		g2d.drawPolygon(this.pontosx, this.pontosy, pontosx.length);

	}
}