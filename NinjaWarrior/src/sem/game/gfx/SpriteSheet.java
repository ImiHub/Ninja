package sem.game.gfx;



import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	private BufferedImage sheet;
	private int xx,yy;
	public SpriteSheet(String path,int x1,int y1)
	{
		try
		{
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
		xx=x1;
		yy=y1;
		
		
	}
	
	public BufferedImage getSprite(int x,int y)
	{
		return sheet.getSubimage(x*xx-xx, y*yy-yy, xx, yy);
	}
	
}
