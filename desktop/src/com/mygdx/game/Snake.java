package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Snake  {
	
	private int x, y;
	private int bodySize;
	private DIRECTION direction = DIRECTION.RIGHT;
	private float timer;
	private final float MOVE_TIME;
	private final int SIZE;
	private final int STEP;
	
	Snake(int size, int speed) {
		MOVE_TIME = 1f / speed;
		SIZE = 16 * size;
		STEP = SIZE;
		this.timer = MOVE_TIME;
	}
	
	public void move(DIRECTION direction) {
		switch (direction) {
			case RIGHT: {
				this.x += STEP;
				//Gdx.app.log("SnakeGame", "move right");
				return;
			}
			case LEFT: {
				this.x -= STEP;
				return;
			}
			case UP: {
				this.y += STEP;
				return;
			}
			case DOWN: {
				this.y -= STEP;
				return;
			}
		}
		
	}
	
	public void updateDirection(DIRECTION newDirection) {
		switch (newDirection) {
			case RIGHT: {
				updateNotOpposite(newDirection, DIRECTION.LEFT);
			}
			break;
			case LEFT: {
				updateNotOpposite(newDirection, DIRECTION.RIGHT);
			}
			break;
			case UP: {
				updateNotOpposite(newDirection, DIRECTION.DOWN);
			}
			break;
			case DOWN: {
				updateNotOpposite(newDirection, DIRECTION.UP);
			}
			break;
		}
	}
	
	private void checkOutOfBounds() {
		if (x > Gdx.graphics.getWidth()) {
			x = 0;
		}
		if (x < 0) {
			x = Gdx.graphics.getWidth();
		}
		if (y > Gdx.graphics.getHeight()) {
			y = 0;
		}
		if (y < 0) {
			y = Gdx.graphics.getHeight();
		}
		
	}
	
	// If this.size == 0, snake can straight away change direction from left to right
	private void updateNotOpposite(DIRECTION newDirection, DIRECTION oppDirection) {
		if (this.direction != oppDirection || this.SIZE == 0) {
			this.direction = newDirection; 
		}
	}
	
	public void update(float delta) {
		timer -= delta;
		if (timer <= 0) {
			move(direction);
			checkOutOfBounds();
			timer = MOVE_TIME;
		}
	}
	
	public void draw(ShapeRenderer shape) {
		drawHead(shape);
	}

	private void drawHead (ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GOLD);
		shape.rect(x, y, SIZE, SIZE);
		shape.end();
	}
	
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	public int getSIZE() {
		return this.SIZE;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}



	
	
}
