import java.awt.*;

public class Boundary
{
    Vector a , b;
    Boundary(int x1 , int y1 , int x2 , int y2)
    {
        a= new Vector(x1,y1);
        b= new Vector(x2, y2);
    }

    void draw(Graphics2D g )
    {
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(1));
        g.drawLine((int)a.x,(int)a.y,(int)b.x,(int)b.y);
    }
}
