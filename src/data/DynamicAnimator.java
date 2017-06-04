package data;

import java.awt.Image;
import java.util.ArrayList;

public class DynamicAnimator implements Runnable {
	static int  nextthreadID = 0;
	int  threadID;
	int positionID;
	
	boolean askedToSuspend = false;
	JGamePanel parent;
	boolean isWaitingForObjectToApply = false;
	boolean isWaitingForSpriteToChange = false;
	
	ArrayList<DynamicObject> objectPool;
	ArrayList<DynamicObject> addCache;
	
	ArrayList<Integer> changePositionCache;
	ArrayList<Image> changeSpriteCache;
	int maxObjects = 128;

	
	public DynamicAnimator(JGamePanel parent) 
	{
		this.parent = parent;
		threadID = nextthreadID;
		positionID = 0;
		
		nextthreadID++;
		
		addCache = new ArrayList<DynamicObject>();
		objectPool = new ArrayList<DynamicObject>();
		changePositionCache = new ArrayList<Integer>();
		changeSpriteCache = new ArrayList<Image>();
	}
	
	public ArrayList<Integer> askToAddObject(DynamicObject dynObj)
	{
		ArrayList<Integer>  objID = new ArrayList<Integer>();
		if (addCache.size() < maxObjects)
		{
			addCache.add(dynObj);
			isWaitingForObjectToApply = true;
			objID.add(threadID);
			objID.add(positionID);
			positionID++;
		}
		else
		{
			objID.add(255);
			objID.add(255);
		}
		return objID;
	}
	
	public void askToSuspend()
	{
		askedToSuspend = true;
	}
	
	public boolean isWaitingForSuspend() 
	{
		return askedToSuspend;
	}
	
	public void getPoolFromCache()
	{
		for(DynamicObject each:addCache)
		{
			objectPool.add(each);
		}
		addCache.clear();
		isWaitingForObjectToApply = false;
	}
	
	public void askToChangeSprite(int position, Image sprite)
	{
		if (changePositionCache.contains(position))
			changeSpriteCache.set(changePositionCache.indexOf(position), sprite);
		else
		{
			changePositionCache.add(position);
			changeSpriteCache.add(sprite);
		}
		isWaitingForSpriteToChange = true;
	}
	
	public void changeSprites()
	{
		for (Image each:changeSpriteCache)
		{
			objectPool.get(changePositionCache.get(changeSpriteCache.indexOf(each))).setActiveSprite(each);
		}
		changePositionCache.clear();
		changeSpriteCache.clear();
		isWaitingForSpriteToChange = false;
	}
	
	public int getMaxObjects() {
		return maxObjects;
	}	

	public int getPositionID() {
		return positionID;
	}

	public ArrayList<DynamicObject> getObjectPool() {
		return objectPool;
	}

	@Override
	public void run() {
		while(!askedToSuspend)
		{
			if (isWaitingForObjectToApply)
			{
				synchronized (this) 
				{
					getPoolFromCache();
				}
			}
			if (isWaitingForSpriteToChange)
			{
				synchronized (this) 
				{
					changeSprites();
				}
			}
			try
			{
				parent.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}

	}

}
