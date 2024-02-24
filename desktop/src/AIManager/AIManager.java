package AIManager;


import EntityManager.Food;
import EntityManager.Snake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import EntityManager.BodyPart;
import PlayerControlManager.InputController;

public class AIManager {
	
	public void updatePosition (Food food, Snake snake) {
		boolean covered;
		int x, y;
		if (!food.isAlive()) {
			do {
				covered = false;
				x = MathUtils.random((Gdx.graphics.getWidth() / snake.getSIZE()) - 1) * snake.getSIZE();
				y = MathUtils.random((Gdx.graphics.getHeight() / snake.getSIZE()) - 1) * snake.getSIZE();
                for (BodyPart bodyPart : snake.getBodyParts()) {
                    if (bodyPart.getX() == x && bodyPart.getY() == y) {
                        covered = true;
                        break;
                    }
                }
				
			} while (covered);
			food.setX(x); // Update food's x position
            food.setY(y); // Update food's y position
            food.alive = true; // Set food to alive

		}
	}

}
