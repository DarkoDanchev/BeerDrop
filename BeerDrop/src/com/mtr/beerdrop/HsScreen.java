package com.mtr.beerdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HsScreen implements Screen {
	Drop game;
    OrthographicCamera camera;
    Preferences prefs;
    public int highscore;
    SpriteBatch batch;
    BitmapFont font;
	public HsScreen(Drop gam) {
		Gdx.input.setCatchBackKey(true);
		game = gam;
		//prefs = getPrefs();
		gam = this.game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true);
		font = new BitmapFont(Gdx.files.internal("font.fnt"),false);
		font.setColor(Color.MAROON);
	}

	@Override
	public void render(float delta) {
		Gdx.input.setCatchBackKey(true);
		if((Gdx.input.isKeyPressed(Keys.BACK))){
			game.setScreen(new MainMenuScreen(game));
			 //Timer timer = new Timer().schedule(game.setScreen(new MainMenuScreen(game)), 1);
		}
		
		camera.update();
		Gdx.gl.glClearColor(124/255F, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        prefs = Gdx.app.getPreferences("highscore");
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(MainMenuScreen.slika, 0, 0, 800, 480);
        font.draw(batch, "Arcade HighScore: " + GameScreen.getHs, 300, 400);
        font.draw(batch, "Free HighScore: " + GameScreen.getHs1, 300, 350);
        font.draw(batch, "Time HighScore: " + GameScreen.getHs4, 300, 300);
        font.draw(batch, "HardCore HighScore: " + GameScreen.getHs2, 300, 250);
        font.draw(batch, "Drunk HighScore: " + GameScreen.getHs3, 300, 200);
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	    font.dispose();
	    batch.dispose();

	}

}
