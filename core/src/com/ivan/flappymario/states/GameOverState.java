package com.ivan.flappymario.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ivan.flappymario.FlappyMario;

public class GameOverState extends State {

    private Texture fondo;
    private Texture gameOver;
    private Music muerto;

    public GameOverState (GameStateManager gsm){
        super(gsm);
        camera.setToOrtho(false, FlappyMario.ANCHO / 2, FlappyMario.ALTO / 2);
        fondo = new Texture("bg.png");
        gameOver = new Texture("gameover.png");

        setUpMuerto();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            muerto.stop();
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) { handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(fondo,camera.position.x - (camera.viewportWidth / 2),camera.position.y - (camera.viewportHeight / 2));
        spriteBatch.draw(gameOver, camera.position.x / 4, camera.position.y + (gameOver.getHeight() / 2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        gameOver.dispose();
        muerto.dispose();
    }

    private void setUpMuerto(){
        muerto = Gdx.audio.newMusic(Gdx.files.internal("muerto.mp3"));
        muerto.setLooping(false);
        muerto.setVolume(0.15f);
        muerto.play();
    }
}
