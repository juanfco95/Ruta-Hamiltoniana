/**
 * Practica 2 Algoritmos Avanzados - Ing Informática UIB
 * @date 26/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=bloavvbQU4w
 */
package model.piezas;

/**
 * Pieza de la torre, sus movimientos son n casillas en el eje x o el eje y.
 */
public class Torre extends Pieza {

    public Torre(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = "Torre";
        imagen = "src/imagenes/torre.png";
        movx = new int[(d - 1) * 4];
        movy = new int[(d - 1) * 4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0;   // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i;   // horizontal
                movy[pos++] = 0; //horizontal            
            }
        }
    }
}
