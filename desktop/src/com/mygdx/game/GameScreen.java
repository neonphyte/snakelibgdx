package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends ScreenAdapter {
	
	private GlyphLayout layout;
	private BitmapFont bitmapFont;
	
	private Batch batch;
	private ShapeRenderer shape;
	private Snake snake;
	Controller controller;
	Food food;
	
	private static final int SNAKE_SIZE = 32;
	private static final int SNAKE_STEP = SNAKE_SIZE;
	private static final float SNAKE_SPEED = 1F;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		snake = new Snake(2,2);
		controller = new Controller();
		food = new Food(snake);
		
		layout = new GlyphLayout();
		bitmapFont = new BitmapFont();
	}
	
	@Override
	public void render(float delta) {
		snake.updateDirection(controller.queryInput());
		snake.update(delta);
		food.updatePosition();
		food.checkFoodCollision();
		clearScreen();
		
		batch.begin();
		//layout.setText(bitmapFont, "Hello");
		//bitmapFont.draw(batch, layout, Gdx.graphics.getHeight() / 2 - layout.width /2,  Gdx.graphics.getHeight() /2 - layout.height /2);
        snake.draw(shape);
        food.draw(shape);
        shape.end();
        batch.end();
		
	}
	
	private void clearScreen() {
		//ScreenUtils.clear(1,0,0,1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	

	
	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
	}

}
