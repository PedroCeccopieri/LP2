package figures;

import java.awt.*;

public class Poligono {

	int posx, posy;
	int pontosx[], pontosy[];
	int contorno;
	int corFundo[];
	int corContorno[];

	public Poligono(int pontosx[], int pontosy[], int contorno, int corFundo[], int corContorno[]) {

		this.pontosx = pontosx;
		this.pontosy = pontosy;
		this.contorno = contorno;
		this.corFundo = corFundo;
		this.corContorno = corContorno;
		
	}

	public void paint (Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(corFundo[0],corFundo[1],corFundo[2]));
		g2d.fillPolygon(this.pontosx, this.pontosy, pontosx.length);
		g2d.setStroke(new BasicStroke(this.contorno));
		g2d.setColor(new Color(corContorno[0],corContorno[1],corContorno[2]));
		g2d.drawPolygon(this.pontosx, this.pontosy, pontosx.length);

	}
}