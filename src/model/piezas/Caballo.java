/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package model.piezas;

/**
 * Pieza del caballo, sus movimientos son de dos casillas en una dirección y 
 * luego otra casilla en perpendicular, haciendo forma de L.
 */
public class Caballo extends Pieza {

    public Caballo() {
        nombre = "Caballo";
        imagen = "src/imagenes/caballo.png";
        
        movx = new int[8];
        movy = new int[8];
        
        int pos = 0;
        movx[pos] = 1; 
        movy[pos++] = 2;            
        movx[pos] = 2; 
        movy[pos++] = 1;            
        movx[pos] = 1; 
        movy[pos++] = -2;            
        movx[pos] = 2; 
        movy[pos++] = -1;        
        movx[pos] = -1; 
        movy[pos++] = 2;            
        movx[pos] = -2; 
        movy[pos++] = 1;            
        movx[pos] = -1; 
        movy[pos++] = -2;            
        movx[pos] = -2; 
        movy[pos++] = -1;            
    }
}
