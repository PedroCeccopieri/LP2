package figures;

import java.awt.Graphics;

public abstract class Figure {

	int posx, posy;
    int width, hight;
    
    public abstract void paint (Graphics g);

    public int getPosx() {
    	return posx;
    }

    public int getPosy() {
    	return posy;
    }

    public int getWidth() {
    	return width;
    }

    public int getHight() {
    	return hight;
    }

    public void drag(int x, int y) {
    	this.posx = x;
    	this.posy = y;
    }
}