package EntityManager;
import PlayerControlManager.InputController; // Import InputController from its package
import PlayerControlManager.KeyboardController; // Import KeyboardController from its package
import PlayerControlManager.DIRECTION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import IOManager.AudioManager;
public class Food extends Entity{
	
	public boolean alive = false;
	private Snake snake;
	private AudioManager audioManager;

	
	// Constructor for food
	protected Food(int size, Snake snake, KeyboardController controller, AudioManager audioManager) {
		super(size);
		this.snake = snake;
		this.audioManager = audioManager;
	}
	
	// Random spawning of food (Jordan's Part)
	//protected void updatePosition () {
	//	boolean covered;
	//	if (!alive) {
	//		do {
	//			covered = false;
	//			x = MathUtils.random((Gdx.graphics.getWidth() / snake.getSIZE()) - 1) * snake.getSIZE();
	//			y = MathUtils.random((Gdx.graphics.getHeight() / snake.getSIZE()) - 1) * snake.getSIZE();
    //           for (BodyPart bodyPart : snake.getBodyParts()) {
    //               if (bodyPart.getX() == x && bodyPart.getY() == y) {
    //                   covered = true;
    //                    break;
     //               }
     //           }
	//			
	//		} while (covered);
	//		alive = true;
	//	}
	//}
	
	// When collide with food, it will create a body part
	protected void checkFoodCollision() {
		if (alive && x == snake.getX() && y == snake.getY()) {
			snake.createBodyPart(snake.getX(), snake.getY());
			audioManager.listen(0);
			
			alive = false;
		}
		
	}
	
	// Draw food
	protected void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GREEN);
		shape.rect(x, y, snake.getSIZE(), snake.getSIZE());
		shape.end();
	}
	
	// Nothing
	protected void draw(ShapeRenderer shape, int size) {
		
	}
	
	// Nothing
	public void drawBodyParts(ShapeRenderer shape) {
		
	}
	
	public void reset() {
		alive = false;
	}
	
	public boolean isAlive() {
        return alive;
    }

}
