package figures;

import java.awt.Graphics;

public abstract class Figure {

	int posx, posy;
    int largura, altura;
    
    public abstract void paint (Graphics g);
}