package com.ivan.flappymario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ivan.flappymario.FlappyMario;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyMario.ANCHO;
		config.height = FlappyMario.ALTO;
		config.title = FlappyMario.TITULO;
		new LwjglApplication(new FlappyMario(), config);
	}
}
