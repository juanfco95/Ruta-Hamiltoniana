/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

/**
 * Pieza del rey, sus movimientos son exactamente una casilla en el eje x y/o 
 * una casilla en el eje y.
 */
public class Rey extends Pieza {

    public Rey() {
        nombre = "Rey";
        imagen = "src/imagenes/rey.png";
        movx = new int[8];
        movy = new int[8];
        int pos = 0;
        movx[pos] = 1; 
        movy[pos++] = 0;            
        movx[pos] = 1; 
        movy[pos++] = -1;            
        movx[pos] = 0; 
        movy[pos++] = -1;            
        movx[pos] = -1; 
        movy[pos++] = -1;        
        movx[pos] = -1; 
        movy[pos++] = 0;            
        movx[pos] = -1; 
        movy[pos++] = 1;            
        movx[pos] = 0; 
        movy[pos++] = 1;            
        movx[pos] = 1; 
        movy[pos++] = 1;            
    }
}
