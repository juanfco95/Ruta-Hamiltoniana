/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 * Clase abstracta que sirve de molde para definir las piezas del tablero y 
 * permite hacer el backtracking con Objetos de tipo pieza independientemente de
 * la implementación de cada una.
 */
public abstract class Pieza {

    protected String nombre; // Nombre de la pieza
    protected String imagen; // Imagen de la pieza
    
    public int x; // Posición x de la pieza en el tablero
    public int y; // Posición y de la pieza en el tablero
    
    protected int movx[]; // Distancia en x que se desplaza con cada movimiento
    protected int movy[]; // Distancia en y que se desplaza con cada movimiento

    // Atributo que señala que los movimientos aumentan con las dimensiones
    protected boolean afectadimension = false;
    
    protected Color colorP; // Color de los números de movimiento de esta pieza

    public static Pieza newPieza(String s, int d){
        switch(s){
            case "Caballo":
                return new Caballo();
            case "Reina":
                return new Reina(d);
            case "Torre":
                return new Torre(d);
            case "Rey":
                return new Rey();
            case "Alfil":
                return new Alfil(d);
            case "Drac":
                return new Drac();
            default: 
                System.out.println("Error al generar nueva pieza: " + s);
        }
        return null;
    }
    
    public Color getColor() {
        return colorP;
    }

    public void setColor(Color c) {
        colorP = c;
    }

    public boolean afectaDimension() {
        return afectadimension;
    }

    public String getNombre() {
        return nombre;
    }

    public Image getImagen() {
        return (new ImageIcon(imagen)).getImage();
    }

    public int getNumMovs() {
        return movx.length;
    }

    public int getMovX(int i) {
        return movx[i];
    }

    public int getMovY(int i) {
        return movy[i];
    }

    public void setCordenates(int i, int j) {
        this.x = i;
        this.y = j;
    }
    
}
