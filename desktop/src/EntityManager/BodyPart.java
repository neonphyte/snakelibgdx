package EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BodyPart extends Entity {
	
	Snake snake;
	
	protected BodyPart(int x, int y, int size) {
		super(x, y, size);
	}
	
	protected void updateBodyPart(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	// Nothing
	protected void draw(ShapeRenderer shape) {
		
	}
	
	// Nothing
	public void drawBodyParts(ShapeRenderer shape) {
		
	}
	
	protected void draw(ShapeRenderer shape, int size) {
		//if (!(this.x == Snake.this.x && this.y == Snake.this.y)) {
			shape.begin(ShapeRenderer.ShapeType.Filled);
			shape.setColor(Color.GOLD);
			shape.rect(this.x, this.y, size, size);
			shape.end();
		//}
	}

}
