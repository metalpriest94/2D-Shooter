package data;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DynamicObject extends GameplayObject {
	private boolean blocking;
	private Image alternativeSprite;
	private Image eliminatedSprite;
	private Image actionSprite;
	
	private Image activeSprite;
	
	private int status = 0;
	
	public DynamicObject(String spriteFile, int posX, int posY, String alternativeSpriteFile, String eliminatedSpriteFile, String actionSpriteFile,JGamePanel parent, ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController) 
	{
		super(spriteFile, posX, posY);
		this.activeSprite = this.sprite;
		blocking = false;
		try
		{
			this.alternativeSprite = ImageIO.read(new File(alternativeSpriteFile));
			this.eliminatedSprite = ImageIO.read(new File(eliminatedSpriteFile));
			this.actionSprite = ImageIO.read(new File(actionSpriteFile));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		if (threadPool.size() == 0)
		{
			threadPool.add(new DynamicAnimator(parent));
			threadController.add(new Thread(threadPool.get(0)));
			threadController.get(0).start();
			
		}
		
		boolean foundFreeAnimator = false;
		int threadCounter = 0;
		while (threadCounter < threadPool.size() && !foundFreeAnimator)
		{
			if (threadPool.get(threadCounter).askToAddObject(this))
				foundFreeAnimator = true;
			else
				threadCounter++;
		}
		if (!foundFreeAnimator)
			threadPool.add(new DynamicAnimator(parent));
		
	}

	public boolean isBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	public void setAlternativeSprite(Image alternativeSprite) {
		this.alternativeSprite = alternativeSprite;
	}

	public void setEliminatedSprite(Image eliminatedSprite) {
		this.eliminatedSprite = eliminatedSprite;
	}

	public void setActionSprite(Image actionSprite) {
		this.actionSprite = actionSprite;
	}
	
	public void setActiveSprite(Image sprite) {
		this.activeSprite = sprite;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Image getAlternativeSprite() {
		return alternativeSprite;
	}

	public Image getEliminatedSprite() {
		return eliminatedSprite;
	}

	public Image getActionSprite() {
		return actionSprite;
	}

	public Image getActiveSprite() {
		return activeSprite;
	}
	
	
}
