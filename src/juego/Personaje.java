package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Personaje 
{
    // Variables de instancia
    double x, y, ancho, alto , angulo;
    
    Image img1;
    Image img2;
    Image img3;
    Image img4; 
    int pisoActual, vidas , puntos ;
    boolean aux;
    boolean agacharse , cantApretada , quieto , seAgacho;
    Juego juego;
    boolean a ,b ,c ,d ,e;


    //int radio;

    public Personaje(double x, double y, double angulo, double alto) {
        this.x = x;
        this.y = y;
        //this.ancho = ancho;
        this.alto = alto;
        this.angulo = angulo;
        this.pisoActual = 1;
        this.vidas = 100;
        this.puntos = 0;
        this.seAgacho = false;
        img1 = Herramientas.cargarImagen("gladiador.png");
        img2 = Herramientas.cargarImagen("gladiador2.png");
        img3 = Herramientas.cargarImagen("agachado.png");

    }

    public void dibujarse(Entorno entorno){

        //cuando el personaje salte no se puede agachar y dibuja

        
        if (this.pisoActual == 2 || this.pisoActual == 4 ){
            this.img3 = Herramientas.cargarImagen("agachado1.png");
            this.img4 = Herramientas.cargarImagen("gladiador2.png");
        }
        if (this.pisoActual == 1 || this.pisoActual == 3 || this.pisoActual == 5){
            this.img3 = Herramientas.cargarImagen("agachado.png");
            this.img4 = Herramientas.cargarImagen("gladiador.png");
        }

        if (entorno.sePresiono(entorno.TECLA_ARRIBA )){ // && entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada(entorno.TECLA_ARRIBA ) && entorno.estaPresionada(entorno.TECLA_IZQUIERDA)){
            this.y-= 50;
            this.seAgacho = false;
            this.cantApretada = true;
            this.agacharse = false;
            entorno.dibujarImagen(img1, this.x, this.y, 25, 1.5);
        } 
        //else if (entorno.sePresiono(entorno.TECLA_DERECHA)&& entorno.estaPresionada(entorno.TECLA_ABAJO)){
        //    entorno.dibujarImagen(img1, this.x, this.y, 25, 1.5);
        //}   
        //cuando el personaje va a la derecha no se puede agachar y dibuja
        else if (entorno.estaPresionada(entorno.TECLA_DERECHA)){ 
            this.seAgacho = false;
            this.agacharse = false;
            
            entorno.dibujarImagen(img1, this.x, this.y, 25, 1.5);
            if (chocasteCon(entorno)){
                cambiarTrayectoria(entorno);
            }
                moverDerecha();
            
        }
            
        //cuando el personaje va a la izquierda no se puede agachar y dibuja
        else if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) ){
            this.seAgacho = false;
            this.agacharse = false;    
            entorno.dibujarImagen(img2, this.x, this.y, 25, 1.5);
            if (chocasteCon(entorno)){
                cambiarTrayectoria(entorno);
            }
            moverIzquierda();
            
        }
        // si rhis.agacharse es true se agacha y lo dibuja
        else if (entorno.estaPresionada(entorno.TECLA_ABAJO) ){
            
            if (!this.agacharse){
                entorno.dibujarImagen(img1, this.x, this.y, 25 , 1.5);
            }
            if (this.agacharse) {
                this.seAgacho = true;
                entorno.dibujarImagen(img3, this.x, this.y, 25.1 , 0.1); 
            }
        }
        
        // CUANDO ESTA QUIETO
        else if (!entorno.estaPresionada(entorno.TECLA_ABAJO)){
            
            
            this.seAgacho = false;
            this.cantApretada = false;
            this.agacharse = true;
            entorno.dibujarImagen(img4, this.x, this.y, 25.1, 1.5);
        }
         
        if(entorno.sePresiono(entorno.TECLA_ENTER)){
            if (this.pisoActual == 1 || this.pisoActual == 3 ){
                if (this.x > 660 && this.x < 799 ){
                    this.x = 608;
					this.pisoActual++;	
				}
            }
            if (this.pisoActual == 2 || this.pisoActual == 4 ){
                if (this.x > 1 && this.x < 120 ){
                    this.x = 160;
					this.pisoActual++;
				}
            }
        }
    
    
    }

    public void colisiones(Entorno entorno , Piso pisos[] ) {
            
        this.y += 5;
        if (this.pisoActual == 1){
			
			
			if (chocasteConPisoInf(pisos[0]) ){
			
				// no traspasa el piso cuando lo toca
				
				colisionPisoInf(pisos[0]);
				//entorno.escribirTexto("CHOQUE INF", 300, 650);
				
			}
			// si toca el piso superior colisiona , si hay un hueco pasa
			if (chocasteConPisoSup(pisos[1]) && this.x < 660){
				colisionPisoSup(pisos[1]);
				entorno.escribirTexto("CHOQUE SUP", 300, 650);
			}
			//cordenadas donde el personaje deja de saltar dependiendo del piso en que se encuentre  
			if (this.y > pisos[1].y && this.y < 530 && this.x < 660 && this.pisoActual == 1){
				//a = false;
			}
			
		}
		if (this.pisoActual == 2){
			a = false;
			c = false;
			d = false;
			e = false;
			if (chocasteConPisoInf(pisos[1]) || (this.x > 660 && this.y < pisos[2].y)){
				//permite saltar
				b = true;
				// no traspasa el piso o el techo
			}
			if (chocasteConPisoInf(pisos[1]) && this.x < 660){
				//permite saltar
				//b = true;
				// no traspasa el piso o el techo
				colisionPisoInf(pisos[1]);
				entorno.escribirTexto("CHOQUE INF", 300, 650);
			}
			if (chocasteConPisoSup(pisos[2]) && this.x > 103){
				colisionPisoSup(pisos[2]);
				entorno.escribirTexto("CHOQUE SUP", 300, 650);
			}
			//deja saltar
			if (this.y > pisos[2].y &&this.y < 410 && this.x < 660 && this.x > 99){
				b = false;
			}
		}
		if (this.pisoActual == 3){
			a = false;
			b = false;
			d = false;
			e = false;
			if (chocasteConPisoInf(pisos[2]) || (this.x > 660 && this.y < pisos[3].y)){
				//permite saltar
				c = true;
				// no traspasa el piso o el techo
			}
			if (chocasteConPisoInf(pisos[2]) && this.x > 103){
				//permite saltar
				//c = true;
				// 	no traspasa el piso o el techo
				colisionPisoInf(pisos[2]);
				entorno.escribirTexto("CHOQUE INF", 300, 650);
			
			}
			if (chocasteConPisoSup(pisos[3]) && this.x < 660){
				colisionPisoSup(pisos[3]);
				entorno.escribirTexto("CHOQUE SUP", 300, 650);
			}
			//deja saltar
			if (this.y > pisos[3].y && this.y < 290 && this.x < 660 && this.x > 99){
				c = false;
			}
		}
		if (this.pisoActual == 4){
			a = false;
			b = false;
			c = false;
			e = false;
			if (chocasteConPisoInf(pisos[3]) || (this.x > 660 && this.y < pisos[4].y)){
				//permite saltar
				d = true;
				// no traspasa el piso o el techo
			}
			if (chocasteConPisoInf(pisos[3]) && this.x < 660){
				//permite saltar
				//d = true;
				// no traspasa el piso o el techo
				colisionPisoInf(pisos[3]);
				entorno.escribirTexto("CHOQUE INF", 300, 650);
			
			}
			if (chocasteConPisoSup(pisos[4]) && this.x > 103){
				colisionPisoSup(pisos[4]);
				entorno.escribirTexto("CHOQUE SUP", 300, 650);
			}
			//deja saltar
			if (this.y > pisos[4].y && this.y < 170 && this.x < 660 && this.x > 99){
				d = false;
			}
		}
		
		if (this.pisoActual == 5){
			a = false;
			b = false;
			c = false;
			d = false;
			if (chocasteConPisoInf(pisos[4]) || (this.x > 660 && this.y < pisos[4].y)){
				//permite saltar
				e = true;
				// no traspasa el piso o el techo
			}
			if (chocasteConPisoInf(pisos[4]) && this.x > 103){
				//permite saltar
				//e = true;
				// no traspasa el piso o el techo
				colisionPisoInf(pisos[4]);
				entorno.escribirTexto("CHOQUE INF", 300, 650);
			
			}
			//deja saltar
			if (this.y < 50 && this.x < 660){
				e = false;
			}
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

    public boolean colisionConCommodore(Commodore compu) {
        if (compu == null){
            return false;
        }
        return this.x > compu.x - 40 && this.x < compu.x + 40 && this.y > compu.y - 40 && this.y < compu.y + 40;   
    }

    public boolean colisionConVelociraptor(Velociraptor velo){
        if (velo == null){
            return false;   
        }
        return this.x > velo.x - 20 && this.x < velo.x + 20 && this.y > velo.y - 20 && this.y < velo.y + 20;   
    } 

    public boolean chocasteConPisoInf(Piso piso) {
        return  this.y +40 >= piso.y ;
    }

    public boolean chocasteConPisoSup(Piso piso) {
        return  this.y -40 <= piso.y ;
    }

    public void colisionPisoInf(Piso piso) {

        if (this.y+40 >= piso.y ){
            this.y = piso.y-40;
            
        }
    }

    public void colisionPisoSup(Piso piso) {
        if (this.y-40 <= piso.y ){
            this.y = piso.y +40;  
        }
    }
    
    public boolean chocasteCon(Entorno e) {
		return x >= e.ancho() || y >= e.alto() || x <= 0 || y <= 0 ;		
	}

    public void cambiarTrayectoria(Entorno e) {
		if (this.x >= e.ancho()){
            this.x -= 4;
        }
        if (this.y >= e.alto()){
            this.y -= 4;
        }
        if (this.x  <= 0){
            this.x += 4;
        }
        if (this.y <= 0){
            this.y += 4;
        }
	}

    public void moverIzquierda() {
            x -= 4;
    }

    public void moverDerecha() {
            x += 4;
    }

    public void moverArriba() {
        y -= 40;
    }

    public void moverAbajo() {
        y += 10;
    }
}

