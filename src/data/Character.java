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
	
	private Image standFrontSprite = null;
	private Image walkFront1Sprite = null;
	private Image walkFront2Sprite = null;
	private Image shootDownSprite = null;
	
	private Image standBackSprite = null;
	private Image walkBack1Sprite = null;
	private Image walkBack2Sprite = null;
	private Image shootUpSprite = null;
	
	private Image standLeftSprite = null;
	private Image walkLeft1Sprite = null;
	private Image walkLeft2Sprite = null;
	private Image shootLeftSprite = null;
	
	private Image standRightSprite = null;
	private Image walkRight1Sprite = null;
	private Image walkRight2Sprite = null;
	private Image shootRightSprite = null;
	
	private Image deadSprite = null;
	private Image disabledSprite = null;
	private Image crippledSprite = null;
	private Image gibbedSprite = null;

	public Character(String name, String gun, boolean isPlayer, int posX, int posY, JGamePanel parent,ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController) 
	{
		super("dummy.png", posX, posY, "dummy.png", "dummy.png", "dummy.png", parent, threadPool, threadController);
		this.name = name;
		this.gun = gun;
		this.isPlayer = isPlayer;
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
			shootDownSprite = ImageIO.read(new File(imgPath + name + "Down" + gun + "Shoot1.png"));
			
			standBackSprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Stand.png"));
			walkBack1Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Walk1.png"));
			walkBack2Sprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Walk2.png"));
			shootUpSprite = ImageIO.read(new File(imgPath + name + "Up" + gun + "Stand.png"));
			
			standLeftSprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Stand.png"));
			walkLeft1Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Walk1.png"));
			walkLeft2Sprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Walk2.png"));
			shootLeftSprite = ImageIO.read(new File(imgPath + name + "Left" + gun + "Stand.png"));
			
			standRightSprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Stand.png"));
			walkRight1Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Walk1.png"));
			walkRight2Sprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Walk2.png"));
			shootRightSprite = ImageIO.read(new File(imgPath + name + "Right" + gun + "Stand.png"));
			
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

	public Image getStandFrontSprite() {
		return standFrontSprite;
	}

	public Image getWalkFront1Sprite() {
		return walkFront1Sprite;
	}

	public Image getWalkFront2Sprite() {
		return walkFront2Sprite;
	}

	public Image getShootDownSprite() {
		return shootDownSprite;
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

	public Image getShootUpSprite() {
		return shootUpSprite;
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

	public Image getShootLeftSprite() {
		return shootLeftSprite;
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

	public Image getShootRightSprite() {
		return shootRightSprite;
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


}
