package com.mtr.beerdrop;

import java.util.Iterator;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;



public class GameScreen implements Screen {
	
	Drop game;
	
	public static boolean soundOn = true;
	public static boolean musicOn = true;
	public static Texture kapka;
	public static Texture bomba;
	public static Texture pozadina;
	public static Texture gameOver;
	public static Texture kofa;
	public static Texture kofa1;
	public static Texture kofa2;
	public static Button button;
	public static Texture dead;
	public static Texture dead1;
	public static Texture dead2;
	public static Sound zvuk1;
	public static Sprite zivot1;
	public static TextButton play;
	public Rectangle sise;
	public static int score = 0;
	public static int currentScore;
	public static int level = 1;
	public static Sprite zivot2;
	public static Sprite zivot3;
	public static Music pesna;
	public static OrthographicCamera camera;
    public static Skin skin;
	public static SpriteBatch batch;
	public static Rectangle bucket;
	//----------prefs--------------------
	public static Preferences prefs;
	//----------------------------------
	public static Array<Rectangle> kapki;
	public static Array<Rectangle> bombi;
	public int sobereniSisinja = 0;
	
	
	public static boolean gameover = false;
	public static int hsA;
	public static int hsT;
	public static int hsF;
	public static int hsD;
	public static int hsH;
	public static BitmapFont font;
	public long poslednoIspustenaKapka;
	public long poslednoIspustenaBomba;
	public long poslednoNamalenaSekunda;
	long startTime;
	public long vreme1 = 0;
	public static long time = 30;
	public Timer timer;
	public static int highscore;
	public static int getHs;
	public static int getHs1;
	public static int getHs2;
	public static int getHs3;
	public static int getHs4;
	public static int highScoreArcade;
	public static int highScoreFree;
	public static int highScoreTime;
	public static int highScoreHc;
	public static int highScoreDrunk;
	
	//------------------------------modes-------------------------------------------------------
	public static boolean isArcade = false;
	public static boolean isFree = false;
	public static boolean isTime = false;
	public static boolean isHc = false;
	public static boolean isDrunk = false;
	//------------------------------------------------------------------------------------------

	public static int zivot = 4;

	public GameScreen(Drop gam) {
	   /* if(OptionsScreen.zvuk){
			soundOn = true;
		}
		if(!OptionsScreen.zvuk){
			soundOn = false;
		}
		if(OptionsScreen.muzika){
			musicOn = true;
		}
		if(!OptionsScreen.muzika){
			musicOn = false;
		}*/
		game = gam;
		Gdx.input.setCatchBackKey(true);
		//RelativeLayout layout = new RelativeLayout(this);


        font = new BitmapFont(Gdx.files.internal("font.fnt"),false);
        font.setColor(Color.GREEN);
		camera = new OrthographicCamera();
		prefs = Gdx.app.getPreferences("highscore");
		prefs.flush();
		getHs = prefs.getInteger("arcade",highScoreArcade);
		getHs1 = prefs.getInteger("free",highScoreFree);
		getHs2 = prefs.getInteger("hc",highScoreHc);
		getHs3 = prefs.getInteger("drunk",highScoreDrunk);
		getHs4 = prefs.getInteger("time",highScoreTime);
		prefs.flush();

		

		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		kapki = new Array<Rectangle>();
		pustiKapka();
		bombi = new Array<Rectangle>();
		if(!isFree){
		if(isArcade){
		if(level > 1){
		pustiBomba();
		}
		}
		}
		kapka = new Texture(Gdx.files.internal("pivo.png"));
		kofa =  new Texture(Gdx.files.internal("gajba.png"));
		kofa1 =  new Texture(Gdx.files.internal("gajba.png"));
		kofa2 =  new Texture(Gdx.files.internal("gajba.png"));
		dead = new Texture(Gdx.files.internal("dead.png"));
		dead1 = new Texture(Gdx.files.internal("dead1.png"));
		dead2 = new Texture(Gdx.files.internal("dead2.png"));
		bomba = new Texture(Gdx.files.internal("Bomb.png"));
		pozadina = new Texture(Gdx.files.internal("pozadina.jpg"));
		
		
		zivot1 = new Sprite(dead);
		zivot2 = new Sprite(dead);
		zivot3 = new Sprite(dead);
		
		gameOver = new Texture(Gdx.files.internal("gameover.png"));
		zvuk1 = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		pesna = Gdx.audio.newMusic(Gdx.files.internal("pesna.mp3"));
		sise = new Rectangle();
		sise.width = 100;
		sise.height = 64;
	
		
		startTime = TimeUtils.nanoTime();

		if(musicOn) {
	    pesna.setLooping(true);
		pesna.play();
		}
		if(!musicOn){
		pesna.setLooping(false);
		pesna.stop();
		}
		
		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 128;
		bucket.height = 64;
		Gdx.input.setCatchBackKey(true);
		
	}
	/*public static void setHighScore(int val) {
	    prefs.putInteger("highScoreArcade", val);
	    prefs.flush();
	}
	public static int getHighScore() {
	    return prefs.getInteger("highScoreArcade");
	}*/
public void resize(int width, int height) {
		
		
	}

	public void render(float delta) {
		Gdx.input.setCatchBackKey(true);
		if((Gdx.input.isKeyPressed(Keys.BACK))){
			game.setScreen(new MainMenuScreen(game));
			pesna.stop();
			 
		}
		float acceleration=Gdx.input.getAccelerometerY();
		

		int vreme = 400;
		if(isDrunk){
			vreme = -400;
		}
		if (Math.abs(acceleration) > 0.3f) 
		{
			bucket.x -= acceleration * -vreme * Gdx.graphics.getDeltaTime(); 
		}


		Gdx.gl.glClearColor(210/255F, 235/255F, 30/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(pozadina, 0, 0, 800, 480);
		
		batch.draw(kofa,bucket.x,bucket.y);
		
		if(!isTime){
		font.draw(batch, "Score:" + score , 675, 460);
		//game.font.draw(batch, "HighScore:" + getHs , 650, 400);
		}
		if(isTime){
		font.draw(batch, "Time:" + time , 700, 460);	
		}
		
		if(isHc){
			
				batch.draw(kofa,0,477-16,16,16);
				kofa2.dispose();
				kofa1.dispose();
				
			
		}
		if(!isHc && !isTime){
		batch.draw(kofa2,0,477-16,16,16);
		batch.draw(kofa1,18,477-16,16,16);
		batch.draw(kofa1,36,477-16,16,16);
		
		if(zivot == 3){
		
		batch.draw(dead,36,477-16,16,16);

		}
		if(zivot == 2){
		batch.draw(dead1,18,477-16,16,16);
		batch.draw(dead,36,477-16,16,16);

		}
		if(zivot == 1){
		batch.draw(dead,0,477-16,16,16);
		batch.draw(dead,18,477-16,16,16);
		batch.draw(dead,36,477-16,16,16);
		

		}
		}
		
		for(Rectangle pustenakapka: kapki ){
			batch.draw(kapka, pustenakapka.x, pustenakapka.y);
		}
		if(!isFree){
		for(Rectangle pustenabomba: bombi){
			batch.draw(bomba, pustenabomba.x, pustenabomba.y);
		}
		}
	
		batch.end();
		/*if(Gdx.input.isTouched()){
			Vector3 pozicija = new Vector3();
			pozicija.set(Gdx.input.getX(),Gdx.input.getY(), 0);
			camera.unproject(pozicija);
			bucket.x = pozicija.x - 1;
			
		}*/
	
		if(Gdx.input.isKeyPressed(Keys.A)) bucket.x -= 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D)) bucket.x += 300 * Gdx.graphics.getDeltaTime();
	
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800-128) bucket.x = 800-128;
		if(isArcade){
		if(score >= 50){
			level = 2;
		}
		if(score >= 100){
			level = 3;
		}
		}
		if(isHc){
			zivot = 1;
	
			
			
		}
		
		if(TimeUtils.millis() - poslednoNamalenaSekunda > 1000) {
			namaliSekunda();
			
		}
		if(!isFree){
		if(isArcade){
		if(level > 1){		
		if(TimeUtils.millis() - poslednoIspustenaBomba > 10000) {
			pustiBomba();
			
		}

		}
		}
		}
		switch(level){
		case 1:
			if(TimeUtils.nanoTime() - poslednoIspustenaKapka > 1000000000) pustiKapka();
			
			break;
		case 2:
			if(TimeUtils.nanoTime() - poslednoIspustenaKapka > 800000000) pustiKapka();
			
			break;
		case 3:
			if(TimeUtils.nanoTime() - poslednoIspustenaKapka > 600000000) pustiKapka();
			
			break;

			
		}
		if(TimeUtils.millis() - poslednoIspustenaBomba > 10000) pustiBomba();
		Iterator<Rectangle> iter1 = bombi.iterator();
		while(iter1.hasNext()){
			Rectangle dr1 = iter1.next();
			sise = dr1;
			dr1.y -= 200 * Gdx.graphics.getDeltaTime();
			if(dr1.y + 64 <0) iter1.remove();
			if(dr1.overlaps(bucket)){
				if(!isTime || !isTime){
				zivot-=1;
				iter1.remove();
				}
				if(isTime){
				 time -= 5;
				 iter1.remove();
				}
				
			}
			
		}
		if(TimeUtils.nanoTime() - poslednoIspustenaKapka > 1000000000) pustiKapka();
		Iterator<Rectangle> iter = kapki.iterator();
		while(iter.hasNext()){
			Rectangle dr = iter.next();
		
			dr.y -= 200 * Gdx.graphics.getDeltaTime();

			if(dr.y + 64 <0) iter.remove();
			
			if(dr.overlaps(bucket)){
				if(soundOn == true){
				zvuk1.play();
				}
				iter.remove();
				score++;
				sobereniSisinja++;
				if(isTime){
					if(sobereniSisinja == 4){
						sobereniSisinja = 0;
						time+=3;
					}
				}
				
			}
			if(sise.overlaps(dr)){
				
				iter.remove();
				
				
			}
			if(dr.y + 64 < 0 && !dr.overlaps(bucket)){
				zivot --;
				
			}

		}
		if(!isTime){
		if(zivot == 0){
			
			bucket.x = 900;
			bucket.y = 600;
			//prefs.putInteger("arcade", 5000);
			//getHs = prefs.getInteger("arcade");
			//prefs.flush();
			//currentScore = score;
			/*if(prefs==null){*/
			if(isArcade){
			if(score > getHs){
				highScoreArcade = score;
		    	prefs.flush();
				prefs = Gdx.app.getPreferences("highscore");
				prefs.putInteger("arcade", highScoreArcade);
				prefs.flush();
				getHs = prefs.getInteger("arcade",highScoreArcade);
				prefs.flush();
				
			}
			}
			if(isFree){
			if(getHs1 > getHs1){
				highScoreFree = score;
		    	prefs.flush();
				prefs = Gdx.app.getPreferences("highscore");
				prefs.putInteger("free", highScoreFree);
				prefs.flush();
				getHs1 = prefs.getInteger("free",highScoreFree);
				prefs.flush();
				
			}
			}
			if(isHc){
			if(score > getHs2){
				highScoreHc = score;
		    	prefs.flush();
				prefs = Gdx.app.getPreferences("highscore");
				prefs.putInteger("hc", highScoreHc);
				prefs.flush();
				getHs2 = prefs.getInteger("hc",highScoreHc);
				prefs.flush();
				
			}
			}
			/*if(isTime){

			}*/
			if(isDrunk){
			if(score > getHs3){
				highScoreDrunk = score;
		    	prefs.flush();
				prefs = Gdx.app.getPreferences("highscore");
				prefs.putInteger("drunk", highScoreDrunk);
				prefs.flush();
				getHs3 = prefs.getInteger("drunk",highScoreDrunk);
				prefs.flush();
				
			}
			}
			
			game.setScreen(new PlayAgain(game));
		}
		}
		if(isTime){
		if(time == 0){
			if(score > getHs4){
				highScoreTime = score;
		    	prefs.flush();
				prefs = Gdx.app.getPreferences("highscore");
				prefs.putInteger("time", highScoreTime);
				prefs.flush();
				getHs4 = prefs.getInteger("time",highScoreTime);
				prefs.flush();
				
			}
			game.setScreen(new PlayAgain(game));
		}
		}

	
		
	}

	





	public void pause() {
		
		
	}

	
	public void resume() {
		
		
	}

	
	public void dispose() {
		kapka.dispose();
		kofa.dispose();
		zvuk1.dispose();
		pesna.dispose();
		batch.dispose();
		
		
	}
	private void pustiKapka(){
		Rectangle pustenakapka = new Rectangle();
		pustenakapka.x = MathUtils.random(0,800-64);
		pustenakapka.y = 480;
		pustenakapka.width = 16;
		pustenakapka.height = 64;
		kapki.add(pustenakapka);
		poslednoIspustenaKapka = TimeUtils.nanoTime();
		
		
	}
	private void pustiBomba(){
		Rectangle pustenabomba = new Rectangle();
		pustenabomba.x = MathUtils.random(0,800-64);
		pustenabomba.y = 480;
		pustenabomba.width = 16;
		pustenabomba.height = 16;
		bombi.add(pustenabomba);
		
		poslednoIspustenaBomba = TimeUtils.millis();		
	}
	private void namaliSekunda(){
		time--;
		poslednoNamalenaSekunda = TimeUtils.millis();
	}




	@Override
	public void show() {
		
		
	}




	@Override
	public void hide() {
		
		
	}


}