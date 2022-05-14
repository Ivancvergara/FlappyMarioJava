package com.ivan.flappymario.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animacion {
    //Definición de atributos

    //Se crea un arreglo donde se van a almacenar las regiones de las animaciones de Mario.
    private Array<TextureRegion> cuerpos;

    private float tiempoMaxCuerpo;
    private float tiempoCorriendoCuerpo;
    private int contadorCuerpos;
    private int cuerpo;

    //Creación del constructor para la animación de Mario.
    public Animacion(TextureRegion region, int contadorCuerpos, float tiempoCiclo){
        cuerpos = new Array<TextureRegion>();
        int anchoCuerpo = region.getRegionWidth() / contadorCuerpos;

        //
        for (int i = 0; i < contadorCuerpos; i++){
            cuerpos.add(new TextureRegion(region, i * anchoCuerpo, 0, anchoCuerpo, region.getRegionHeight()));
        }
        this.contadorCuerpos = contadorCuerpos;
        tiempoMaxCuerpo = tiempoCiclo / contadorCuerpos;
        cuerpo = 0;
    }

    public void update(float dt){
        //El tiempo va a ser sumado con el Delta Tiempo.
        tiempoCorriendoCuerpo += dt;

        // Condicional para cambiar de animación de Mario.
        if(tiempoCorriendoCuerpo > tiempoMaxCuerpo){
            cuerpo++;
            tiempoCorriendoCuerpo = 0;
        }

        // Se reinicia la animación
        if(cuerpo >= contadorCuerpos){
            cuerpo = 0;
        }
    }

    public TextureRegion getCuerpo(){
        return cuerpos.get(cuerpo);
    }

}
