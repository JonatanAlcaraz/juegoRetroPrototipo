package juego;

import java.awt.Image;


import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor 
{
	// Variables de instancia
    double x, y, ancho, alto;
    double factorDesplazamiento = 3;
    Image img1;
    Image img2;
    int pisoActual;
    Piso piso;
    Juego juego;
    
    boolean parado;
    //int radio;

    public Velociraptor(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho= ancho;
        this.alto = alto;
        this.pisoActual = 1;
        this.parado = false;
        img1 = Herramientas.cargarImagen("velociraptor.png");
        img2 = Herramientas.cargarImagen("Velociraptor2.png");
        
	}

	public void dibujarse(Entorno entorno){		
		if (pisoActual == 1 || pisoActual == 3 || pisoActual == 5) {
            entorno.dibujarImagen(img2, this.x, this.y - 10, this.ancho, this.alto);
           
        }
		else {
            entorno.dibujarImagen(img1, this.x, this.y - 10 , this.ancho, this.alto);
            
        }
    }

    //actuliza el pisoActual
    public void actualizarPiso(Piso pisos[]) {
        if (this.y < pisos[0].y && this.y > pisos[1].y ){
            this.pisoActual = 1;
        }
        if (this.y < pisos[1].y && this.y > pisos[2].y ){
            this.pisoActual = 2;
        }
        if (this.y < pisos[2].y && this.y > pisos[3].y ){
            this.pisoActual = 3;
        }
        if (this.y < pisos[3].y && this.y > pisos[4].y ){
            this.pisoActual = 4;
        }
        if (this.y < pisos[4].y){
            this.pisoActual = 5;
        }
    }

    public boolean chocasteConPisoInf(Piso piso /*,piso piso2 ,piso piso3 ,piso piso4 ,piso piso5*/ ) {
        
        return  this.y +40 >= piso.y /*|| this.y +40 >= piso2.y || this.y +40 >= piso3.y || this.y +40 >= piso4.y || this.y +40 >= piso5.y*/;
    }
    public boolean chocasteConPisoSup(Piso piso /*,piso piso2 ,piso piso3 ,piso piso4 ,piso piso5*/ ) {
        return  this.y +40 >= piso.y /*|| this.y +40 >= piso2.y || this.y +40 >= piso3.y || this.y +40 >= piso4.y || this.y +40 >= piso5.y*/;
    }

    public void colisionPisoInf(Piso piso) {
        if (this.y+40 >= piso.y ){
            this.y = piso.y-30;
            this.parado = true;
        }
        else{
            this.parado = false;
        }
    }
    public void colisionPisoSup(Piso piso) {
        if (this.y-40 <= piso.y ){
            this.y = piso.y +30;
        }}
    
	public void moverDerecha() {
		this.x += this.factorDesplazamiento;
	}
	
	public void moverIzquierda() {
		this.x -= this.factorDesplazamiento;
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
            this.factorDesplazamiento = 0;
        }

        
    }
   
}