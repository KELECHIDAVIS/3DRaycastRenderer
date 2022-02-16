import java.awt.*;
import java.util.ArrayList;

public class Player  {

    double x , y ;int  size;
    int angle =0; // in deg;
    double vX=0, vY=0, speed=0.00002, cap=2;
     int gunLength=15; // make gun a line so it is easier to change angle ;first quad i think x+a, y-a;\
    int gunX, gunY;
    ArrayList<Rectangle> obstacles = new ArrayList<>();
    public Player(int x, int y , int size, int[][] grid)
    {
        this.x = x;
        this.y = y ;
        gunX = x +size/2;
        gunY = y  +size/2;
        this.size = size;
        for(int i =0; i<grid.length; i++  )
        {
            for(int j =0; j<grid[i].length; j++)
            {
                if(grid[i][j]==1) // obstacle
                {

                  obstacles.add(new Rectangle(j*Panel.boxSize,i*Panel.boxSize,Panel.boxSize,Panel.boxSize));
                }
            }
        }

    }

    public void drawGun(Graphics2D g)
    {

        int finalX = (int)(gunX+(gunLength*Math.cos(Math.toRadians(angle))));
        int finalY = (int)(gunY+(gunLength*Math.sin(Math.toRadians(angle)))); // y might have to be minus
        g.drawLine(gunX,gunY,finalX,finalY);
    }



    public void draw(Graphics2D g)
    {

        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,size,size);

        g.setStroke(new BasicStroke(3));

        drawGun(g);
        g.setStroke(new BasicStroke(1));






    }
    public void update()
    {
        // if hitting a wall stop the movement in that direction


            // x
            for(int i =0; i<obstacles.size(); i++)
            {
                if(obstacles.get(i).intersects(getBounds()))
                {
                    Rectangle rect = getBounds();
                    if(rect.x<obstacles.get(i).x)
                    {
                        // right side
                        vX=0;
                        x-=1;

                    }
                    if(rect.x>obstacles.get(i).x)
                    {
                        // right side
                        vX=0;
                        x+=1;

                    }
                    if(rect.y<obstacles.get(i).y)
                    {
                        // right side
                        vY=0;
                        y-=1;

                    }
                    if(rect.y>obstacles.get(i).y)
                    {
                        // right side
                        y+=1;
                        vY=0;
                    }
                }
            }


            x+=vX;

            y+=vY;

            gunX = (int) (x +size/2);

            gunY = (int) (y  +size/2);


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
