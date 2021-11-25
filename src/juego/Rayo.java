package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo 
{
	// Variables de instancia
	double x;
	double y;
	double ancho;
	double alto;
	double factorDesplazamiento = 20;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	boolean rayito , existe;
	public Rayo(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.rayito = false;
		this.existe = true;
		
		img1 = Herramientas.cargarImagen("rayo.gif");
	}
	
	public void dibujarse(Entorno entorno, Personaje personaje) {
		
		entorno.dibujarImagen(img1, this.x, this.y, this.ancho, this.alto);
		
		if (personaje.pisoActual == 1 || personaje.pisoActual == 3 || personaje.pisoActual == 5) 
			moverDerecha();
		else{
			moverIzquierda();
		} 	
	}
	public boolean colisionConVelociraptor(Velociraptor velo){
        if (velo == null){
            return false;
        }   
        return this.x > velo.x - 20 && this.x < velo.x + 20 && this.y > velo.y - 20 && this.y < velo.y + 20;   
    }

 public void moverDerecha() {
	this.x += factorDesplazamiento;
}
public void moverIzquierda() {
	this.x -= factorDesplazamiento;
}
public boolean chocasteCon(Entorno e) {
	return x >= e.ancho() || y >= e.alto() || x <= 0 || y <= 0 ;		
}

public void cambiarTrayectoria(Entorno e) {
	if (this.x >= e.ancho()){
        this.x -= 1;
        this.factorDesplazamiento = 0;
    }
    if (this.y >= e.alto()){
        this.y -= 1;
        this.factorDesplazamiento = 0;
    }
    if (this.x  <= 0){
        this.x += 1;
        this.factorDesplazamiento = 0;
    }
    if (this.y <= 0){
        this.y += 1;
        this.factorDesplazamiento = 0;}
    }

}