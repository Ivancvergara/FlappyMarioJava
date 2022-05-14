package com.ivan.flappymario.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tubo {
    public static final int ANCHO_TUBO = 52;

    private static final int FLUCTUACION = 130;
    private static final int TUBO_DIST = 100;
    private static final int APERTURA_MAS_BAJA = 120;

    private Texture tuboSuperior;
    private Texture tuboInferior;

    private Vector2 posTuboSuperior;
    private Vector2 posTuboInferior;

    //Clase que determina un número cualquiera dentro de un random, con limitación definida.
    private Random aleatorio;

    //Variables para definir los rectangulos físcos para hacer las colisiones para que pueda terminar el juego ante el choque de Mario contra un tubo.
    private Rectangle limiteSuperior;
    private Rectangle limiteInferior;

    public Tubo(float x){
        //Se crean las texturas de los Tubos para poder colocarlos en el juego.
        tuboSuperior = new Texture("tubosuperior.png");
        tuboInferior = new Texture("tuboinferior.png");

        aleatorio = new Random();

        posTuboSuperior = new Vector2(x, aleatorio.nextInt(FLUCTUACION) + TUBO_DIST + APERTURA_MAS_BAJA);
        posTuboInferior = new Vector2(x, posTuboSuperior.y - TUBO_DIST - tuboSuperior.getHeight());

        //Se crean los rectángulos para las colisiones.
        limiteSuperior = new Rectangle(posTuboSuperior.x, posTuboSuperior.y, tuboSuperior.getWidth(), tuboSuperior.getHeight());
        limiteInferior = new Rectangle(posTuboInferior.x, posTuboInferior.y, tuboInferior.getWidth(), tuboInferior.getHeight());
    }

    //Método para reposicionar los tubos y los rectángulos para las colisiones.
    public void reposicion(float x){
        posTuboSuperior.set(x, aleatorio.nextInt(FLUCTUACION) + TUBO_DIST + APERTURA_MAS_BAJA);
        posTuboInferior.set(x, posTuboSuperior.y - TUBO_DIST - tuboSuperior.getHeight());

        limiteSuperior.setPosition(posTuboSuperior.x, posTuboSuperior.y);
        limiteInferior.setPosition(posTuboInferior.x, posTuboInferior.y);
    }

    //Método para saber cuándo va a hacer colisión con algunos de los tubos, es decir, cuando pierde.
    public boolean colision(Rectangle jugador){
        return jugador.overlaps(limiteSuperior) || jugador.overlaps(limiteInferior);
    }

    public Texture getTuboSuperior() {
        return tuboSuperior;
    }

    public Texture getTuboInferior() {
        return tuboInferior;
    }

    public Vector2 getPosTuboSuperior() {
        return posTuboSuperior;
    }

    public Vector2 getPosTuboInferior() {
        return posTuboInferior;
    }

    public void dispose(){
        tuboSuperior.dispose();
        tuboInferior.dispose();
    }

}
