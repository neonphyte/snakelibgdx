package CollisionManager;
import IOManager.AudioManager;
import Simulation.STATE;

import com.badlogic.gdx.Gdx;

import EntityManager.BodyPart;
import EntityManager.Entity;
import EntityManager.Food;
import EntityManager.Snake;

public class CollisionManager {

    public static void checkCollisions(Snake snake, Food food, AudioManager audioManager) {
        checkFoodCollision(snake, food, audioManager);
        checkBodyCollision(snake);
        checkOutOfBounds(snake);
    }

    private static void checkFoodCollision(Snake snake, Food food, AudioManager audioManager) {
        if (food.isAlive() && food.getX() == snake.getX() && food.getY() == snake.getY()) {
            snake.createBodyPart(snake.getX(), snake.getY());
            audioManager.listen(0); // Example method call to play sound
            food.reset();
        }
    }

    private static void checkBodyCollision(Snake snake) {
        for (BodyPart bodyPart : snake.getBodyParts()) {
            if (bodyPart.getX() == snake.getX() && bodyPart.getY() == snake.getY()) {
                snake.setState(STATE.PLAYING);
            }
        }
    }
    
    

    private static void checkOutOfBounds(Entity entity) {
        int x = entity.getX();
        int y = entity.getY();
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        if (x < 0 || x >= screenWidth || y < 0 || y >= screenHeight) {
            entity.reset();
        }
    }
}

