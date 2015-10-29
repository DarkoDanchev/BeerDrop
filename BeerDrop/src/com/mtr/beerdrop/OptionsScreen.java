package com.mtr.beerdrop;







import javax.swing.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
public class OptionsScreen implements Screen,InputProcessor {
	
	Drop game;
	Stage stage;
	Stage stage1;
	Texture cross;
	Table table;
	String state;
	TextureAtlas atlas;
	Skin skin;
	TextButton zvukOn;
	static boolean zOn;
	static boolean mOn;
	static boolean zvuk;
	static boolean muzika;
	TextButton zvukOff;
	TextButton muzikaOn;
	TextButton muzikaOff;
	BitmapFont font1;
	BitmapFont font2;
	static Preferences prefs;
	OrthographicCamera camera;
	public int kliknanoZvuk = 1;
	public int kliknanoMuzika = 1;
	SpriteBatch batch;


	  


	public OptionsScreen(Drop gam) {
		/*prefs = Gdx.app.getPreferences("opcii");
		prefs.flush();
		zvuk = prefs.getBoolean("Sound");
		prefs.flush();
		muzika = prefs.getBoolean("Music");
		prefs.flush();
		if(zvuk){
			GameScreen.soundOn = true;
		}
		else if(!zvuk){
			GameScreen.soundOn = false;
		}
		else if(!muzika){
			GameScreen.musicOn = false;
		}
		else if(muzika){
			GameScreen.musicOn = true;
		}*/
		Gdx.input.setCatchBackKey(true);
		this.game = gam;

        font1 = new BitmapFont(Gdx.files.internal("font.fnt"),false);
		font1.setScale(2);
		font2 = new BitmapFont(Gdx.files.internal("font.fnt"),false);
		font2.setScale(2);
		font1.setColor(Color.BLUE);
		font2.setColor(Color.BLUE);
		//Preferences prefs = Gdx.app.getPreferences("opcii.txt");
		cross = new Texture(Gdx.files.internal("options_buttons/Cross.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		//Gdx.input.setCatchBackKey(true);
		batch = new SpriteBatch();
	}

	

	@Override
	public void render(float delta) {
		Gdx.input.setCatchBackKey(true);
		if((Gdx.input.isKeyPressed(Keys.BACK))){
			game.setScreen(new MainMenuScreen(game));
			 //Timer timer = new Timer().schedule(game.setScreen(new MainMenuScreen(game)), 1);
		}
		stage.act();
		
		camera.update();
		Gdx.gl.glClearColor(124/255F, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(MainMenuScreen.slika, 0, 0, 800, 480);

        

        batch.end();
        stage.draw();
        batch.begin();

        if(kliknanoZvuk == 1){
        	font1.draw(batch, "Sound is on", 100, 350);
        	//font2.dispose();
        }
        if(kliknanoZvuk == 0){
        	font2.draw(batch, "Sound is off", 100, 350);
        	//font1.dispose();
        }
        if(kliknanoMuzika == 1){
        	font1.draw(batch, "Music is on", 100, 150);
        	//font2.dispose();
        }
        if(kliknanoMuzika == 0){
        	font2.draw(batch, "Music is off", 100, 150);
        	//font1.dispose();
        }

        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		stage = new Stage(new ExtendViewport(200,200));
		stage1 = new Stage(new ExtendViewport(150,150));
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		atlas = new TextureAtlas(Gdx.files.internal("options_buttons/options_buttons.pack"));
        skin = new Skin(atlas);
        skin.addRegions(atlas);
        table = new Table(skin);
        table.setFillParent(true);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        final TextButtonStyle zvukButtonStyle = new TextButtonStyle();
        zvukButtonStyle.up = skin.getDrawable("sd_on_up");
        zvukButtonStyle.down = skin.getDrawable("sd_on_down");
        zvukButtonStyle.pressedOffsetX = 1;
        zvukButtonStyle.pressedOffsetY = -1;
        zvukButtonStyle.font = game.font;
        zvukOn = new TextButton("",zvukButtonStyle);
        
        final TextButtonStyle zvukOffButtonStyle = new TextButtonStyle();
        zvukOffButtonStyle.up = skin.getDrawable("sd_off_up");
        zvukOffButtonStyle.down = skin.getDrawable("sd_off_down");
        zvukOffButtonStyle.pressedOffsetX = 1;
        zvukOffButtonStyle.pressedOffsetY = -1;
        zvukOffButtonStyle.font = game.font;
        zvukOff = new TextButton("",zvukOffButtonStyle);
        
        final TextButtonStyle muzikaOnBS = new TextButtonStyle();
        muzikaOnBS.up = skin.getDrawable("ms_on_up");
        muzikaOnBS.down = skin.getDrawable("ms_on_down");
        muzikaOnBS.pressedOffsetX = 1;
        muzikaOnBS.pressedOffsetY = -1;
        muzikaOnBS.font = game.font;
        muzikaOn = new TextButton("",muzikaOnBS);
        
        final TextButtonStyle muzikaOffBS = new TextButtonStyle();
        muzikaOffBS.up = skin.getDrawable("ms_off_up");
        muzikaOffBS.down = skin.getDrawable("ms_off_down");
        muzikaOffBS.pressedOffsetX = 1;
        muzikaOffBS.pressedOffsetY = -1;
        muzikaOffBS.font = game.font;
        muzikaOff = new TextButton("",zvukButtonStyle);
        zvukOn.setX(180);
        zvukOn.setY(100);
        muzikaOn.setX(180);
        muzikaOn.setY(20);
        //table.add(zvukOn);
        //table.add(muzikaOn);
        stage.addActor(muzikaOn);
        stage.addActor(zvukOn);
        //stage1.addActor(cross);
        
        
        zvukOn.addListener(new ChangeListener(){
            @Override
 			public void changed(ChangeEvent event, Actor actor) {
            if(kliknanoZvuk == 1){

            //GameScreen.soundOn = true;
           // prefs.putBoolean("Sound", true);
            //prefs.flush();
           // zvuk = prefs.getBoolean("Sound");
           // prefs.flush();
            kliknanoZvuk = 0;
            GameScreen.soundOn = false;
            //zOn = true;
            //zvuk = true;
            }
            else if(kliknanoZvuk == 0){

            //GameScreen.soundOn = true;
            kliknanoZvuk = 1;
            GameScreen.soundOn = true;
            //prefs.putBoolean("Sound", false);
            //prefs.flush();
          //  zvuk = prefs.getBoolean("Sound");
            //prefs.flush();
           // zvuk = false;
            }
 			}
         	
         });
        muzikaOn.addListener(new ChangeListener(){
            @Override
 			public void changed(ChangeEvent event, Actor actor) {
                if(kliknanoMuzika == 0){
                //GameScreen.musicOn = true;
               // prefs.putBoolean("Music", true);
               // prefs.flush();
              //  muzika = prefs.getBoolean("Music");
                prefs.flush();
                kliknanoMuzika = 1;
                GameScreen.musicOn = true;
            	//muzika = true;
                }
            
                else if(kliknanoMuzika == 1){
            	//muzika = false;

            	//GameScreen.musicOn = false;
              //  prefs.putBoolean("Music", false);
               // prefs.flush();
                //muzika = prefs.getBoolean("Music");
               // prefs.flush();
            	kliknanoMuzika = 0;
            	GameScreen.musicOn = false;
            	//muzika = false;
            	
            }
 			}
         	
         });
        
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
		

	}




	@Override
	public boolean keyUp(int keycode) {
        if(keycode == Keys.BACK){
	       game.setScreen(new MainMenuScreen(game));
        }
        return true;
		
	}



	@Override
	public boolean keyTyped(char character) {
        if(character == Keys.BACK){
	       game.setScreen(new MainMenuScreen(game));
        }
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyDown(int keycode) {
        if(keycode == Keys.BACK){
	       game.setScreen(new MainMenuScreen(game));
        }
		return false;
	}
	public void onBackPressed() {
		game.setScreen(new MainMenuScreen(game));
	}

}
