import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Panel extends GamePanel{
    static int boxSize = 64 ;
    int[][] grid = {{1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,1},
                    {1,0,0,1,0,0,0,1},
                    {1,0,0,0,0,1,0,1},
                    {1,0,0,0,0,0,0,1},
                    {1,0,1,0,0,0,0,1},
                    {1,0,0,0,0,0,0,1},
                    {1,1,1,1,1,1,1,1}};  // 8 by 8 grid with 1 representing obstacle and 0 representing free space
    Player player = new Player (boxSize*grid.length/2,boxSize*grid.length/2,10, grid );
    Panel()
    {

        this.setBackground(Color.gray);
        this.start();

    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        // render grid
        Graphics2D g1 = (Graphics2D) g;
        for(int i =0; i<8; i++)
        {
            for(int j =0; j<8; j++  )
            {
                g1.setColor(new Color(255,255,255,170));
                g1.setStroke(new BasicStroke(1));
                if(grid[i][j]!=1)
                {
                    g1.setColor(new Color(255,255,255,60));
                    g1.drawRect(j*boxSize,i*boxSize,boxSize,boxSize);

                }else
                {
                    g1.setColor(new Color(255,255,255,255));
                    g1.fillRect(j*boxSize,i*boxSize,boxSize,boxSize);
                    g1.setColor(Color.black);
                    g1.setStroke(new BasicStroke(4));
                    g1.drawRect(j*boxSize,i*boxSize,boxSize,boxSize);
                }


            }
        }

            // then render player
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
            case KeyEvent.VK_S:
                player.vY=0;
                player.vY=player.speed;
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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
