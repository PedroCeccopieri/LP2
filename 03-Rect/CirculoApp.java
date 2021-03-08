class Circulo {

	int raio; 
	int posx, posy;

	Circulo(int raio, int posx, int posy) {
		this.raio = raio;
		this.posx = posx;
		this.posy = posy;
	}

	void print () {
		System.out.format("Esse circulo tem raio %d e esta na posicao (%d,%d)\n", this.raio, this.posx, this.posy);
		System.out.format("Area do circulo: %d\n", this.calculaArea());
	}

	int calculaArea() {
		return 3 * raio * raio;
	}

	void drag(int dx, int dy) {
		this.posx += dx; this.posy += dy;
	}
}

class CirculoApp {

	public static void main(String[] args) {
		
		Circulo c1 = new Circulo(5,1,1);
		c1.print();
		c1.drag(3, 4);
		c1.print();
	}
}
