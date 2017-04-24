package data;

import java.util.ArrayList;

public class DynamicAnimator implements Runnable {
	boolean askedToSuspend = false;
	JGamePanel parent;
	boolean waitingForObjectToApply = false;
	
	ArrayList<DynamicObject> objectPool;
	ArrayList<DynamicObject> cachePool;
	int maxObjects = 128;
	
	public DynamicAnimator(JGamePanel parent) 
	{
		this.parent = parent;
		cachePool = new ArrayList<DynamicObject>();
		objectPool = new ArrayList<DynamicObject>();
	}
	
	public boolean askToAddObject(DynamicObject dynObj)
	{
		if (cachePool.size() < maxObjects)
		{
			cachePool.add(dynObj);
			waitingForObjectToApply = true;
			return true;
		}
		else
			return false;
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
		objectPool.clear();
		for(DynamicObject each:cachePool)
		{
			objectPool.add(each);
		}
		waitingForObjectToApply = false;
	}
	
	@Override
	public void run() {
		while(!askedToSuspend)
		{
			System.out.println(objectPool.size());
			for (DynamicObject each:objectPool)
			{
				if(each.getStatus() == 0)
				{
					each.setActiveSprite(each.getAlternativeSprite());
					each.setStatus(1);
					System.out.println("!1");
				}
				else
				{
					each.setActiveSprite(each.getSprite());
					each.setStatus(0);
					System.out.println("!2");
				}
			}
			if (waitingForObjectToApply)
			{
				getPoolFromCache();
			}
			try
			{
				parent.repaint();
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}

	}

}
