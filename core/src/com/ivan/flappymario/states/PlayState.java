package com.ivan.flappymario.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ivan.flappymario.FlappyMario;
import com.ivan.flappymario.sprites.Mario;
import com.ivan.flappymario.sprites.Tubo;


public class PlayState extends State{

    //Constante privada para el espaciado de entre tubos.
    private static final int ESPACIO_TUBO = 125;
    private static final int CONTADOR_TUBO = 4;
    private static final int PISO_Y_PERDIDA = -30;

    private Mario mario;
    private Texture fondo;
    private Music principal;
    private BitmapFont puntuacion;

    private Texture piso;
    private Vector2 pisoPos1;
    private Vector2 pisoPos2;
    int Contador = 0;

    //Arreglo para realizar la posición de los tubos dentro del juego.
    private Array<Tubo> tubos;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        mario = new Mario(50, 320);
        camera.setToOrtho(false, FlappyMario.ANCHO / 2, FlappyMario.ALTO / 2);
        fondo = new Texture("bg2.png");
        piso = new Texture("piso.png");
        puntuacion = new BitmapFont();
        pisoPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, PISO_Y_PERDIDA);
        pisoPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + piso.getWidth(), PISO_Y_PERDIDA);

        tubos = new Array<Tubo>();

        for(int i = 1 ; i <= CONTADOR_TUBO ; i++){
            tubos.add(new Tubo(i *(ESPACIO_TUBO + Tubo.ANCHO_TUBO)));
        }

        setUpPrincipal();

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mario.volar();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updatePiso();
        mario.update(dt);

        camera.position.x = mario.getPosition().x + 80;

        //Bucle para reposicionar los tubos. (Ver dispositiva).
        for (Tubo tubo: tubos){
            if(camera.position.x - (camera.viewportWidth / 2) > tubo.getPosTuboSuperior().x + tubo.getTuboSuperior().getWidth()){
                tubo.reposicion(tubo.getPosTuboSuperior().x + ((Tubo.ANCHO_TUBO + ESPACIO_TUBO) * CONTADOR_TUBO));
                Contador = Contador + 1;
            }

            if (tubo.colision(mario.getLimites())){
                principal.stop();
                gsm.set(new GameOverState(gsm));
            }
        }

        if(mario.getPosition().y <= piso.getHeight() + PISO_Y_PERDIDA) {
            principal.stop();
            gsm.set(new GameOverState(gsm));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(fondo,camera.position.x - (camera.viewportWidth / 2),camera.position.y - (camera.viewportHeight / 2));
        spriteBatch.draw(mario.getTexture(),mario.getPosition().x,mario.getPosition().y);
        spriteBatch.draw(piso, pisoPos1.x, pisoPos1.y);
        spriteBatch.draw(piso, pisoPos2.x, pisoPos2.y);
        for(Tubo tubo: tubos){
            spriteBatch.draw(tubo.getTuboSuperior(), tubo.getPosTuboSuperior().x, tubo.getPosTuboSuperior().y);
            spriteBatch.draw(tubo.getTuboInferior(), tubo.getPosTuboInferior().x, tubo.getPosTuboInferior().y);
        }
        puntuacion.draw(spriteBatch, "Puntuación: " + Contador, camera.position.x - (camera.viewportWidth / 3), 40);
        spriteBatch.end();
    }

    //Método para dejar de usar algunos objetos que no se utilicen para que no haya problemas con la memoria. Es decir, se libera memoria al quitar elementos que ya no se ocupen.
    @Override
    public void dispose() {
        fondo.dispose();
        mario.dispose();
        for(Tubo tubo: tubos) {
            tubo.dispose();
        }
        principal.dispose();
    }

    //Método para reposicionar el piso.
    private void updatePiso(){
        if(camera.position.x - (camera.viewportWidth / 2) > pisoPos1.x + piso.getWidth())
            pisoPos1.add(piso.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > pisoPos2.x + piso.getWidth())
            pisoPos2.add(piso.getWidth() * 2, 0);
    }

    private void setUpPrincipal(){
        principal = Gdx.audio.newMusic(Gdx.files.internal("principal.mp3"));
        principal.setLooping(true);
        principal.setVolume(0.15f);
        principal.play();
    }
}
