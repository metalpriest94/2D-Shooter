package data;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Character extends DynamicObject {
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
	
	private Image dyingSprite = null;
	private Image disabledSprite = null;

	public Character(String spriteFile, int posX, int posY, String alternativeSpriteFile, String eliminatedSpriteFile, String actionSpriteFile,JGamePanel parent, ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController) 
	{
		super(spriteFile, posX, posY, alternativeSpriteFile, eliminatedSpriteFile, actionSpriteFile,parent, threadPool, threadController);	
	}



	public Image getWalkFront1Sprite() {
		return walkFront1Sprite;
	}



	public void setWalkFront1Sprite(Image walkFront1Sprite) {
		this.walkFront1Sprite = walkFront1Sprite;
	}



	public Image getWalkFront2Sprite() {
		return walkFront2Sprite;
	}



	public void setWalkFront2Sprite(Image walkFront2Sprite) {
		this.walkFront2Sprite = walkFront2Sprite;
	}



	public Image getWalkBack1Sprite() {
		return walkBack1Sprite;
	}



	public void setWalkBack1Sprite(Image walkBack1Sprite) {
		this.walkBack1Sprite = walkBack1Sprite;
	}



	public Image getWalkBack2Sprite() {
		return walkBack2Sprite;
	}



	public void setWalkBack2Sprite(Image walkBack2Sprite) {
		this.walkBack2Sprite = walkBack2Sprite;
	}



	public Image getWalkLeft1Sprite() {
		return walkLeft1Sprite;
	}



	public void setWalkLeft1Sprite(Image walkLeft1Sprite) {
		this.walkLeft1Sprite = walkLeft1Sprite;
	}



	public Image getWalkLeft2Sprite() {
		return walkLeft2Sprite;
	}



	public void setWalkLeft2Sprite(Image walkLeft2Sprite) {
		this.walkLeft2Sprite = walkLeft2Sprite;
	}



	public Image getWalkRight1Sprite() {
		return walkRight1Sprite;
	}



	public void setWalkRight1Sprite(Image walkRight1Sprite) {
		this.walkRight1Sprite = walkRight1Sprite;
	}



	public Image getWalkRight2Sprite() {
		return walkRight2Sprite;
	}



	public void setWalkRight2Sprite(Image walkRight2Sprite) {
		this.walkRight2Sprite = walkRight2Sprite;
	}



	public void setStandFrontSprite(Image standFrontSprite) {
		this.standFrontSprite = standFrontSprite;
	}



	public void setShootDownSprite(Image shootDownSprite) {
		this.shootDownSprite = shootDownSprite;
	}



	public void setStandBackSprite(Image standBackSprite) {
		this.standBackSprite = standBackSprite;
	}



	public void setShootUpSprite(Image shootUpSprite) {
		this.shootUpSprite = shootUpSprite;
	}



	public void setStandLeftSprite(Image standLeftSprite) {
		this.standLeftSprite = standLeftSprite;
	}



	public void setShootLeftSprite(Image shootLeftSprite) {
		this.shootLeftSprite = shootLeftSprite;
	}



	public void setStandRightSprite(Image standRightSprite) {
		this.standRightSprite = standRightSprite;
	}



	public void setShootRightSprite(Image shootRightSprite) {
		this.shootRightSprite = shootRightSprite;
	}



	public void setDyingSprite(Image dyingSprite) {
		this.dyingSprite = dyingSprite;
	}



	public void setDisabledSprite(Image disabledSprite) {
		this.disabledSprite = disabledSprite;
	}
	
	
}
