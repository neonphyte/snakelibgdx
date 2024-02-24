package EntityManager;

import PlayerControlManager.InputController; // Import InputController from its package
import PlayerControlManager.KeyboardController; // Import KeyboardController from its package
import Simulation.STATE;
import PlayerControlManager.DIRECTION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
public class Snake extends Entity  {
	
	private int oldX, oldY;
	private InputController inputController; 
	private DIRECTION direction = DIRECTION.RIGHT;
	private float timer;
	private final float MOVE_TIME;
	private final int SIZE;
	private final int STEP;
	private STATE state = STATE.PLAYING;
	private Array<BodyPart> bodyParts = new Array<>();
	
	// Constructor for Snake Object that takes in size and speed
	public Snake(int size, int speed, InputController inputController) {
        super(size);
        this.inputController = inputController;
		MOVE_TIME = 1f / speed;
		SIZE = 16 * size;
		STEP = SIZE;
		this.timer = MOVE_TIME;
	}
	
	
	
	protected void checkOutOfBounds() {
		// When Snake hits the right side boundary, Snake will come out from left side boundary
		if (x > Gdx.graphics.getWidth()) {
			x = 0;
		}
		// When Snake hits the left side boundary, Snake will come out from right side boundary
		if (x < 0) {
			x = Gdx.graphics.getWidth() - STEP;
		}
		// When Snake hits the top side boundary, Snake will come out from bottom side boundary
		if (y > Gdx.graphics.getHeight()) {
			y = 0;
		}
		// When Snake hits the bottom side boundary, Snake will come out from top side boundary
		if (y < 0) {
			y = Gdx.graphics.getHeight() - STEP;
		}
		
	}
	
	// Method to check for body collision
	protected void checkBodyCollision() {
		for (BodyPart bodyPart : bodyParts) {
			if (bodyPart.x == this.x && bodyPart.y == this.y) {
				state = STATE.GAMEOVER;
			}
		}
	}
	
	protected void move() {
        // Store the current position before moving
        this.oldX = this.x;
        this.oldY = this.y;

        // Update the position based on the current direction
        switch (direction) {
            case RIGHT:
                this.x += STEP;
                break;
            case LEFT:
                this.x -= STEP;
                break;
            case UP:
                this.y += STEP;
                break;
            case DOWN:
                this.y -= STEP;
                break;
            default:
                // Handle any other cases (e.g., NONE)
                break;
        }
    }
	
	protected void updateDirection(DIRECTION newDirection) {
        if (this.direction != newDirection) {
            // Check if the new direction is not opposite to the current direction
            switch (newDirection) {
                case RIGHT:
                    updateNotOpposite(newDirection, DIRECTION.LEFT);
                    break;
                case LEFT:
                    updateNotOpposite(newDirection, DIRECTION.RIGHT);
                    break;
                case UP:
                    updateNotOpposite(newDirection, DIRECTION.DOWN);
                    break;
                case DOWN:
                    updateNotOpposite(newDirection, DIRECTION.UP);
                    break;
                default:
                    // Handle any other cases
                    break;
            }
        }
    }

    private void updateNotOpposite(DIRECTION newDirection, DIRECTION oppDirection) {
        // Check if the new direction is not opposite to the current direction
        if (this.direction != oppDirection || bodyParts.size == 0) {
            this.direction = newDirection;
        }
    }
	
	// What will happen when the state change
	public STATE update(float delta) {
		DIRECTION newDirection = inputController.queryInput(); 
	    updateDirection(newDirection);
		timer -= delta;
		if (timer <= 0) {
			timer = MOVE_TIME;
			move();
			checkOutOfBounds(); 
			updateBodyParts();
			checkBodyCollision();
			// directionSet = false;
			
		}
		return state;
	}
	
	public void setState(STATE state) {
        this.state = state;
    }
	
	// Nothing
	protected void draw(ShapeRenderer shape, int size) {
		
	}
	
	// Draw the head
	protected void draw(ShapeRenderer shape) {
		drawHead(shape);
	}
	
	// Create head, define color, and type
	private void drawHead (ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GOLD);
		shape.rect(x, y, SIZE, SIZE);
		shape.end();
	}
	
	// Getter for SIZE
	public int getSIZE() {
		return this.SIZE;
	}
	
	// Getter for X
	public int getX() {
		return this.x;
	}
	
	// Getter for Y
	public int getY() {
		return this.y;
	}
	
	//Reset() the bodypart when state change from GAMEOVER to PLAYING
	public void clearBodyPart() {
		x = 0;
		y = 0;
		oldX = 0;
		oldY = 0;  	
		bodyParts.clear();
		timer = MOVE_TIME;
		direction = DIRECTION.RIGHT;
		state = STATE.PLAYING;
	}
	
	// Create body part then update into the array
	public void createBodyPart(int x, int y) {
		BodyPart bodyPart = new BodyPart(2,2,2);
		bodyPart.updateBodyPart(x, y, SIZE);
		bodyParts.insert(0, bodyPart);
	}
	
	// Update the body part
	protected void updateBodyParts() {
		if (bodyParts.size > 0) {
			
			BodyPart bodyPart = bodyParts.removeIndex(0);
			bodyPart.updateBodyPart(oldX, oldY, SIZE);
			bodyParts.add(bodyPart);
		}
	}
	
	// Draw the body part using a for loop to access the array
	public void drawBodyParts(ShapeRenderer shape) {
		for (BodyPart bodyPart : bodyParts) {
			bodyPart.draw(shape, SIZE);
		}
	}
	
	public Array<BodyPart> getBodyParts() {
	        return bodyParts;
	 }
	
	
	
}
