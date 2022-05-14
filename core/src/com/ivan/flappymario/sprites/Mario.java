package com.ivan.flappymario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Mario {

    //Constante de movimiento.
    private static final int MOVIMIENTO = 100;

    //Constante que va almacenar la gravedad.
    private static final int GRAVITY = -13;

    //Contiene los datos de la posición de nuestro personaje, Posición Y, X, Z.
    private Vector3 position;

    private Vector3 velocity;

    private Animacion animacionMario;

    private Texture texture;

    private Music salto;

    //Atributo tipo Rectángulo
    private Rectangle limites;

    public Mario(int x, int y){
        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("animacionmario.png");
        animacionMario = new Animacion(new TextureRegion(texture), 3, 0.5f);

        limites = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
    }

    //En cada actualización del juego, va a cambiar la posición del personaje.
    public void update(float dt){
        animacionMario.update(dt);

        //Se le agrega una gravedad a la velocidad, para que cuando se ejecute el juego, el personaje caiga.
        if(position.y > 0){
            velocity.add(0,GRAVITY, 0);
        }

        //Se escala el Delta Time, esto para que haga la actualización del personaje, para que cada segundo que pase en el juego agregué la gravedad.
        velocity.scl(dt);

        //Se especifica que la posición del personaje va a ir cambiando conforme jugamos.
        position.add(MOVIMIENTO * dt, velocity.y, 0);

        if(position.y < 0 ){
            position.y = 0;
        }

        //Se especifica que se va a hacer la actulización de poición por cada segundo que pasa.
        velocity.scl(1/dt);

        limites.setPosition(position.x, position.y);
    }

    //Método para obtener el rectángulo, para definir el contornod del personaje y poderlo comparar cuando haga una colisión con un tubo.
    public Rectangle getLimites(){
        return limites;
    }

    //Método para devolver los datos de la Posición
    public Vector3 getPosition() {
        return position;
    }

    //Método para devolver los datos de la Textura.
    public TextureRegion getTexture() {
        return animacionMario.getCuerpo();
    }

    //Método para hacer volar a Mario.
    public void volar(){
        velocity.y = 250;
        setUpSalto();
    }

    public void dispose(){
        texture.dispose();
        salto.dispose();
    }

    private void setUpSalto(){
        salto = Gdx.audio.newMusic(Gdx.files.internal("salto.mp3"));
        salto.setLooping(false);
        salto.setVolume(0.10f);
        salto.play();
    }

}
