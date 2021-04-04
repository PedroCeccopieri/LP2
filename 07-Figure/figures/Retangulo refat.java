package figures;

import java.awt.*;

public class Retangulo extends Figure {

    public Retangulo (int posx, int posy, int largura, int altura) {
        this.posx = posx;
        this.posy = posy;
        this.largura = largura;
        this.altura = altura;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.posx,this.posy, this.largura,this.altura);
    }
}