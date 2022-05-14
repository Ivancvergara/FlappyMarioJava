package com.ivan.flappymario.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gameStateManager){
        this.gsm = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

   protected abstract void handleInput();
    //Ciclo del Juego


    public abstract void update(float dt);

    //Método Render, que carga todos los elementos que el juego necesita.
    public abstract void render (SpriteBatch spriteBatch);
    public abstract void dispose();
}
