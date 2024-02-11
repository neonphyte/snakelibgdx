package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Food {
	private int x, y;
	private boolean alive = false;
	Snake snake;
	
	Food(Snake snake) {
		this.snake = snake;
	}
	
	public void updatePosition () {
		boolean covered;
		if (!alive) {
			do {
				covered = false;
				x = MathUtils.random((Gdx.graphics.getWidth() / snake.getSIZE()) - 1) * snake.getSIZE();
				y = MathUtils.random((Gdx.graphics.getHeight() / snake.getSIZE()) - 1) * snake.getSIZE();
				if (x == snake.getX() || y == snake.getY()) {
					covered = true;
				}
				alive = true;
				
			} while (covered);
		}
	}
	
	public void checkFoodCollision() {
		if (alive && x == snake.getX() && y == snake.getY()) {
			alive = false;
		}
	}
	
	public void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.rect(x, y, snake.getSIZE(), snake.getSIZE());
		shape.end();
	}

}
