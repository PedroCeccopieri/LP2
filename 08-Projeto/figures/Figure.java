package figures;

import ivisible.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Figure implements IVisible, Serializable {

	public int posx, posy;
    public int width, hight;

    protected int stroke;
    protected int backgroundColor[], strokeColor[];

    protected ArrayList<rPoint> points = new ArrayList<rPoint>();

    public Figure() {

    	for (int i = 0; i < 4; i++) points.add(new rPoint(0, 0, i));
    }
    
    public void paint (Graphics g, boolean focused) {

    	Graphics2D g2d = (Graphics2D) g;

    	if (focused) {
    		g2d.setStroke(new BasicStroke(2));
    		g2d.setColor(new Color(255,0,0));
    		g2d.drawRect(this.posx - 5, this.posy - 5, this.width + 10, this.hight + 10);
    	}
    }

    public void setPoints() {
    	points.get(0).setPos(this.posx + (this.width / 2) - (10/2), this.posy);
    	points.get(1).setPos(this.posx + this.width - 10, this.posy + (this.hight / 2) - (10/2));
    	points.get(2).setPos(this.posx + (this.width / 2) - (10/2), this.posy + this.hight - 10);
    	points.get(3).setPos(this.posx, this.posy + (this.hight / 2) - (10/2));
    }

    public boolean isPoint(int x, int y) {
    	for (rPoint p: this.points) if (p.clicked(x,y)) return true;
    	return false;
    }

    public int getPointIndex(int x, int y) {
    	for (int i = 0; i < 4; i++) {
    		if (points.get(i).clicked(x,y)) return i;
    	} return -1;
    }

    public int getPosx() {
    	return this.posx;
    }

    public int getPosy() {
    	return this.posy;
    }

    public int getWidth() {
    	return this.width;
    }

    public int getHight() {
    	return this.hight;
    }

    public void drag(int dx, int dy) {
    	this.posx += dx;
    	this.posy += dy;

    	setPoints();
    }

    public boolean clicked (int x, int y) {
    	return (this.posx <= x && x <= this.posx + this.width && this.posy <= y && y <= this.posy + this.hight);
    }

    public void resize(int dx, int dy, int index) {
    	switch(index) {
    		case 0:
    			this.posy += dy;
    			this.hight -= dy;
    			break;
    		case 1:
    			this.width += dx;
    			break;
    		case 2:
    			this.hight += dy;
    			break;
    		case 3:
    			this.posx += dx;
    			this.width -= dx;
    			break;
    	}

    	setPoints();
    }

    public void changeStroke(int n) {
    	if (this.stroke >= 0 && this.stroke + n >= 0) this.stroke += n;
    }

    public void changeBackgroundColor(int r, int g, int b) {
    	if (this.backgroundColor[0] + r >= 0 && this.backgroundColor[0] + r <= 255) this.backgroundColor[0] += r;
    	if (this.backgroundColor[1] + g >= 0 && this.backgroundColor[1] + g <= 255) this.backgroundColor[1] += g;
    	if (this.backgroundColor[2] + b >= 0 && this.backgroundColor[2] + b <= 255) this.backgroundColor[2] += b;
    	System.out.format("%d %d %d\n", this.backgroundColor[0],this.backgroundColor[1],this.backgroundColor[2]);
    }

    public void changeStrokeColor(int r, int g, int b) {
    	if (this.strokeColor[0] + r >= 0 && this.strokeColor[0] + r <= 255) this.strokeColor[0] += r;
    	if (this.strokeColor[1] + g >= 0 && this.strokeColor[1] + g <= 255) this.strokeColor[1] += g;
    	if (this.strokeColor[2] + b >= 0 && this.strokeColor[2] + b <= 255) this.strokeColor[2] += b;
    	System.out.format("%d %d %d\n", this.strokeColor[0],this.strokeColor[1],this.strokeColor[2]);
    }
}