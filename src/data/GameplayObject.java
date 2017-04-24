package data;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameplayObject {
	int posX = 0;
	int posY = 0;

	Image sprite = null;
	
	public GameplayObject(String spriteFile, int posX, int posY)
	{
		try
		{
			this.sprite = ImageIO.read(new File(spriteFile));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public Image getSprite() {
		return sprite;
	}
}
