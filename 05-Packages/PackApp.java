import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {

	public static void main(String[] args) {
		PackFrame frame = new PackFrame();
		frame.setVisible(true);
	}
}

class PackFrame extends JFrame {

	Retangulo r1;
	Elipse e1;
	Poligono p1;

	PackFrame () {
		this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
		this.setTitle("Java Packages");
		this.setSize(500,500);
		this.r1 = new Retangulo(50, 150, 25, 50, 5, new int[] {0,255,0}, new int[] {255,0,255});
		this.e1 = new Elipse(100, 100, 100, 50, 5, new int[] {255,255,0}, new int[] {0,0,255});
		this.p1 = new Poligono(new int[] {200,300,375,425,325,150}, new int[] {200,150,175,275,425,350}, 5, new int[] {0,255,255}, new int[] {0,0,0});

	}

	public void paint (Graphics g) {
		super.paint(g);
		this.r1.paint(g);
		this.e1.paint(g);
		this.p1.paint(g);
	}
}
