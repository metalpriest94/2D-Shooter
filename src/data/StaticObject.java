package data;

public class StaticObject extends GameplayObject {
	private final boolean blocking;
	public StaticObject(String spriteFile, int posX, int posY) {
		super(spriteFile, posX, posY);
		blocking = true;	
	}

}
