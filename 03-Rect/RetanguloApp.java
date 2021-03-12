class Retangulo {

	int largura, altura; 
	int posx, posy;

	Retangulo(int largura, int altura, int posx, int posy) {
		this.largura = largura;
		this.altura = altura;
		this.posx = posx;
		this.posy = posy;
	}

	void print () {
		System.out.format("Esse retangulo tem largura %d e altura %d. esta na posicao (%d,%d)\n", this.largura, this.altura, this.posx, this.posy);
		System.out.format("Area do retangulo: %d\n", this.calculaArea());
	}

	int calculaArea() {
		return this.largura * this.altura;
	}

	void drag(int dx, int dy) {
		this.posx += dx; this.posy += dy;
	}
}

class RetanguloApp {

	public static void main(String[] args) {
		
		Retangulo c1 = new Retangulo(5, 4,1,1);
		c1.print();
		c1.drag(3, 4);
		c1.print();
	}
}