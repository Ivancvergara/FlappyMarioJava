package com.ivan.flappymario.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


//Clase de administra los estados del juego.
public class GameStateManager {

    private Stack<State> states;

    //Constructor para inicializar la pila de Estados
    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push (State state){
        states.push(state);
    }

    //Elimina el último estado que haya ingresado en la pila
    public void pop(){
        states.pop();
    }

    //Setea(Reemplaza) un estado dentro de la pila
    public void set(State state) {
        states.pop();
        states.push(state);
    }

    //Actualiza cada uno de los elementos que necesita para ejecutar correctamente el juego.
    public void update(float dt){
        states.peek().update(dt);
    }

    //Renderiza toda la aplicación el SpriteBatch (El cual es un contenedor que ayuda a almacenar varias cosas importantes para nuestro juego. Optimiza la carga de los elementos para nuestro juego.)
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
