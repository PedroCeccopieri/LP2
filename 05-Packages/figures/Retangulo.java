package figures;

import java.awt.*;

public class Retangulo {

	int posx, posy;
	int largura, altura;
	int contorno;
	int corFundo[];
	int corContorno[];

	public Retangulo(int posx, int posy, int largura, int altura, int contorno, int corFundo[], int corContorno[]) {

		this.posx = posx;
		this.posy = posy;
		this.largura = largura;
		this.altura = altura;
		this.contorno = contorno;
		this.corFundo = corFundo;
		this.corContorno = corContorno;
		
	}

	public void print () {
		System.out.format("Esse retangulo tem largura %d e altura %d. esta na posicao (%d,%d)\n", this.largura, this.altura, this.posx, this.posy);
	}

	public void paint (Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(corFundo[0],corFundo[1],corFundo[2]));
		g2d.fillRect(this.posx, this.posy, this.largura, this.altura);
		g2d.setStroke(new BasicStroke(this.contorno));
		g2d.setColor(new Color(corContorno[0],corContorno[1],corContorno[2]));
		g2d.drawRect(this.posx, this.posy, this.largura, this.altura);

	}

}
