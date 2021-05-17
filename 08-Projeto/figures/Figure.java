package figures;

import ivisible.*;

import java.awt.Graphics;

public abstract class Figure implements IVisible {

	int posx, posy;
    int width, hight;
    int stroke;
    int backgroundColor[], strokeColor[];
    
    public abstract void paint (Graphics g);

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
    }

    public boolean clicked (int x, int y) {
    	if (this.posx <= x && x <= this.posx + this.width && this.posy <= y && y <= this.posy + this.hight) return true;
    	else return false;
    }

    public void resize(int dx, int dy) {
    	if (dx != 0) {
    		this.width += dx;
    	}

    	if (dy != 0) {
    		this.hight += dy;
    	}
    }

    public void changeStroke(int n) {
    	if (this.stroke >= 0 && this.stroke + n >= 0) this.stroke += n;
    	//System.out.println(this.stroke);
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