import java.awt.*;
import java.util.ArrayList;

public class Player  {

    double x , y ;int  size;
    int angle =0; // in deg;
    double vX=0, vY=0, speed=.2, cap=2;
    //range for fov
    int fov =15;

     ArrayList<Ray> rays = new ArrayList<>();

    public Player(int x, int y , int size)
    {
        this.x = x;
        this.y = y ;
        for(int i =angle-fov; i<angle+fov; i++)
        {
            Ray ray = new Ray(x,y, i);
            rays.add(ray);
        }


        this.size = size;


    }

    /*public void drawGun(Graphics2D g)
    {

        int finalX = (int)(gunX+(gunLength*Math.cos(Math.toRadians(angle))));
        int finalY = (int)(gunY+(gunLength*Math.sin(Math.toRadians(angle)))); // y might have to be minus
        g.drawLine(gunX,gunY,finalX,finalY);
    }*/



    public void draw(Graphics2D g)
    {

        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,size,size);

        g.setStroke(new BasicStroke(3));

        /*drawGun(g);*/
        for(int j =0; j<rays.size(); j++)
        {
            rays.get(j).draw(g);

        }

       //walls
        for(int i =0; i<Panel.walls.size(); i++)
        {
            for(int j =0; j<rays.size(); j++)
            {

                Vector point = rays.get(i).cast(Panel.walls.get(i));
                if(point!=null)
                    g.drawLine((int)rays.get(i).pos.x,(int)rays.get(i).pos.y,(int)point.x, (int)point.y);

            }


        }








    }
    public void update()
    {
        // if hitting a wall stop the movement in that direction



            if(x>=512-size)
            {
                x=512-size;
            }

            if(x<0)
            {
                x = 0;
            }

        if(y>=512-size)
        {
            y=512-size;
        }

        if(y<0)
        {
            y = 0;
        }

        x+=vX*Panel.dt;

            y+=vY*Panel.dt;
            for(int i =0; i<rays.size(); i++)
            {
                rays.set(i,new Ray((int)(x+size/2.0),(int)(y+size/2.0),angle));
            }

            /*gunX = (int) (x +size/2);

            gunY = (int) (y  +size/2);*/


    }
    public boolean collide()
    {

        return false;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,size,size);
    }




}
