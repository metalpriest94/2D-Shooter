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
	
	InputMap input;
	ActionMap action;
	
	final boolean RELEASE = true;
	final boolean PRESS = false;
	
	AbstractAction shootDown;
	AbstractAction shootDownRelease;
	AbstractAction walkDown;
	AbstractAction walkDownRelease;
	
	int testStat= 0;
	
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
		
		input = parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		action = parent.getActionMap();
		
		initiateActions();
		initiateMapping();
		
		
	}
	
	public void initiateActions()
	{
		shootDown = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (testStat == 0 )
				{
					threadPool.get(0).getObjectPool().get(0).changeSprite(threadPool.get(0).getObjectPool().get(0).getAlternativeSprite(), threadPool);
					testStat = 1;
				}
				else if (testStat == 1)
				{
					threadPool.get(0).getObjectPool().get(0).changeSprite(threadPool.get(0).getObjectPool().get(0).getEliminatedSprite(), threadPool);
					testStat = 2;
				}
				else
				{
					threadPool.get(0).getObjectPool().get(0).changeSprite(threadPool.get(0).getObjectPool().get(0).getActionSprite(), threadPool);
					testStat = 0;
				}
			}
		};
		
		shootDownRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				threadPool.get(0).getObjectPool().get(0).changeSprite(threadPool.get(0).getObjectPool().get(0).getSprite(), threadPool);
				testStat= 0;
			}
		};
		
		walkDown = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				threadPool.get(0).getObjectPool().get(0).setPosY(threadPool.get(0).getObjectPool().get(0).getPosY()+5);
			}
		};
		
		walkDownRelease = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) 
			{

			}
		};
	}
	public void initiateMapping()
	{
		
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, PRESS), DOWNKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, RELEASE), DOWNKEYRELEASE);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, PRESS), SKEY);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, RELEASE), SKEYRELEASE);

		action.put(DOWNKEY, shootDown);
		action.put(DOWNKEYRELEASE, shootDownRelease);
		action.put(SKEY, walkDown);
		action.put(SKEYRELEASE, walkDownRelease);
	}
}
