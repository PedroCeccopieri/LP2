import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {

	public static void main(String[] args) {
		PaintFrame frame = new PaintFrame();
		frame.setVisible(true);
	}
}

class PaintFrame extends JFrame {

	Retangulo r1, r2, r3;
	PaintFrame () {
		this.setTitle("Painting Figures");
		this.setSize(500,500);
		this.r1 = new Retangulo(50, 100, 50, 50, 5, new int[] {0,255,0}, new int[] {255,0,255});
		this.r2 = new Retangulo(100, 150, 50, 50, 10, new int[] {255,0,0}, new int[] {0,255,255});
		this.r3 = new Retangulo(150, 200, 50, 50, 15, new int[] {0,0,255}, new int[] {255,255,0});
	}

	public void paint (Graphics g) {
		super.paint(g);
		this.r1.paint(g);
		this.r2.paint(g);
		this.r3.paint(g);
	}

}

class Retangulo {

	int posx, posy;
	int largura, altura;
	int contorno;
	int corFundo[];
	int corContorno[];

	Retangulo(int posx, int posy, int largura, int altura, int contorno, int corFundo[], int corContorno[]) {

		this.posx = posx;
		this.posy = posy;
		this.largura = largura;
		this.altura = altura;
		this.contorno = contorno;
		this.corFundo = corFundo;
		this.corContorno = corContorno;
		
	}

	void print () {
		System.out.format("Esse retangulo tem largura %d e altura %d. esta na posicao (%d,%d)\n", this.largura, this.altura, this.posx, this.posy);
	}

	void paint (Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(corFundo[0],corFundo[1],corFundo[2]));
		g2d.fillRect(this.posx, this.posy, this.largura, this.altura);
		g2d.setStroke(new BasicStroke(this.contorno));
		g2d.setColor(new Color(corContorno[0],corContorno[1],corContorno[2]));
		g2d.drawRect(this.posx, this.posy, this.largura, this.altura);

	}

}
