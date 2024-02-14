package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Food {
	private int x, y;
	private boolean alive = false;
	Snake snake;
	Controller controller;
	
	// Constructor for food
	Food(Snake snake, Controller controller) {
		this.snake = snake;
		this.controller = controller;
	}
	
	// Random spawning of food
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
			snake.createBodyPart(snake.getX(), snake.getY());
			alive = false;
		}
	}
	
	// Draw food
	public void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GREEN);
		shape.rect(x, y, snake.getSIZE(), snake.getSIZE());
		shape.end();
	}
	
	public void reset() {
		alive = false;
	}

}
