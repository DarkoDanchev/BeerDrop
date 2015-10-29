package com.mtr.beerdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;

public class ModeScreen implements Screen {
	TextButton arcade;
	TextButton free;
	TextButton time;
	TextButton hc;
	TextButton drunk;
	Texture pozadina;
	Texture pozadina1;
	SpriteBatch batch;
	TextureAtlas atlas;
	OrthographicCamera camera;
	Stage stage;
	Table tab;
	Skin skin;
    
	Drop game;
	
	public ModeScreen(Drop gam) {
		Gdx.input.setCatchBackKey(true);
		this.game = gam;
		pozadina = new Texture(Gdx.files.internal("playbackground.png"));
		pozadina1 = new Texture(Gdx.files.internal("Modes.png"));
		atlas = new TextureAtlas("buttons_mode/buttons_mode.pack");
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.input.setCatchBackKey(true);
		if((Gdx.input.isKeyPressed(Keys.BACK))){
			game.setScreen(new MainMenuScreen(game));
			 //Timer timer = new Timer().schedule(game.setScreen(new MainMenuScreen(game)), 1);
		}
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(MainMenuScreen.slika, 0, 0, 800, 480);
		//Save.dt.setTentitiveScore(45);
		batch.draw(pozadina1, 0, 0, 800, 480);
		batch.end();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(300, 650));
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(atlas);
		skin.addRegions(atlas);
		tab = new Table(skin);
		tab.setFillParent(true);
		tab.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		TextButtonStyle arcadeButtonStyle = new TextButtonStyle();
		arcadeButtonStyle.up = skin.getDrawable("arcade_up");
		arcadeButtonStyle.down = skin.getDrawable("arcade_down");
		arcadeButtonStyle.pressedOffsetX = 1;
		arcadeButtonStyle.pressedOffsetY = -1;
		arcadeButtonStyle.font = game.font;
		
		
		arcade = new TextButton("",arcadeButtonStyle);
		
		TextButtonStyle freeButtonStyle = new TextButtonStyle();
		freeButtonStyle.up = skin.getDrawable("fp_up");
		freeButtonStyle.down = skin.getDrawable("fp_down");
		freeButtonStyle.pressedOffsetX = 1;
		freeButtonStyle.pressedOffsetY = -1;
		freeButtonStyle.font = game.font;
		
		free = new TextButton("",freeButtonStyle);
		
		TextButtonStyle timeButtonStyle = new TextButtonStyle();
		timeButtonStyle.up = skin.getDrawable("time_up");
		timeButtonStyle.down = skin.getDrawable("time_down");
		timeButtonStyle.pressedOffsetX = 1;
		timeButtonStyle.pressedOffsetY = -1;
		timeButtonStyle.font = game.font;
		
		time = new TextButton("",timeButtonStyle);
		
		TextButtonStyle hcButtonStyle = new TextButtonStyle();
		hcButtonStyle.up = skin.getDrawable("hc_up");
		hcButtonStyle.down = skin.getDrawable("hc_down");
		hcButtonStyle.pressedOffsetX = 1;
		hcButtonStyle.pressedOffsetY = -1;
		hcButtonStyle.font = game.font;
		
		hc = new TextButton("",hcButtonStyle);
		
		TextButtonStyle drunkButtonStyle = new TextButtonStyle();
		drunkButtonStyle.up = skin.getDrawable("drunk_up");
		drunkButtonStyle.down = skin.getDrawable("drunk_down");
		drunkButtonStyle.pressedOffsetX = 1;
		drunkButtonStyle.pressedOffsetY = -1;
		drunkButtonStyle.font = game.font;
		
		drunk = new TextButton("",drunkButtonStyle);
       
        
        tab.columnDefaults(0);
        tab.add(arcade);
     
       
        tab.add(free);
        
        tab.add(time);
        
        tab.add(hc);
        
   
        tab.add(drunk);
        stage.addActor(tab);
        
        arcade.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			   game.setScreen(new GameScreen(game));
			   GameScreen.isArcade = true;
			   GameScreen.zivot = 4;
			   GameScreen.gameover = false;
			   GameScreen.isDrunk = false;
			   GameScreen.isFree = false;
			   GameScreen.isHc = false;
			   GameScreen.isTime = false;
			}
        	
        });
        
        free.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			   game.setScreen(new GameScreen(game));
			   GameScreen.isFree = true;
			   GameScreen.isArcade = false;
			   GameScreen.isDrunk = false;
			   GameScreen.isHc = false;
			   GameScreen.isTime = false;
			   GameScreen.gameover = false;
			   GameScreen.zivot = 4;
			}
        	
        });
        
        hc.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			   game.setScreen(new GameScreen(game));
			   GameScreen.isHc = true;
			   GameScreen.isArcade = false;
			   GameScreen.isDrunk = false;
			   GameScreen.isFree = false;
			   GameScreen.isTime = false;
			   GameScreen.gameover = false;
			   GameScreen.zivot = 1;
			   
			}
        	
        });
        
        time.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			   game.setScreen(new GameScreen(game));
			   GameScreen.isArcade = false;
			   GameScreen.isDrunk = false;
			   GameScreen.isFree = false;
			   GameScreen.isHc = false;
			   GameScreen.isTime = true;
			}
        	
        });
        
        drunk.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			   game.setScreen(new GameScreen(game));
			   GameScreen.isArcade = false;
			   GameScreen.isDrunk = true;
			   GameScreen.isFree = false;
			   GameScreen.isHc = false;
			   GameScreen.isTime = false;
			}
        	
        });

	}

	@Override
	public void hide() {
		

	}

	@Override
	public void pause() {
	

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void dispose() {
	

	}

}
