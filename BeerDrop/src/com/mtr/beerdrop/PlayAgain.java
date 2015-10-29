package com.mtr.beerdrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class PlayAgain implements Screen {
	Drop game;
	TextureAtlas atlas;
	public static BitmapFont font;
	Stage stage;
	TextButton pa;
	TextButton mm;
	Skin skin;
	Table table;
	OrthographicCamera camera;
	SpriteBatch batch;
	//public static int currentScore = GameScreen.score;
	public static int hs;
	Texture go;
	//Drop gam;

	public PlayAgain(Drop gam) {
		Gdx.input.setCatchBackKey(true);
		// TODO Auto-generated constructor stub
		game = gam;
		this.game = gam;
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
		
		
		camera.update();
		Gdx.gl.glClearColor(124/255F, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(MainMenuScreen.slika, 0, 0, 800, 480);
	    game.font.draw(batch, "Score: " + GameScreen.score, 350, 400);
		//game.font.draw(game.batch, "Tap to play again", 50, 50);
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
		stage = new Stage(new ExtendViewport(600,300));
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("pa_buttons/pa_buttons.pack");
        skin = new Skin(atlas);
        skin.addRegions(atlas);
 
        table = new Table(skin);
        table.setFillParent(true);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font = new BitmapFont();
		skin = new Skin(atlas);
		go = new Texture(Gdx.files.internal("go.png"));
        TextButtonStyle paStyle = new TextButtonStyle();
        paStyle.up = skin.getDrawable("pa_up");
        paStyle.down = skin.getDrawable("pa_down");
        paStyle.pressedOffsetX = 1;
        paStyle.pressedOffsetY = -1;
        paStyle.font = font;
		
		pa = new TextButton("",paStyle);
		
        TextButtonStyle mmStyle = new TextButtonStyle();
        mmStyle.up = skin.getDrawable("mm_up");
        mmStyle.down = skin.getDrawable("mm_down");
        mmStyle.pressedOffsetX = 1;
        mmStyle.pressedOffsetY = -1;
        mmStyle.font = font;
		
		mm = new TextButton("",mmStyle);
		
		table.add(mm);
		table.add(pa);
		
		table.setY(5);
		
		stage.addActor(table);
	       pa.addListener(new ChangeListener(){

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					game.setScreen(new GameScreen(game));
	                   if(GameScreen.isArcade){
	                	   GameScreen.zivot = 4;
	                	   GameScreen.score = 0;
	                	   GameScreen.isArcade = true;
	                	   GameScreen.isDrunk = false;
	                	   GameScreen.isFree = false;
	                	   GameScreen.isHc = false;
	                	   GameScreen.isTime = false;
	                   }
	                   if(GameScreen.isHc){
	                	   GameScreen.zivot = 1;
	                	   GameScreen.score = 0;
	                	   GameScreen.isArcade = false;
	                	   GameScreen.isDrunk = false;
	                	   GameScreen.isFree = false;
	                	   GameScreen.isHc = true;
	                	   GameScreen.isTime = false;
	                   }
	                   if(GameScreen.isTime){
	                	   GameScreen.zivot = 0;
	                	   GameScreen.time = 30;
	                	   GameScreen.score = 0;
	                	   GameScreen.isArcade = false;
	                	   GameScreen.isDrunk = false;
	                	   GameScreen.isFree = false;
	                	   GameScreen.isHc = false;
	                	   GameScreen.isTime = true;
	                   }
	                   if(GameScreen.isDrunk){
	                	   GameScreen.zivot = 4;
	                	   GameScreen.score = 0;
	                	   GameScreen.isArcade = false;
	                	   GameScreen.isDrunk = true;
	                	   GameScreen.isFree = false;
	                	   GameScreen.isHc = false;
	                	   GameScreen.isTime = false;
	                   }
	                   if(GameScreen.isFree){
	                	   GameScreen.zivot = 4;
	                	   GameScreen.score = 0;
	                	   GameScreen.isArcade = false;
	                	   GameScreen.isDrunk = false;
	                	   GameScreen.isFree = true;
	                	   GameScreen.isHc = false;
	                	   GameScreen.isTime = false;
	                   }
				   
				   //stage.dispose();

				}
	        	
	        });
	       mm.addListener(new ChangeListener(){

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					GameScreen.isArcade = false;
					GameScreen.isDrunk = false;
					GameScreen.isFree = false;
					GameScreen.isHc = false;
					GameScreen.isTime = false;
					game.setScreen(new MainMenuScreen(game));
					//stage.dispose();
				}

	        	
	        });
		
	}

	@Override
	public void hide() {
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
		GameScreen.gameOver.dispose();
		stage.dispose();
		batch.dispose();
		
	}

}
