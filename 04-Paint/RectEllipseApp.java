import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class RectEllipseApp {

	public static void main(String[] args) {
		RectEllipseFrame frame = new RectEllipseFrame();
		frame.setVisible(true);
	}
}

class RectEllipseFrame extends JFrame {

	Retangulo r1;
	Elipse e1, e2, e3;

	RectEllipseFrame () {
		this.setTitle("Painting Figures");
		this.setSize(500,500);
		this.r1 = new Retangulo(50, 150, 25, 50, 5, new int[] {0,255,0}, new int[] {255,0,255});
		this.e1 = new Elipse(100, 100, 100, 50, 5, new int[] {255,255,0}, new int[] {0,0,255});
		this.e2 = new Elipse(330, 270, 50, 25, 10, new int[] {0,255,0}, new int[] {255,0,255});
		this.e3 = new Elipse(240, 410, 150, 75, 15, new int[] {255,0,0}, new int[] {0,255,255});
	}

	public void paint (Graphics g) {
		super.paint(g);
		this.r1.paint(g);
		this.e1.paint(g);
		this.e2.paint(g);
		this.e3.paint(g);
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

class Elipse {

	int posx, posy;
	int largura, altura;
	int contorno;
	int corFundo[];
	int corContorno[];

	Elipse(int posx, int posy, int largura, int altura, int contorno, int corFundo[], int corContorno[]) {

		this.posx = posx;
		this.posy = posy;
		this.largura = largura;
		this.altura = altura;
		this.contorno = contorno;
		this.corFundo = corFundo;
		this.corContorno = corContorno;
		
	}

	void print () {
		System.out.format("Essa elipse tem largura %d e altura %d, esta na posicao (%d,%d)\n", this.largura, this.altura, this.posx, this.posy);
	}

	void paint (Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(corFundo[0],corFundo[1],corFundo[2]));
		g2d.fill(new Ellipse2D.Double(this.posx, this.posy, this.largura, this.altura));
		g2d.setStroke(new BasicStroke(this.contorno));
		g2d.setColor(new Color(corContorno[0],corContorno[1],corContorno[2]));
		g2d.draw(new Ellipse2D.Double(this.posx, this.posy, this.largura, this.altura));

	}

}