package EntityManager;

import java.util.ArrayList;
import PlayerControlManager.InputController; // Import InputController from its package
import PlayerControlManager.KeyboardController; // Import KeyboardController from its package
import PlayerControlManager.DIRECTION;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import AIManager.AIManager;
import CollisionManager.CollisionManager;
import IOManager.AudioManager;

public class EntityManager {
	
	public ArrayList<Entity> entityList;
	private Snake snake;
	private Food food;
	private KeyboardController controller;
	private AudioManager audioManager;
	private CollisionManager collisionManager;
	private InputController keyboardController;
	private AIManager aiManager;
	
	public EntityManager() {
		this.entityList = new ArrayList<Entity>();
		controller = new KeyboardController();
		audioManager = new AudioManager();
		keyboardController = new KeyboardController();
		this.snake = new Snake(2,3, keyboardController);
		this.food = new Food(2, snake, controller, audioManager);
		entityList.add(this.snake);
		entityList.add(this.food);
		collisionManager = new CollisionManager();
		aiManager = new AIManager();
	}

	public void draw(ShapeRenderer shape) {
		for (Entity entity : entityList) {
            entity.draw(shape);
        }
	}
	
	public void userMovement(float delta) {
		snake.update(delta);
	}
	
	public void randomSpawn() {
		aiManager.updatePosition(food, snake);
	}
	
	protected void snakeToFood() {
		food.checkFoodCollision();
	}
	
	protected void snakeToSnake() {
        // Check snake body collision
        snake.checkBodyCollision();
    }
	
	protected void checkCollisions() {
		snakeToSnake();
		snakeToFood();
       
    }

  
	
}
