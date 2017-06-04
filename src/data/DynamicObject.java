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
	
	private int handledBy = 255;
	private int position = 255;
	
	String imgPath = ("resources" + File.separator + "gfx" + File.separator);
	ArrayList<DynamicAnimator> threadPool;
	
	public DynamicObject(String spriteFile, int posX, int posY, String alternativeSpriteFile, String eliminatedSpriteFile, String actionSpriteFile,JGamePanel parent, ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController) 
	{
		super(spriteFile, posX, posY);
		
		this.threadPool = threadPool;
		this.activeSprite = this.sprite;
		blocking = false;
		try
		{
			this.alternativeSprite = ImageIO.read(new File(imgPath + alternativeSpriteFile));
			this.eliminatedSprite = ImageIO.read(new File(imgPath + eliminatedSpriteFile));
			this.actionSprite = ImageIO.read(new File(imgPath + actionSpriteFile));
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
			ArrayList<Integer> positioning = threadPool.get(threadCounter).askToAddObject(this);
			if (positioning.get(0) != 255 && positioning.get(1) != 255)
			{
				handledBy = positioning.get(0);
				position = positioning.get(1);
				foundFreeAnimator = true;
			}
			else				
				threadCounter++;
		}
		if (!foundFreeAnimator)
			threadPool.add(new DynamicAnimator(parent));
		
	}
	
	public void changeSprite(Image sprite, ArrayList<DynamicAnimator> threadPool)
	{
		threadPool.get(handledBy).askToChangeSprite(position, sprite);
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

	public int getHandledBy() {
		return handledBy;
	}

	public int getPosition() {
		return position;
	}
	
	
	
}
