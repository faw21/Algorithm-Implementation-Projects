import java.awt.* ;
import javax.swing.* ;


public class Cloth extends JPanel
{
    public int width;
    public int height;
    public int pixels;


    public Cloth(int w, int h, int p)
    {
        this.pixels = p;
        this.width = w * p ;
        this.height = h * p;
        setPreferredSize(new Dimension(this.width, this.height));
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics) ;
        graphics.setColor(Color.black) ;
        graphics.fillRect(0,0,width,height) ;
    }

    public void drawCut(Cut c)
    {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.white);
        graphics.drawLine(c.x1*pixels, c.y1*pixels, c.x2*pixels, c.y2*pixels);
    }

    public void drawGarment(Garment c)
    {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.gray);
        graphics.fillRect(c.x*pixels, c.y*pixels, c.width*pixels, c.height*pixels);
    }
}