package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Commodore {
    double x, y, alto , angulo;
    Image img1;
    int pisoActual;

    public Commodore(double x, double y, double angulo, double alto) {
        this.x = x;
        this.y = y;
        
        this.alto = alto;
        this.angulo = angulo;
        this.pisoActual = 1;
        
        img1 = Herramientas.cargarImagen("commodore.png");
        

        
    }
    

    public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(img1, this.x, this.y, 25.1 , 0.2);
    }
}
