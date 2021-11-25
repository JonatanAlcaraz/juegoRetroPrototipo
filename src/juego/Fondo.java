

package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Fondo {

    double x;
    double y;
    Image img1; 
    
    public Fondo(double x, double y) {
        this.x = x;
        this.y = y;
        this.img1 = Herramientas.cargarImagen("castillo.jpg");
    }

    public void dibujar(Entorno e) {
        
        e.dibujarImagen(img1, x, y, 0.0);
    }
    
}
