import ivisible.IVisible;
import figures.Figure;
import java.awt.*;

public class Button implements IVisible {
    static int SPC = 20;
    static int DIM = 40;
    static int PAD = 4;

    public int idx;
    
    private Figure fig;

    public Button (int idx, Figure fig) {
        this.idx = idx;
        this.fig = fig;
        this.fig.posx = PAD+SPC;
        this.fig.posy = PAD+SPC + idx*DIM;
        this.fig.width = DIM-PAD*2;
        this.fig.hight = DIM-PAD*2;
    }

    public boolean clicked (int x, int y) {
        return SPC<=x && x<=SPC+DIM && SPC+this.idx*DIM<=y && y<=SPC+this.idx*DIM+DIM;
    }

    public int getIndex() {
        return this.idx;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(focused ? Color.GRAY : Color.LIGHT_GRAY);
        g2d.fillRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        this.fig.paint(g, false);
    }
}