package data;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

public class JGamePanel extends JPanel {
	private ArrayList<DynamicObject> dynamicObjects;
	private ArrayList<StaticObject> staticObjects;
	private ArrayList<Character> characters;
	
	private int sizeX;
	private int sizeY;
	
	private int topLeftX;
	private int topLeftY;
	
	int scale = 5;
	
	public JGamePanel()
	{
		super();
		
		staticObjects = new ArrayList<StaticObject>();
		dynamicObjects = new ArrayList<DynamicObject>();
		characters = new ArrayList<Character>();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d= (Graphics2D)g;
		for (StaticObject each:staticObjects)
		{
			Image currentSprite = each.getSprite();
			g2d.drawImage(currentSprite, each.posX, each.posY, each.getSprite().getWidth(this) * scale, each.getSprite().getHeight(this) * scale, this);
		}
		
		for (DynamicObject each:dynamicObjects)
		{
			Image currentSprite = each.getActiveSprite();
			g2d.drawImage(currentSprite, each.posX - each.getActiveSprite().getWidth(this)*scale / 2, each.posY - each.getActiveSprite().getHeight(this) * scale, each.getActiveSprite().getWidth(this) * scale, each.getActiveSprite().getHeight(this) * scale, this);
		}
		
		for (Character each:characters)
		{
			Image currentSprite = each.getActiveSprite();
			g2d.drawImage(currentSprite, each.posX - each.getActiveSprite().getWidth(this)*scale / 2, each.posY - each.getActiveSprite().getHeight(this) * scale, each.getActiveSprite().getWidth(this) * scale, each.getActiveSprite().getHeight(this) * scale, this);
		}
	}
	
	public void addStaticObject(String spriteFile, int posX, int posY)
	{
		staticObjects.add(new StaticObject(spriteFile, posX, posY));
	}
	
	public void addDynamicObject(String spriteFile, int posX, int posY, String alternativeSpriteFile, String eliminatedSpriteFile, String actionSpriteFile, ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController)
	{
		dynamicObjects.add(new DynamicObject(spriteFile , posX, posY, alternativeSpriteFile, eliminatedSpriteFile, actionSpriteFile, this ,threadPool, threadController));
	}
	
	public void addCharacter(String name, String gun, boolean isPlayer, int posX, int posY, ArrayList<DynamicAnimator> threadPool, ArrayList<Thread> threadController)
	{
		characters.add(new Character(name, gun, isPlayer, posX, posY, this ,threadPool, threadController));
	}
}
