package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;

public class Snake  {
	
	private int x, y;
	private int oldX, oldY;
	private DIRECTION direction = DIRECTION.RIGHT;
	private float timer;
	private final float MOVE_TIME;
	private final int SIZE;
	private final int STEP;
	private STATE state = STATE.PLAYING;
//	private boolean directionSet = false;
	private Array<BodyPart> bodyParts = new Array<>();
	
	// Constructor for Snake Object that takes in size and speed
	Snake(int size, int speed) {
		MOVE_TIME = 1f / speed;
		SIZE = 16 * size;
		STEP = SIZE;
		this.timer = MOVE_TIME;
	}
	
	// By default w/o touching any keys, Snake will move right
	public void move(DIRECTION direction) {
		this.oldX = this.x;
		this.oldY = this.y;
		switch (direction) {
			case RIGHT: {
				this.x += STEP;
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
	
	// If directionSet is true, snake can go left and right instantly
	public void updateDirection(DIRECTION newDirection) {
		if (this.direction != newDirection) {
			//directionSet = true;
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
		
	}
	
	
	private void checkOutOfBounds() {
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
	public void checkBodyCollision() {
		for (BodyPart bodyPart : bodyParts) {
			if (bodyPart.x == this.x && bodyPart.y == this.y) {
				state = STATE.GAMEOVER;
			}
		}
	}
	
	// If this.size != 0, snake can straight away change direction from left to right
	private void updateNotOpposite(DIRECTION newDirection, DIRECTION oppDirection) {
		if (this.direction != oppDirection || bodyParts.size == 0) {
			this.direction = newDirection; 
		}
	}
	
	public STATE update(float delta) {
		timer -= delta;
		if (timer <= 0) {
			timer = MOVE_TIME;
			move(direction);
			checkOutOfBounds(); 
			updateBodyParts();
			checkBodyCollision();
			//directionSet = false;
			
		}
		return state;
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
	
	
	public int getSIZE() {
		return this.SIZE;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	//reset()
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
	
	public void createBodyPart(int x, int y) {
		BodyPart bodyPart = new BodyPart();
		bodyPart.updateBodyPart(x, y);
		bodyParts.insert(0, bodyPart);
	}
	
	public void drawBodyParts(ShapeRenderer shape) {
		for (BodyPart bodyPart : bodyParts) {
			bodyPart.draw(shape);
		}
	}
	
	public void updateBodyParts() {
		if (bodyParts.size > 0) {
			
			BodyPart bodyPart = bodyParts.removeIndex(0);
			bodyPart.updateBodyPart(oldX, oldY);
			bodyParts.add(bodyPart);
		}
	}
	
	private class BodyPart {
		int x, y;
		
		
		public void updateBodyPart(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void draw(ShapeRenderer shape) {
			if (!(this.x == Snake.this.x && this.y == Snake.this.y)) {
				shape.begin(ShapeRenderer.ShapeType.Filled);
				shape.setColor(Color.GOLD);
				shape.rect(this.x, this.y, Snake.this.SIZE, Snake.this.SIZE);
				shape.end();
			}
		}


	}
	
}
