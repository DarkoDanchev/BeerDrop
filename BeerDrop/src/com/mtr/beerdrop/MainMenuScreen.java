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


//import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;



public class MainMenuScreen implements Screen {
    final Drop game;
    TextButton play;
    TextButton options;
    TextButton hs;
    TextButton skins;
    TextButton more;
    static Texture slika;
    SpriteBatch batch;
    //TextureAtlas textureatlas;
    TextureAtlas atlas;
    //FileHandle slika_pack;
    Skin skin;
    Viewport viewport;
     Stage stage;
    Label heading;
    OrthographicCamera camera;
    Table tab;
   
   // public Rectangle viewport ;
    float VIRTUAL_WIDTH;
    float VIRTUAL_HEIGHT;
    float scrw = 800;
    float scrh = 480;
    float HALF_VIRTUAL_WIDTH;
    float HALF_VIRTUAL_HEIGHT;
    public long vreme;
    

	//private TextBounds draw;

    public MainMenuScreen(final Drop gam) {
    	Gdx.input.setCatchBackKey(true);
		
	    //GameScreen.pesna.stop();
    	GameScreen.score = 0;
		GameScreen.prefs = Gdx.app.getPreferences("highscore");
		GameScreen.getHs = GameScreen.prefs.getInteger("arcade",GameScreen.highScoreArcade);
		GameScreen.getHs1 = GameScreen.prefs.getInteger("free",GameScreen.highScoreFree);
		GameScreen.getHs2 = GameScreen.prefs.getInteger("hc",GameScreen.highScoreHc);
		GameScreen.getHs3 = GameScreen.prefs.getInteger("drunk",GameScreen.highScoreDrunk);
		GameScreen.getHs4 = GameScreen.prefs.getInteger("time",GameScreen.highScoreTime);
		OptionsScreen.prefs = Gdx.app.getPreferences("opcii");
		OptionsScreen.zvuk = OptionsScreen.prefs.getBoolean("Sound");
		OptionsScreen.muzika = OptionsScreen.prefs.getBoolean("Music");
		GameScreen.time = 30;
        game = gam;
        camera = new OrthographicCamera();
        camera.viewportHeight = scrh;
        camera.viewportWidth = scrw;
        camera.position.set(camera.viewportWidth * .5f,camera.viewportHeight * .5f, 0f);
        camera.update();
		batch = new SpriteBatch();
		slika = new Texture(Gdx.files.internal("pozadina.png"));
		GameScreen.isArcade = false;
		GameScreen.isDrunk = false;
		GameScreen.isFree = false;
		GameScreen.isHc = false;
		GameScreen.isTime = false;
		GameScreen.gameover = false;
		OptionsScreen.zvuk = true;
		OptionsScreen.muzika = true;
		/*if(OptionsScreen.zvuk){
			GameScreen.soundOn = true;
		}
		else if(!OptionsScreen.zvuk){
			GameScreen.soundOn = false;
		}
		else if(!OptionsScreen.muzika){
			GameScreen.musicOn = false;
		}
		else if(OptionsScreen.muzika){
			GameScreen.musicOn = true;
		}*/
		/*if(OptionsScreen.zvuk) OptionsScreen.prefs.putBoolean("Sound", true);
		if(OptionsScreen.muzika) OptionsScreen.prefs.putBoolean("Music", true);
		if(!OptionsScreen.zvuk) OptionsScreen.prefs.putBoolean("Sound", false);
		if(!OptionsScreen.muzika) OptionsScreen.prefs.putBoolean("Music", false);*/
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
		/*if(OptionsScreen.zvuk == true){
			GameScreen.soundOn = true;
			OptionsScreen.zOn = true;
		}
		if(OptionsScreen.zvuk == false){
			GameScreen.soundOn = false;
			OptionsScreen.zOn = false;
		}
		if(OptionsScreen.muzika == false){
			GameScreen.musicOn = false;
			OptionsScreen.mOn = false;
		}
		if(OptionsScreen.muzika == true){
			GameScreen.musicOn = true;
			OptionsScreen.mOn = true;
		}*/
		//GameScreen.pesna.stop();
		
		
		
        
    }

	
	@Override
	public void render(float delta) {
		stage.act();
		Gdx.input.setCatchBackKey(true);
		if((Gdx.input.isKeyPressed(Keys.BACK))){
			Gdx.app.exit();
		}
		camera.update();
		Gdx.gl.glClearColor(124/255F, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(slika, 0, 0, 800, 480);
        batch.end();
		stage.draw();
        
    
        
        
     
 
        
        
   }

	


	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		

		stage = new Stage(new ExtendViewport(200, 350));
	    
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		atlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		
        skin = new Skin(atlas);
        skin.addRegions(atlas);
 
        tab = new Table(skin);
        tab.setFillParent(true);
       
        tab.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        final TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.down = skin.getDrawable("play_down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = game.font;
        textButtonStyle.up = skin.getDrawable("play_up");
        
        play = new TextButton("",textButtonStyle);
        final TextButtonStyle optionsButtonStyle = new TextButtonStyle();
        optionsButtonStyle.down = skin.getDrawable("options_down");
        optionsButtonStyle.up = skin.getDrawable("options_up");
        optionsButtonStyle.pressedOffsetX = 1;
        optionsButtonStyle.pressedOffsetY = -1;
        optionsButtonStyle.font = game.font;
        
       
        options = new TextButton("",optionsButtonStyle);
        
        final TextButtonStyle hsButtonStyle = new TextButtonStyle();
        hsButtonStyle.down = skin.getDrawable("hs_down");
        hsButtonStyle.up = skin.getDrawable("hs_up");
        hsButtonStyle.pressedOffsetX = 1;
        hsButtonStyle.pressedOffsetY = -1;
        hsButtonStyle.font = game.font;
        
        hs = new TextButton("",hsButtonStyle);
        
        final TextButtonStyle sButtonStyle = new TextButtonStyle();
        sButtonStyle.down = skin.getDrawable("skin_down");
        sButtonStyle.up = skin.getDrawable("skin_up");
        sButtonStyle.pressedOffsetX = 1;
        sButtonStyle.pressedOffsetY = -1;
        sButtonStyle.font = game.font;
        
        skins = new TextButton("",sButtonStyle);
        
        final TextButtonStyle mButtonStyle = new TextButtonStyle();
        mButtonStyle.down = skin.getDrawable("more_down");
        mButtonStyle.up = skin.getDrawable("more_up");
        mButtonStyle.pressedOffsetX = 1;
        mButtonStyle.pressedOffsetY = -1;
        mButtonStyle.font = game.font;
        
        more = new TextButton("",mButtonStyle);
        play.top();
  
        tab.columnDefaults(0);
        tab.add(play);
     
       // tab.row();
        tab.add(options);
        tab.row();
        //tab.add(skins);
        tab.row();
        tab.add(more);
       // tab.row();
   
        tab.add(hs);
        tab.row();
        tab.setX(100);
        
        
        play.addListener(new ChangeListener(){
            @Override
 			public void changed(ChangeEvent event, Actor actor) {
         	   game.setScreen(new ModeScreen(game));
         	   stage.clear();
 				
 			}
         	
         });
        options.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen(game));
				
			}
        	
        });
        more.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MoreScreen(game));
				
			}
        	
        });
        hs.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new HsScreen(game));
				
			}
        	
        });
        // tab.debug();
		stage.addActor(tab);

		
	}

	
   @Override
	public void hide() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();
		//slika.dispose();
		
		
		
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
		stage.dispose();
		skin.dispose();
		atlas.dispose();
		//slika.dispose();
		
	}


}
