package com.mtr.beerdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class Splash implements Screen {
	SpriteBatch batch;
	OrthographicCamera camera;
	Sound zvukmtr;
    private Texture texture = new Texture(Gdx.files.internal("splash.jpg"));
    private Image splashImage = new Image(texture);
    Stage stage;
    Drop game;
	public Splash(Drop drop) {
		zvukmtr = Gdx.audio.newSound(Gdx.files.internal("mtr.mp3"));
		zvukmtr.play();
		this.game = drop;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
        camera.viewportHeight = 480;
        camera.viewportWidth = 800;
        camera.position.set(camera.viewportWidth * .5f,camera.viewportHeight * .5f, 0f);
        camera.update();
	}

	@Override
	public void render(float delta) {
		camera.update();

        Gdx.gl.glClearColor(196/255F, 178/255f, 91/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
       // batch.draw(texture, 0, 0, 800, 480);
        batch.end();
        stage.act();
        stage.draw();


	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		/*if(OptionsScreen.zOn){
			GameScreen.soundOn = true;
		}
		if(!OptionsScreen.zOn){
			GameScreen.soundOn = false;
		}
		if(OptionsScreen.mOn){
			GameScreen.musicOn = true;
		}
		if(!OptionsScreen.mOn){
			GameScreen.musicOn = false;
		}
		if(OptionsScreen.zvuk){
			GameScreen.soundOn = true;
		}
		if(!OptionsScreen.zvuk){
			GameScreen.soundOn = false;
		}
		if(OptionsScreen.muzika){
			GameScreen.musicOn = true;
		}
        if(!OptionsScreen.muzika){
        	GameScreen.musicOn = false;
        }*/

		stage = new Stage(new ExtendViewport(800, 480));
        stage.addActor(splashImage);
        zvukmtr.play();
        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                       ,Actions.fadeIn(0.5f),Actions.delay(3),Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new MainMenuScreen(game));
            }
        })));
    }


	

	@Override
	public void hide() {
        texture.dispose();
        stage.dispose();


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
        texture.dispose();
        stage.dispose();


	}

}
