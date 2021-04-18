package figures;

import java.awt.Graphics;

public abstract class Figure {

	int posx, posy;
    int width, hight;
    int stroke;
    int backgroundColor[], strokeColor[];
    
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

    public void changeStroke(int n) {
    	if (this.stroke >= 0 && this.stroke + n >= 0) this.stroke += n;
    	//System.out.println(this.stroke);
    }

    public void changeBackgroundColor(int r, int g, int b) {
    	if (this.backgroundColor[0] > 0 && this.backgroundColor[0] < 255) this.backgroundColor[0] += r;
    	if (this.backgroundColor[1] > 0 && this.backgroundColor[1] < 255) this.backgroundColor[1] += g;
    	if (this.backgroundColor[2] > 0 && this.backgroundColor[2] < 255) this.backgroundColor[2] += b;
    	//System.out.format("%d %d %d\n", this.backgroundColor[0],this.backgroundColor[1],this.backgroundColor[2]);
    }

    public void changeStrokeColor(int r, int g, int b) {
    	if (this.strokeColor[0] > 0 && this.strokeColor[0] < 255) this.strokeColor[0] += r;
    	if (this.strokeColor[1] > 0 && this.strokeColor[1] < 255) this.strokeColor[1] += g;
    	if (this.strokeColor[2] > 0 && this.strokeColor[2] < 255) this.strokeColor[2] += b;
    	//System.out.format("%d %d %d\n", this.strokeColor[0],this.strokeColor[1],this.strokeColor[2]);
    }
}