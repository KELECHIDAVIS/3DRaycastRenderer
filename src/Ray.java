import java.awt.*;

public class Ray   {

    Vector pos;
    Vector dir;
    int angle ;
    Ray(Vector pos , int angle)
    {
        this.angle = angle ;
        this.pos  = pos;
        this.dir = new Vector(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));
    }

    void draw(Graphics2D g )
    {
        g.setColor(Color.white);
        // translate
        this.dir = new Vector(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));

        g.drawLine((int)pos.x,(int)pos.y,(int)((pos.x+dir.x*10)), (int)(pos.y+dir.y*10));
        g.setStroke(new BasicStroke(1));

    }

    static double dist(Vector first , Vector second)
    {
        double dX = (second.x-first.x);
        double dY = (second.y-first.y);
        return Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));

    }


    Vector cast(Boundary wall)
    {
        // if it collides  then return the point at which
        double x1 = wall.a.x;
        double y1 = wall.a.y;
        double x2 = wall.b.x;
        double y2 = wall.b.y;

        double x3 = pos.x;
        double y3 = pos.y;
        double x4 = pos.x+dir.x;
        double y4 = pos.y+dir.y;

        double denominator = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);  // if this is 0 then they are perfectly parallel
        if(denominator==0)
        {
            return null;
        }
        double t = ((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/denominator;
        double u = -((x1-x2)*(y1-y3)-(y1-y2)*(x1-x3))/denominator;
        if(t >0&&t<1&&u>0)
        {
           Vector point ;
           point=new Vector(x1+t*(x2-x1),y1+t*(y2-y1));
           return point ;
        }
        return null ;
    }


}
