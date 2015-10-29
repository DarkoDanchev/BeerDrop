package com.mtr.beerdrop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"),false);
        font.setColor(Color.GREEN);
        this.setScreen(new Splash(this));
    }

    public void render() {
        super.render();
    }
    public void resize(int width, int height) {
    	super.resize(width, height);
   	}

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
