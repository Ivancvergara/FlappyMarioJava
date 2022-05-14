package com.ivan.flappymario.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ivan.flappymario.FlappyMario;

//Se hereda de la clase abstracta State, para poder realizar nuestro MenúState
public class MenuState extends State{

    //Se crean los objetos de tipo Texture para el fondo y la imagen del título.
    private Texture fondo;
    private Texture titulo;

    //Se crea el objeto de tipo Musica, que sonará al inicializar la aplicación.
    private Music menu;

    //Se crea un constructor para el Menú State
    public MenuState(GameStateManager gsm){
        super(gsm);

        //Se manda a llamar a la cámara que creamos en GameStateManager, para que esta tenga movimiento a la hora de jugar.
        //Ver diapositiva.
        camera.setToOrtho(false, FlappyMario.ANCHO / 2, FlappyMario.ALTO / 2);

        //Se inicializan las texturas creadas al inicio, para asignarle ya qué imágenes ocupar.
        fondo = new Texture("bg.png");
        titulo = new Texture("flappymario.png");

        //Se inicializa la configuración (Configuración creada al final) de la música que se ejecuta al iniciar el programa.
        setUpMusic();
    }

    //Constructor de entrada de datos.
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            menu.stop();
        }
    }

    //Contructor para actualizar los elementos dentro del juego.
    @Override
    public void update(float dt) {
        handleInput();
    }

    //Constructor para renderizar cada uno de los elementos dentro del juego.
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(fondo, 0, 0);
        spriteBatch.draw(titulo, camera.position.x - titulo.getWidth() / 2, camera.position.y + (titulo.getHeight() / 3) );

        spriteBatch.end();
    }

    //Constructor para disposear, es decir, este se encarga de liberar recursos cuando no se utilicen.
    @Override
    public void dispose() {
        fondo.dispose();
        titulo.dispose();
        menu.dispose();
    }

    //Constructor para la configuración de la música.
    private void setUpMusic(){
        menu = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        menu.setLooping(true);
        menu.setVolume(0.15f);
        menu.play();
    }
}
