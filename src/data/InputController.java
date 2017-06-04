package data;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class InputController {
	JGamePanel parent;
	ArrayList<DynamicAnimator> threadPool;
	ArrayList<Character> characterList;
	
	Character player;
	
	InputMap input;
	ActionMap action;
	
	final boolean RELEASE = true;
	final boolean PRESS = false;
	
	AbstractAction shootDown;
	AbstractAction shootDownRelease;
	AbstractAction walkUp;
	AbstractAction walkUpRelease;
	AbstractAction walkDown;
	AbstractAction walkDownRelease;
	AbstractAction walkLeft;
	AbstractAction walkLeftRelease;
	AbstractAction walkRight;
	AbstractAction walkRightRelease;
	
	//Shooting
	int shootStat= 0;
	
	//Walking
	int walkStat= 0;
	int stepSpan = 5;
	int stepDist = 5;
	
	//Keys - Actions
	final String UPKEY = "up";
	final String UPKEYRELEASE = "upR";
	final String DOWNKEY = "down";
	final String DOWNKEYRELEASE = "downR";
	final String LEFTKEY = "left";
	final String LEFTKEYRELEASE = "leftR";
	final String RIGHTKEY = "right";
	final String RIGHTKEYRELEASE = "rightR";
	
	final String WKEY = "W";
	final String WKEYRELEASE = "WR";
	final String SKEY = "S";
	final String SKEYRELEASE = "SR";
	final String AKEY = "A";
	final String AKEYRELEASE = "AR";
	final String DKEY = "D";
	final String DKEYRELEASE = "DR";
	
	public InputController(JGamePanel parent, ArrayList<DynamicAnimator> threadPool) 
	{
		this.parent = parent;
		this.threadPool = threadPool;
		
		characterList = parent.getCharacters();
		
		for (Character each:characterList)
		{
			if (each.isPlayer())
			{
				System.out.println(each.getHandledBy() + " " + each.getPosition());
				player = (Character)threadPool.get(each.getHandledBy()).getObjectPool().get(each.getPosition());
				break;
			}
		}
		
		
		
		input = parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		action = parent.getActionMap();
		
		initiateActions();
		initiateMapping();
		
		
	}
	
	public ArrayList<Integer> getObjInLine(int direction)
	{
		ArrayList<Integer> obj = new ArrayList<Integer>();
		obj.add(255);
		obj.add(255);
		
		boolean objFound = false;
		boolean notDone = true;
		while (!objFound && notDone)
		{
			for (Character each:characterList)
			{
				int posX = each.getPosX();
				int posY = each.getPosY();
				
				int diffX = posX - player.getPosX();
				int diffY = posY - player.getPosY();
				
				System.out.println(diffX + " " + diffY);
						
				if ((direction ==3 && diffY < 500 && diffY > 0 && diffX > -500 && diffX < 500 && diffX < diffY && diffX > -diffY))
				{
					objFound = true;
					obj.clear();
					obj.add(each.getHandledBy());
					obj.add(each.getPosition());
				}

			}
			notDone = false;
		}
		return obj;
	}
	
	public void initiateActions()
	{
		shootDown = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(shootStat);
				if (shootStat == 0)
				{
					player.changeSprite(player.getShootDown1Sprite(), threadPool);
					ArrayList<Integer> objToShoot = getObjInLine(3);
					if (!(objToShoot.get(0) == 255) && !(objToShoot.get(1) == 255))
					{
						Character victim = (Character)threadPool.get(objToShoot.get(0)).getObjectPool().get(objToShoot.get(1));
						victim.receiveDamage(30, false);
						shootStat++;
					}
				}
				else if (shootStat == 2)
				{
					player.changeSprite(player.getShootDown2Sprite(), threadPool);
					ArrayList<Integer> objToShoot = getObjInLine(3);
					if (!(objToShoot.get(0) == 255) && !(objToShoot.get(1) == 255))
					{
						Character victim = (Character)threadPool.get(objToShoot.get(0)).getObjectPool().get(objToShoot.get(1));
						victim.receiveDamage(30, false);
						shootStat++;
					}
				}
				else
				{
					player.changeSprite(player.getStandFrontSprite(), threadPool);
					shootStat++;
					if (shootStat >= 4)
						shootStat = 0;
				}
			}
		};
		
		shootDownRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.changeSprite(player.getStandFrontSprite(), threadPool);
				shootStat= 0;
			}
		};
		
		
		
		
		
		walkUp = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.setPosY(player.getPosY()-stepDist);
				if (walkStat < stepSpan)
				{
					player.changeSprite(player.getWalkBack1Sprite(), threadPool);
					walkStat++;
				}
				else if (walkStat >= stepSpan*2 && walkStat < stepSpan*3)
				{
					player.changeSprite(player.getWalkBack2Sprite(), threadPool);
					walkStat++;
				}
				else if ((walkStat >= stepSpan && walkStat < stepSpan*2) || (walkStat >= stepSpan*3 && walkStat < stepSpan*4))
				{
					player.changeSprite(player.getStandBackSprite(), threadPool);
					walkStat++;
					if (walkStat >= stepSpan*4)
						walkStat = 0;
				}
			}
		};
		
		walkUpRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.changeSprite(player.getStandBackSprite(), threadPool);
				if (walkStat < stepSpan)
					walkStat = stepSpan;
				else
					walkStat = 0;
			}
		};
		
		walkDown = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.setPosY(player.getPosY()+stepDist);
				if (walkStat < stepSpan)
				{
					player.changeSprite(player.getWalkFront1Sprite(), threadPool);
					walkStat++;
				}
				else if (walkStat >= stepSpan*2 && walkStat < stepSpan*3)
				{
					player.changeSprite(player.getWalkFront2Sprite(), threadPool);
					walkStat++;
				}
				else if ((walkStat >= stepSpan && walkStat < stepSpan*2) || (walkStat >= stepSpan*3 && walkStat < stepSpan*4))
				{
					player.changeSprite(player.getStandFrontSprite(), threadPool);
					walkStat++;
					if (walkStat >= stepSpan*4)
						walkStat = 0;
				}
			}
		};
		
		walkDownRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.changeSprite(player.getStandFrontSprite(), threadPool);
				if (walkStat < stepSpan)
					walkStat = stepSpan;
				else
					walkStat = 0;
			}
		};
		
		walkLeft = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.setPosX(player.getPosX()-stepDist);
				if (walkStat < stepSpan)
				{
					player.changeSprite(player.getWalkLeft1Sprite(), threadPool);
					walkStat++;
				}
				else if (walkStat >= stepSpan*2 && walkStat < stepSpan*3)
				{
					player.changeSprite(player.getWalkLeft2Sprite(), threadPool);
					walkStat++;
				}
				else if ((walkStat >= stepSpan && walkStat < stepSpan*2) || (walkStat >= stepSpan*3 && walkStat < stepSpan*4))
				{
					player.changeSprite(player.getStandLeftSprite(), threadPool);
					walkStat++;
					if (walkStat >= stepSpan*4)
						walkStat = 0;
				}
			}
		};
		
		walkLeftRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.changeSprite(player.getStandLeftSprite(), threadPool);
				if (walkStat < stepSpan)
					walkStat = stepSpan;
				else
					walkStat = 0;
			}
		};
		
		walkRight = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.setPosX(player.getPosX()+stepDist);
				if (walkStat < stepSpan)
				{
					player.changeSprite(player.getWalkRight1Sprite(), threadPool);
					walkStat++;
				}
				else if (walkStat >= stepSpan*2 && walkStat < stepSpan*3)
				{
					player.changeSprite(player.getWalkRight2Sprite(), threadPool);
					walkStat++;
				}
				else if ((walkStat >= stepSpan && walkStat < stepSpan*2) || (walkStat >= stepSpan*3 && walkStat < stepSpan*4))
				{
					player.changeSprite(player.getStandRightSprite(), threadPool);
					walkStat++;
					if (walkStat >= stepSpan*4)
						walkStat = 0;
				}
			}
		};
		
		walkRightRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				player.changeSprite(player.getStandRightSprite(), threadPool);
				if (walkStat < stepSpan)
					walkStat = stepSpan;
				else
					walkStat = 0;
			}
		};
	}
	public void initiateMapping()
	{
		
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, PRESS), DOWNKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, RELEASE), DOWNKEYRELEASE);
		
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, PRESS), WKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, RELEASE), WKEYRELEASE);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, PRESS), SKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, RELEASE), SKEYRELEASE);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, PRESS), AKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, RELEASE), AKEYRELEASE);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, PRESS), DKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, RELEASE), DKEYRELEASE);

		action.put(DOWNKEY, shootDown);
		action.put(DOWNKEYRELEASE, shootDownRelease);
		
		action.put(WKEY, walkUp);
		action.put(WKEYRELEASE, walkUpRelease);
		action.put(SKEY, walkDown);
		action.put(SKEYRELEASE, walkDownRelease);
		action.put(AKEY, walkLeft);
		action.put(AKEYRELEASE, walkLeftRelease);
		action.put(DKEY, walkRight);
		action.put(DKEYRELEASE, walkRightRelease);
	}
}
