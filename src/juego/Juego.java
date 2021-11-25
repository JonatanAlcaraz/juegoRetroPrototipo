package juego;

import java.awt.Color;


import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

//import juego.personaje;
//import juego.piso;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	Entorno entorno;
	Personaje personaje;
	
	Fondo fondo;
	Commodore compu;
	boolean a ,b ,c ,d ,e , rayito, finDelJuego;
	Laser laser;
	Rayo rayo;
	Panel panel , aux;
	Laser[] lasers;
	Velociraptor[] velociraptor;
	Piso[] pisos;
	Image imagenGameOver , imagenWin;
	
	public Juego() {
		// Inicializa el objeto entorno 
		this.entorno = new Entorno(this, " Trabajo Practico: Castlevania, Barbarianna Viking Edition - Grupo 6 - ALcaraz - Alaniz  - V0.01", 800, 675);
		this.personaje = new Personaje(60, 565.5 ,1,1);
		this.compu = new Commodore(636, 65, 1, 1);
		this.panel = new Panel(400, 650, 800, 100);
		this.imagenGameOver = Herramientas.cargarImagen("gameover.png");
		this.imagenWin = Herramientas.cargarImagen("win.jpg");
		this.finDelJuego = false;
		
		//fondo
		this.fondo = new Fondo(400, 337.5);

		// pisos  
		this.pisos = new Piso[5];
		this.pisos[0] = new Piso(395.5, 605.5, 970, 10);
		this.pisos[1] = new Piso(300.5, 485.5, 670, 10);
		this.pisos[2] = new Piso(470.5, 365.5, 670, 10);
		this.pisos[3] = new Piso(300.5, 245.5, 670, 10);
		this.pisos[4] = new Piso(470.5, 125.5, 670, 10); 
		
		// velocirraptors
		this.velociraptor = new Velociraptor[5]; 
		this.velociraptor[0] = new Velociraptor(448, 85.5 ,0.1,0.3);
		this.velociraptor[1] = new Velociraptor(648, 565.5 ,0.1,0.3);
		this.velociraptor[2] = new Velociraptor(120, 445.5 ,0.1,0.3);
		this.velociraptor[3] = new Velociraptor(649, 325.5 ,0.1,0.3);
		this.velociraptor[4] = new Velociraptor(121, 205.5 ,0.1,0.3);
		
		//lasers velocirraptors
		this.lasers = new Laser[5];
		this.lasers[0] = new Laser(velociraptor[0].x, velociraptor[0].y ,0.1 , 0.2);
		this.lasers[1] = new Laser(velociraptor[1].x, velociraptor[1].y ,0.1 , 0.2);
		this.lasers[2] = new Laser(velociraptor[2].x, velociraptor[2].y ,0.1 , 0.2);
		this.lasers[3] = new Laser(velociraptor[3].x, velociraptor[3].y ,0.1 , 0.2);
		this.lasers[4] = new Laser(velociraptor[4].x, velociraptor[4].y ,0.1 , 0.2);
		
		// Inicia el juego!
		this.entorno.iniciar();
	}
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		entorno.cambiarFont("Arial", 18, Color.white);
		fondo.dibujar(entorno);
		panel.dibujar(entorno);
		compu.dibujarse(entorno);
		personaje.dibujarse(entorno);
		personaje.colisiones(entorno, pisos);
	
		// dibuja los pisos
		for (int index = 0; index < pisos.length; index++) {
			pisos[index].dibujar(entorno);	
		}
		
		personaje.actualizarPiso(this.pisos);
		//entorno.escribirTexto("personaje x:" + personaje.x, 500, 150);
		//entorno.escribirTexto("velo y:" + velociraptor[0].y, 500, 200);
		
		if (!finDelJuego){
			entorno.escribirTexto("Vida: " + this.personaje.vidas + "%", 150, 650 );
			entorno.escribirTexto("Enemigos eliminados: " + this.personaje.puntos, 500, 650);
		}
		
		// Dibujamos el Rayo cuando dispara pero solo si no existe 
		if (entorno.sePresiono(entorno.TECLA_ESPACIO) && this.rayo == null){
			
			this.rayo = new Rayo(personaje.x,personaje.y,0,0.2);
			// rayito permite que no se buguee el rayo
			this.rayito = true;
		}
		if (this.rayito){	
			if (this.rayo == null){
				this.rayo = new Rayo(personaje.x,personaje.y,0,0.2);
			}
			else{
				if (this.rayo.chocasteCon(entorno) ){
					this.rayito = false;
					this.rayo = null;
				}
				else{
					rayo.dibujarse(entorno, personaje);
				}
			}
		}
		// movimiento de dinosauriuo
		for (int i = 0; i < velociraptor.length; i++) {

			if(this.velociraptor[i] != null) {
				this.velociraptor[i].y += 3;
				this.velociraptor[i].factorDesplazamiento = 3;
				// actualiza el piso del dinosaurio
				this.velociraptor[i].actualizarPiso(this.pisos);
				// lo dibuja
				this.velociraptor[i].dibujarse(entorno);
				// si esta en el piso 3 o 5 
				// mientras no este arriba del hueco toca el piso
				if (((this.velociraptor[i].pisoActual  == 5) && this.velociraptor[i].x > 103) || (this.velociraptor[i].pisoActual  == 3  && this.velociraptor[i].x > 103)){
					
					this.velociraptor[i].colisionPisoInf(pisos[this.velociraptor[i].pisoActual - 1]);	
					// se mueve
					this.velociraptor[i].moverIzquierda();
				}
				// si esta en el piso 2 o 4 
				// mientras no este arriba del hueco toca el piso
				if (((this.velociraptor[i].pisoActual  == 2) && this.velociraptor[i].x < 660) || ((this.velociraptor[i].pisoActual  == 4) && this.velociraptor[i].x <660)){
					this.velociraptor[i].colisionPisoInf(pisos[this.velociraptor[i].pisoActual - 1]);
					// se mueve
					this.velociraptor[i].moverDerecha();
				}
				
				if(this.velociraptor[i].pisoActual==1){
					//toca el piso
					this.velociraptor[i].colisionPisoInf(pisos[this.velociraptor[i].pisoActual - 1]);	
					//se mueve
					this.velociraptor[i].moverIzquierda();
				}
				
				// si llega al fondo aparece arriba / si choca con el personaje aparece arriba  / si choca con el rayo aparece arriba 
				if (  this.personaje.colisionConVelociraptor(velociraptor[i]) || this.velociraptor[i].chocasteCon(entorno) || (this.rayo != null && this.rayo.colisionConVelociraptor(velociraptor[i]))){
					
					this.velociraptor[i].factorDesplazamiento = 0;
					if (this.personaje.colisionConVelociraptor(velociraptor[i]) && this.personaje.vidas > 0) {	
							this.personaje.vidas--;	
					}
					if (this.rayo != null && this.rayo.colisionConVelociraptor(velociraptor[i]) && this.personaje.vidas >  0 && !this.finDelJuego) {
						this.rayo = null;
						this.rayito = false; 
						this.personaje.puntos += 1;
					}
					this.velociraptor[i] = new Velociraptor(448, 85.5 ,0.1,0.3);
				}
			}
		}
		// disparos de dinosaurios
		for (int j = 0; j < lasers.length; j++) {
			if(this.lasers[j] != null)  {
				// velocidad del laser
				this.lasers[j].factorDesplazamiento = 14;

				if((this.velociraptor[j].pisoActual==1 || this.velociraptor[j].pisoActual==3 || this.velociraptor[j].pisoActual==5) && this.velociraptor[j].chocasteConPisoInf(pisos[this.velociraptor[j].pisoActual - 1])){
					// dibuja y mueve dependiendo del piso
					this.lasers[j].dibujarse(entorno, velociraptor[j], pisos);
					this.lasers[j].moverIzquierda();
				}
				if(this.velociraptor[j].pisoActual==2 || this.velociraptor[j].pisoActual==4) {
					// dibuja y mueve dependiendo del piso
					this.lasers[j].dibujarse(entorno, velociraptor[j], pisos);
					this.lasers[j].moverDerecha();
				}
				
				if(this.lasers[j].chocasteCon(entorno)){
					
					// para que no dispare en el aire 
					if(this.velociraptor[j].chocasteConPisoInf(pisos[this.velociraptor[j].pisoActual - 1]) && this.velociraptor[j].x > 220 && this.velociraptor[j].x < 655){
						this.lasers[j] = null;
						this.lasers[j] = new Laser( velociraptor[j].x , velociraptor[j].y , 0.1, 0.2);
					}
				}
				// daño del laser si no se agacho
				else if(this.lasers[j].colisionConPersonaje(this.personaje) && this.personaje.vidas > 0 && !this.personaje.seAgacho  ){
					
					this.personaje.vidas--;
					
					/*if(this.velociraptor[j].chocasteConPisoInf(pisos[this.velociraptor[j].pisoActual - 1]) && this.velociraptor[j].x > 220 && this.velociraptor[j].x < 655 ){
						this.lasers[j] = null;
						this.lasers[j] = new laser( velociraptor[j].x , velociraptor[j].y , 0.1, 0.2);
					}*/
				}
			}	
		}
		//dibujar fin del juego 
		if (this.personaje.vidas == 0 && !this.finDelJuego) {
			entorno.dibujarImagen(imagenGameOver, entorno.ancho() / 2, entorno.alto() / 2 - 7, 0);
			entorno.escribirTexto("Puntuacion Total: " + this.personaje.puntos, entorno.ancho() / 2.5, entorno.alto() - 90);
		
		}
		if (this.personaje.colisionConCommodore(compu)) {
			this.finDelJuego = true;
		} 	
		if (this.finDelJuego){
			entorno.dibujarImagen(imagenWin, entorno.ancho() / 2, entorno.alto() / 2 - 7, 0);
			aux  = new Panel(400, 20, 800, 100);
			entorno.escribirTexto("Puntuacion Total: " + this.personaje.puntos, entorno.ancho() / 2.5, entorno.alto() - 90);
			aux.dibujar(entorno);
			
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

