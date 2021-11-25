package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Laser 
{
	// Variables de instancia
	double x, y, ancho, alto;
	double factorDesplazamiento = 1;
	int pisoActual;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	public Laser( double x ,double y, double ancho, double alto) {
		
        this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img1 = Herramientas.cargarImagen("fuego.gif");
        img2 = Herramientas.cargarImagen("fuego1.gif");
	}

	public void dibujarse(Entorno entorno , Velociraptor velociraptor , Piso[] pisos){
		if ((velociraptor.pisoActual == 1 || velociraptor.pisoActual == 3 || velociraptor.pisoActual == 5)) {
			
			entorno.dibujarImagen(img1, this.x - 40, this.y -  35, 0.0,0.1);	       
		}
		else {
			entorno.dibujarImagen(img2, this.x + 40, this.y - 35, 0.0,0.1);
		}	
	}
	
	public boolean colisionConPersonaje(Personaje pers){
        return this.x > pers.x - 20 && this.x < pers.x + 20 && this.y > pers.y - 35 && this.y < pers.y + 20;   
    }
	
	public boolean chocasteCon(Entorno e) {
		return x >= e.ancho() || y >= e.alto() || x <= 0 || y <= 0 ;		
	}
    
	public void moverDerecha() {
		this.x += factorDesplazamiento;
	}

	public void moverIzquierda() {
		this.x -= factorDesplazamiento;
	}

}
