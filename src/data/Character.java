package data;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Character extends DynamicObject {
	
	private String name;
	private String gun;
	private boolean isPlayer;
	
	private int health;
	private boolean isDead;
	
	private Image standFrontSprite = null;
	private Image walkFront1Sprite = null;
	private Image walkFront2Sprite = null;
	private Image shootDown1Sprite = null;
	private Image shootDown2Sprite = null;
	
	private Image standBackSprite = null;
	private Image walkBack1Sprite = null;
	private Image walkBack2Sprite = null;
	private Image shootUp1Sprite = null;
	private Image shootUp2Sprite = null;
	
	private Image standLeftSprite = null;
	private Image walkLeft1Sprite = null;
	private Image walkLeft2Sprite = null;
	private Image shootLeft1Sprite = null;
	private Image shootLeft2Sprite = null;
	
	private Image standRightSprite = null;
	private Image walkRight1Sprite = null;
	private Image walkRight2Sprite = null;
	private Image shootRight1Sprite = null;
	private Image shootRight2Sprite = null;
	
	private Image deadSprite = null;
	private Image disabledSprite = null;
	private Image crippledSprite = null;
	private Image gibbedSprite = null;

	public Character(String name, String gun, boolean isPlayer, int posX, int posY, JGamePanel parent,ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController) 
	{
		super("chad.png", posX, posY, "dummy.png", "dummy.png", "dummy.png", parent, threadPool, threadController);
		this.name = name;
		this.gun = gun;
		this.isPlayer = isPlayer;
		this.health = 100;
		this.isDead = false;
		getSprites(name, gun);
		
	}

	public void getSprites(String name, String gun)//name always lowercase, gun always capital
	{
		String imgPath = ("resources" + File.separator + "gfx" + File.separator);
		
		try
		{
			standFrontSprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Stand.png"));
			walkFront1Sprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Walk1.png"));
			walkFront2Sprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Walk2.png"));
			shootDown1Sprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Shoot1.png"));
			shootDown2Sprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Shoot2.png"));
			
			standBackSprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Stand.png"));
			walkBack1Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Walk1.png"));
			walkBack2Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Walk2.png"));
			shootUp1Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Shoot1.png"));
			shootUp2Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Shoot2.png"));
			
			standLeftSprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Stand.png"));
			walkLeft1Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Walk1.png"));
			walkLeft2Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Walk2.png"));
			shootLeft1Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Shoot1.png"));
			shootLeft2Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Shoot2.png"));
			
			standRightSprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Stand.png"));
			walkRight1Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Walk1.png"));
			walkRight2Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Walk2.png"));
			shootRight1Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Shoot1.png"));
			shootRight2Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Shoot2.png"));
			
			deadSprite = ImageIO.read(new File(imgPath + name + "Dead.png"));
			disabledSprite = ImageIO.read(new File(imgPath + name + "Crippled.png")); //replace with Disabled Sprite
			crippledSprite = ImageIO.read(new File(imgPath + name + "Crippled.png"));
			gibbedSprite = ImageIO.read(new File(imgPath + name + "Gibbed.png"));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void receiveDamage(int amount, boolean isFromExplosion)
	{
		health -= amount;
		if (health <= 0 && isFromExplosion)
			die(true);
		else if (health <= 0)
			die(false);
	}
	
	public void die(boolean isGibbed)
	{
		if(isGibbed)
			this.changeSprite(gibbedSprite, threadPool);
		else
			this.changeSprite(deadSprite, threadPool);
		isDead = true;
	}
	

	public Image getStandFrontSprite() {
		return standFrontSprite;
	}

	public Image getWalkFront1Sprite() {
		return walkFront1Sprite;
	}

	public Image getWalkFront2Sprite() {
		return walkFront2Sprite;
	}

	public Image getShootDown1Sprite() {
		return shootDown1Sprite;
	}
	
	public Image getShootDown2Sprite() {
		return shootDown2Sprite;
	}

	public Image getStandBackSprite() {
		return standBackSprite;
	}

	public Image getWalkBack1Sprite() {
		return walkBack1Sprite;
	}

	public Image getWalkBack2Sprite() {
		return walkBack2Sprite;
	}

	public Image getShootUp1Sprite() {
		return shootUp1Sprite;
	}
	
	public Image getShootUp2Sprite() {
		return shootUp2Sprite;
	}

	public Image getStandLeftSprite() {
		return standLeftSprite;
	}

	public Image getWalkLeft1Sprite() {
		return walkLeft1Sprite;
	}

	public Image getWalkLeft2Sprite() {
		return walkLeft2Sprite;
	}

	public Image getShootLeft1Sprite() {
		return shootLeft1Sprite;
	}
	
	public Image getShootLeft2Sprite() {
		return shootLeft2Sprite;
	}

	public Image getStandRightSprite() {
		return standRightSprite;
	}

	public Image getWalkRight1Sprite() {
		return walkRight1Sprite;
	}

	public Image getWalkRight2Sprite() {
		return walkRight2Sprite;
	}

	public Image getShootRight1Sprite() {
		return shootRight1Sprite;
	}
	
	public Image getShootRight2Sprite() {
		return shootRight2Sprite;
	}

	public Image getDeadSprite() {
		return deadSprite;
	}

	public Image getDisabledSprite() {
		return disabledSprite;
	}

	public Image getCrippledSprite() {
		return crippledSprite;
	}

	public Image getGibbedSprite() {
		return gibbedSprite;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public boolean isDead() {
		return isDead;
	}


}
