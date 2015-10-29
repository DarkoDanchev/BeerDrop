package com.mtr.beerdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoreScreen implements Screen {
    Drop game;
    OrthographicCamera camera;
    SpriteBatch batch;
	public MoreScreen(Drop gam) {
		Gdx.input.setCatchBackKey(true);
		this.game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true);
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
       
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(MainMenuScreen.slika, 0, 0, 800, 480);
        game.font.draw(batch, "Visit us on fb: https://www.facebook.com/MegaTechRevolution", 25, 200);
        game.font.draw(batch,"Or on yt: http://www.youtube.com/user/MegaTechRevolution",25,100);
        
        //game.font.setColor(Color.GREEN);
   
        batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
