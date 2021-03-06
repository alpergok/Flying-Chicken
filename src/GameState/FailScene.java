package GameState;

import Game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class FailScene extends GameState
{
    public static int currentLevel = 0;
    private int currentChoice = 0;
    private String[] options = {" TRY AGAIN", " MAIN MENU"};
    private BufferedImage image;

    public FailScene(GameStateManager gsm)
    {
        this.gsm = gsm;
        init();
    }
    
    @Override
    public void init()
    {
        try
        {
            image = ImageIO.read(new FileImageInputStream(new File("FailScene.png")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setFont(GamePanel.customFont);
        g.drawImage(image, 0, 0, null);
        
        // DRAW MENU OPTIONS
        for(int i = 0; i < options.length; i++)
        {
            if(i == currentChoice)
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);
            
            g.drawString(options[i], 561, 465 + i * 60);
        }
    }
    
    private void select()
    {
        if(currentChoice == 0)
        {   
            if(currentLevel == 1)
                gsm.setState(GameStateManager.LEVEL1);
            else if(currentLevel == 2)
                gsm.setState(GameStateManager.LEVEL2);
            else if(currentLevel == 3)
                gsm.setState(GameStateManager.LEVEL3);
        }
        
        if(currentChoice == 1)
        {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }   

    @Override
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_ENTER)
        {
            select();
        }
        
        if(k == KeyEvent.VK_UP)
        {
            currentChoice--;
            if(currentChoice == -1)
            {
                currentChoice = options.length - 1;
            }
        }
        
        if(k == KeyEvent.VK_DOWN)
        {
            currentChoice++;
            if(currentChoice == options.length)
            {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k)
    {
        
    }
}
