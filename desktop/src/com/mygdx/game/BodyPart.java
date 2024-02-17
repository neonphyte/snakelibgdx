package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BodyPart {
	
	int x, y, size;
	Snake snake;
	
	
	public void updateBodyPart(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void draw(ShapeRenderer shape, int size) {
		//if (!(this.x == Snake.this.x && this.y == Snake.this.y)) {
			shape.begin(ShapeRenderer.ShapeType.Filled);
			shape.setColor(Color.GOLD);
			shape.rect(this.x, this.y, size, size);
			shape.end();
		//}
	}

}
