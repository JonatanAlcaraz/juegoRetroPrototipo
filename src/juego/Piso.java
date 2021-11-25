package juego;

import entorno.Entorno;
import java.awt.Color;

public class Piso {
    double x;
    double y;
    double ancho;
    double alto;
    
    public Piso(double x, double y , double ancho , double alto) {
        this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno e) {
		
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
    }
}
