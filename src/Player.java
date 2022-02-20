import java.awt.*;
import java.util.ArrayList;

public class Player  {

    double x , y ;int  size;
    int angle =0; // in deg;
    double vX=0, vY=0, speed=.2, cap=2;
    //range for fov
    int fov =30;
    Vector pos;
     ArrayList<Ray> rays = new ArrayList<>();
     int offset = 0;

    public Player(int x, int y , int size)
    {
        this.x = x;
        this.y = y ;
        this.size  = size ;
        pos = new Vector(x+size/2,y+size/2);
        for(int i =-25; i<25 ; i+=1){
            rays.add(new Ray(pos, i)); // create rays
        }
    }

    void update()
    {

        if(pos.x>=Panel.WIDTH-size)
        {
            pos.x=512-size;
        }

        if(pos.x<0)
        {
            pos.x = 0;
        }

        if(pos.y>=Panel.HEIGHT-size)
        {
            pos.y=512-size;
        }

        if(pos.y<0)
        {
            pos.y = 0;
        }
        pos.x+=vX* Panel.dt;
        pos.y+=vY* Panel.dt;
    }

    void rotate(int angle)
    {
       this.offset+=angle;
        for(int i =0 ; i<rays.size() ; i+=1){
            rays.get(i).angle = i+offset; // create rays
        }
    }

    public void draw(Graphics2D g      )
    {
        g.setColor(Color.gray);
        g.drawRect((int)pos.x-size/2,(int)pos.y-size/2,size,size);
        for(int i =0; i<rays.size(); i++)
        {
            rays.get(i ).draw(g);
        }

    }

    ArrayList<Double> look(ArrayList<Boundary>walls, Graphics2D g )
    {
        ArrayList<Double> scene = new ArrayList<>() ;
        for(Ray ray : rays)
        {
            Vector closest =null;
            double record = Double.POSITIVE_INFINITY;
            for(Boundary wall : walls)
            {

                Vector pt = ray.cast(wall);

                if(pt!= null)
                {
                    // intersection
                    double d = Ray.dist(pos , pt);
                    if(d<record)
                    {
                        record = d;
                        closest = pt ;
                    }



                }
            }
            if(closest!= null)
            {
                g.setStroke(new BasicStroke(1)); // may want to change line thickness wen angle change is good
                g.setColor(new Color(255,255,255,100));
                g.drawLine((int)pos.x,(int)pos.y,(int)closest.x,(int)closest.y);
                g.setStroke(new BasicStroke(1));
            }
            scene.add(record);

        }
        return scene;
    }





}
