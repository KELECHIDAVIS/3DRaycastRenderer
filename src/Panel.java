import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Panel extends GamePanel{
    /*static int boxSize = 64 ;
    int[][] grid = {{1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,1},
                    {1,0,0,1,0,0,0,1},
                    {1,0,0,0,0,1,0,1},
                    {1,0,0,0,0,0,0,1},
                    {1,0,1,0,0,0,0,1},
                    {1,0,0,0,0,0,0,1},
                    {1,1,1,1,1,1,1,1}};  // 8 by 8 grid with 1 representing obstacle and 0 representing free space*/
    static int WIDTH =Main.WIDTH, HEIGHT=Main.HEIGHT; // width is width  of the left half
    Player player = new Player (WIDTH/2,HEIGHT/2,8 );

    //Boundary wall = new Boundary(450,100,450,400);

    Vector wallstart , wallend;
    static ArrayList<Boundary> walls = new ArrayList<>() ;
    Boundary divider = new Boundary(WIDTH,0,WIDTH,HEIGHT); // wall in the middle of the screen
    static double  timer =0, dt =0, prev;
    Panel()
    {

        this.setBackground(Color.black);
        prev = System.currentTimeMillis();
        walls.add(divider);// right
        walls.add(new Boundary(0,0,0,HEIGHT));// left
        walls.add(new Boundary(0,0,WIDTH,0) );// top
        walls.add(new Boundary(0,HEIGHT,WIDTH,HEIGHT) ); //bottom
        //walls.add(wall);
        this.start();

    }

    @Override
    public void update() {
        // compute delta time
        double now = System.currentTimeMillis();
        dt = now- prev ;
        prev = now  ;
        timer+= dt;


        player.update();

    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.gray);
        divider.draw(g1);
            // then render player
        player.draw( g1);
        for(int i =0; i<walls.size(); i++)
        {
            walls.get(i).draw(g1);

        }


       ArrayList<Double> scene = player.look(walls, g1);
        int w = WIDTH/scene.size();

        for(int i =0; i<scene.size(); i++)
        {
            double sq = scene.get(i)*scene.get(i);
            double wSq = WIDTH*WIDTH;
            int col =(int) map(sq, 0,wSq,255,0);
            if(col<0)
                col= 0;
            int h = (int) map(scene.get(i), 0,WIDTH,HEIGHT,0);
            System.out.println(scene.get(i)+": "+col);
                g1.setColor(new Color(255, 255, 255, col));
                // draw rect at its center
                g1.fillRect(WIDTH+10+i*w, HEIGHT/2-h/2,w,h);


           // g.drawRect(WIDTH+10+i*w, 0,w,HEIGHT);
        }


    }

    public double map(double value , double start1, double end1, double start2, double end2 )
    {


        return ((value-start1)/(end1-start1))*(end2-start2)+start2 ;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode())
        {

            case KeyEvent.VK_W :
                player.vY=0;
                player.vY=-player.speed;
                break;
            case KeyEvent.VK_D :
                player.vX=0;
                player.vX=player.speed;
                break;
            case KeyEvent.VK_A :
                player.vX=0;
                player.vX=-player.speed;
                break;
            case KeyEvent.VK_Z :
                if(!walls.isEmpty())
                walls.remove(walls.size()-1);
                break;
            case KeyEvent.VK_S:
                player.vY=0;
                player.vY=player.speed;
                break;
            case KeyEvent.VK_LEFT:
                player.rotate(-2);
                break;
            case KeyEvent.VK_RIGHT:
               player.rotate(2);
                break;


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {

            case KeyEvent.VK_W :
                player.vY=0;
                break;
            case KeyEvent.VK_D :
                player.vX=0;
                break;
            case KeyEvent.VK_A :
                player.vX=0;
                break;
            case KeyEvent.VK_S:
                player.vY=0;
                break;


        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        wallstart = new Vector(e.getX(),e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        wallend = new Vector(e.getX(),e.getY());
        if(wallstart.x<divider.a.x&&wallend.x<divider.a.x)
            walls.add(new Boundary((int)wallstart.x,(int)wallstart.y,(int)wallend.x,(int)wallend.y));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
