package sem.game.gfx;


import java.awt.image.BufferedImage;

public class Sprite
{
	public SpriteSheet sheet;
	
	public BufferedImage image;
	
	public Sprite(SpriteSheet sheet,int x,int y)
	{
		this.sheet=sheet;
		image = sheet.getSprite(x, y);
	}
	
	public BufferedImage getBufferedImage()
	{
		return image;
	}

}
