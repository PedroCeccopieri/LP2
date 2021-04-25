package figures;

import java.awt.Graphics;

public abstract class Figure {

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

    public void drag(int x, int y) {
    	this.posx = x;
    	this.posy = y;
    }

    /*public void resize(int x, int y, Figure p) {
    	if (p.getPosx() == (this.posx-5) + (this.width+5)/2 && p.getPosy() == this.posy-5) {
    		System.out.println("topo");
    	} 

    	else if (p.getPosx() == (this.posx-5) && p.getPosy() == this.posy-5 + (this.hight+5)/2) {
    		System.out.println("esquerda");
    	}

    	else if (p.getPosx() == (this.posx-5) + (this.width+5)/2 && p.getPosy() == this.posy + this.hight) {
    		System.out.println("base");
    	}

    	else if (p.getPosx() == this.posx + this.width && p.getPosy() == this.posy-5 + (this.hight+5)/2) {
    		System.out.println("direita");
    	}
    }*/

    public void changeStroke(int n) {
    	if (this.stroke >= 0 && this.stroke + n >= 0) this.stroke += n;
    	//System.out.println(this.stroke);
    }

    public void changeBackgroundColor(int r, int g, int b) {
    	if (this.backgroundColor[0] > 0 && this.backgroundColor[0] < 255) this.backgroundColor[0] += r;
    	if (this.backgroundColor[1] > 0 && this.backgroundColor[1] < 255) this.backgroundColor[1] += g;
    	if (this.backgroundColor[2] > 0 && this.backgroundColor[2] < 255) this.backgroundColor[2] += b;
    	System.out.format("%d %d %d\n", this.backgroundColor[0],this.backgroundColor[1],this.backgroundColor[2]);
    }

    public void changeStrokeColor(int r, int g, int b) {
    	if (this.strokeColor[0] > 0 && this.strokeColor[0] < 255) this.strokeColor[0] += r;
    	if (this.strokeColor[1] > 0 && this.strokeColor[1] < 255) this.strokeColor[1] += g;
    	if (this.strokeColor[2] > 0 && this.strokeColor[2] < 255) this.strokeColor[2] += b;
    	System.out.format("%d %d %d\n", this.strokeColor[0],this.strokeColor[1],this.strokeColor[2]);
    }
}