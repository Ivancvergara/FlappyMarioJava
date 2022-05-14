package com.ivan.flappymario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ivan.flappymario.states.GameStateManager;
import com.ivan.flappymario.states.MenuState;

public class FlappyMario extends ApplicationAdapter {

    //Se crean las constantes del tamaño de la pantalla, así como del título.
	public static final int ANCHO = 480;
	public static final int ALTO = 720;
	public static final String TITULO = "FlappyMario para Android";

	private GameStateManager gsm;
	private SpriteBatch batch;

	//
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
