package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Snake  {
	
	private int size, step;
	private float speed;
	private int x, y;
	private DIRECTION direction = DIRECTION.RIGHT;
	private float timer;
	private final float MOVE_TIME;
	
	Snake(int size, int speed, int step) {
		this.size = size;
		this.speed = speed;
		this.step = step;
		MOVE_TIME = 1f / speed;
		this.timer = MOVE_TIME;
	}
	
	public void move(DIRECTION direction) {
		switch (direction) {
			case RIGHT: {
				this.x += step;
				//Gdx.app.log("SnakeGame", "move right");
				return;
			}
			case LEFT: {
				this.x -= step;
				return;
			}
			case UP: {
				this.y += step;
				return;
			}
			case DOWN: {
				this.y -= step;
				return;
			}
		}
		
	}
	
	public void update(float delta) {
		timer -= delta;
		if (timer <= 0) {
			move(direction);
			timer = MOVE_TIME;
		}
	}
	
	public void draw(Batch batch, ShapeRenderer shape) {
		drawHead(shape);
	}

	private void drawHead (ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GOLD);
		shape.rect(x, y, size, size);
		shape.end();
	}
	
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}




	
	
}
