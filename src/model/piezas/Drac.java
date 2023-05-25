/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

/**
 * Pieza del dragón, sus movimientos son los del caballo más los movimientos del
 * rey, es decir, una casilla en uno o ambos ejes más el movimiento en L.
 */
public class Drac extends Pieza {

    public Drac() {
        nombre = "Drac";
        imagen = "src/imagenes/drac.png";
        movx = new int[16];
        movy = new int[16];
        int pos = 0;
        //caballo
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
        //rey
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
