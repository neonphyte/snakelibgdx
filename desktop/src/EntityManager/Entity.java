package EntityManager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity  {
	
	protected int x;
	protected int y;
	protected int size;
	private boolean alive = false;

	
	protected Entity(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	protected Entity(int size) {
		this.size = size;
	}
	
	protected abstract void draw(ShapeRenderer shape);
	protected abstract void draw(ShapeRenderer shape, int size);
	public abstract void drawBodyParts(ShapeRenderer shape);
	
	public void reset() {
		alive = false;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	protected int getSize() {
		return size;
	}


	protected void setSize(int size) {
		this.size = size;
	}

	

}
