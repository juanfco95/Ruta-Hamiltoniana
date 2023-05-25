/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

/**
 * Pieza del alfil, sus movimientos se limitan a las diagonales.
 */
public class Alfil extends Pieza {

    public Alfil(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = "Alfil";
        imagen = "src/imagenes/alfil.png";
        movx = new int[(d - 1) * 4 * 2];
        movy = new int[(d - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = i;   //oblicuo 1
                movy[pos++] = i; //oblicuo1
                movx[pos] = -i;  //oblicuo 2
                movy[pos++] = i; //oblicuo2               
            }
        }
    }
    
}
