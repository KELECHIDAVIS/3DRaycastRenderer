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
    Player player = new Player (256,256,8 );
    //Boundary wall = new Boundary(450,100,450,400);

    Vector wallstart , wallend;
    static ArrayList<Boundary> walls = new ArrayList<>() ;
    static double  timer =0, dt =0, prev;
    Panel()
    {

        this.setBackground(Color.black);
        prev = System.currentTimeMillis();
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
        g1.drawLine(512,0,512,512);
            // then render player
        for(int i =0; i<walls.size(); i++)
        {
            walls.get(i).draw(g1);
        }
        player.draw((Graphics2D) g);

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
                player.angle-=4;
                if(player.angle<=-360)
                {
                    player.angle+=360;
                }
                break;
            case KeyEvent.VK_RIGHT:
                player.angle+=4;
                if(player.angle>=360)
                {
                    player.angle-=360;
                }
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
